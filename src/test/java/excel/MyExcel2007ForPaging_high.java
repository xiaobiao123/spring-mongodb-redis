//package excel;
//
//import org.apache.commons.lang3.StringUtils;
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.xssf.eventusermodel.XSSFReader;
//import org.apache.poi.xssf.model.SharedStringsTable;
//import org.apache.poi.xssf.usermodel.XSSFRichTextString;
//import org.xml.sax.*;
//import org.xml.sax.helpers.DefaultHandler;
//import org.xml.sax.helpers.XMLReaderFactory;
//
//import java.io.InputStream;
//import java.util.*;
//import java.util.Map.Entry;
//import java.util.regex.Pattern;
//
//public class MyExcel2007ForPaging_high {
//    private static List<Map<String, String>> dataListT;
//    private final int startRow;
//    private final int endRow;
//    private int currentRow = 0;
//    private final String filename;
//    private static Map<String, String> map;
//    static char[] strChar;
//
//    private static String cs;
//
//
//    /**
//     * 构造方法
//     */
//    public MyExcel2007ForPaging_high(String filename, int startRow, int endRow) throws Exception {
//        dataListT = new ArrayList<>();
//        if (StringUtils.isEmpty(filename)) throw new Exception("文件名不能空");
//        this.filename = filename;
//        this.startRow = startRow;
//        this.endRow = endRow + 1;
//        processSheet();
//    }
//
//    /**
//     * 指定获取第一个sheet
//     *
//     * @throws Exception
//     */
//    private void processSheet() throws Exception {
//        OPCPackage pkg = OPCPackage.open(filename);
//        XSSFReader r = new XSSFReader(pkg);
//        SharedStringsTable sst = r.getSharedStringsTable();
//        XMLReader parser = fetchSheetParser(sst);
//        Iterator<InputStream> it = r.getSheetsData();
//        while (it.hasNext()) {
//            map = null;
//            InputStream sheet1 = it.next();
//            InputSource sheetSource = new InputSource(sheet1);
//            parser.parse(sheetSource);
//            sheet1.close();
//        }
//    }
//
//    public void processAllSheets(String filename) throws Exception {
//
//        OPCPackage pkg = OPCPackage.open(filename);
//        XSSFReader r = new XSSFReader(pkg);
//        SharedStringsTable sst = r.getSharedStringsTable();
//
//        XMLReader parser = fetchSheetParser(sst);
//        Iterator<InputStream> sheets = r.getSheetsData();
//        while (sheets.hasNext()) {
//            System.out.println("Processing new sheet:\n");
//            InputStream sheet = sheets.next();
//            InputSource sheetSource = new InputSource(sheet);
//            parser.parse(sheetSource);
//            sheet.close();
//            System.out.println("");
//        }
//    }
//
//
//    /**
//     * 加载sax 解析器
//     *
//     * @param sst
//     * @return
//     * @throws SAXException
//     */
//    private XMLReader fetchSheetParser(SharedStringsTable sst) throws SAXException {
//        XMLReader parser =
//                XMLReaderFactory.createXMLReader(
//                        "org.apache.xerces.parsers.SAXParser"
//                );
//        ContentHandler handler = new PagingHandler(sst);
//        parser.setContentHandler(handler);
//        return parser;
//    }
//
//    /**
//     * See org.xml.sax.helpers.DefaultHandler javadocs
//     */
//    private class PagingHandler extends DefaultHandler {
//        /**
//         * 共享字符串表
//         */
//        private SharedStringsTable sst;
//        /**
//         * 上一次的内容
//         */
//        private String lastContents;
//        /**
//         * 字符串标识
//         */
//        private boolean nextIsString;
//        private String index = null;
//
//        private PagingHandler(SharedStringsTable sst) {
//            this.sst = sst;
//        }
//
//        /**
//         * 开始元素 （获取key 值）
//         */
//        @Override
//        public void startElement(String uri, String localName, String name,
//                                 Attributes attributes) throws SAXException {
//            if (name.equals("c")) {
//                index = attributes.getValue("r");// 那一列 如果A2
//
//                //CellReference cellReference = new CellReference(attributes.getValue("r"));
//                ////col
//                //short col = cellReference.getCol(); // cell index
//                //int row = cellReference.getRow(); // row index
//                //判断是否是新的一行
//                if (Pattern.compile("^A[0-9]+$").matcher(index).find()) {
//                    if (map != null && isAccess() && !map.isEmpty()) {
//                        dataListT.add(map);
//                    }
//                    map = new LinkedHashMap<>();
//                    currentRow++;
//                }
//                if (isAccess()) {
//                    String cellType = attributes.getValue("t");  //cell value
//                    if (cellType == null) { //处理空单元格问题
//                        nextIsString = true;
//                        cs = "x";
//                    } else
//                        if (cellType != null && cellType.equals("s")) {
//                        nextIsString = true;
//                        cs = "s";
//                    } else {
//                        nextIsString = false;
//                        cs = "";
//                    }
//                }
//            }
//            lastContents = "";
//        }
//
//        /**
//         * 获取value
//         */
//        @Override
//        public void endElement(String uri, String localName, String name)
//                throws SAXException {
//            if (isAccess()) {
//                if (nextIsString) {
//                    if ("s".equals(cs)) {
//                        int idx = Integer.parseInt(lastContents);
//                        lastContents = new XSSFRichTextString(sst.getEntryAt(idx)).toString();
//                        nextIsString = false;
//                    }
//                }
//                if ("c".equals(name) && "x".equals(cs)) {
//                    map.put(index, "");
//                }
//
//                if (name.equals("v")) {
//                    map.put(index, lastContents);
//                }
//            }
//
//        }
//
//        @Override
//        public void characters(char[] ch, int start, int length)
//                throws SAXException {
//            if (isAccess()) {
//                lastContents += new String(ch, start, length);
//            }
//        }
//
//        @Override
//        public void endDocument() throws SAXException {
//            if (map != null && isAccess() && !map.isEmpty()) {
//                dataListT.add(map);
//            }
//        }
//
//    }
//
//    private boolean isAccess() {
//        if (currentRow >= startRow && currentRow <= endRow) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 获取数据 并且填补字段值为空的数据
//     *
//     * @return
//     * @throws Exception
//     */
//    public List<Map<String, String>> getMyDataList() throws Exception {
//        List<Map<String, String>> list = dataListT.subList(startRow, dataListT.size());
//        if (!list.isEmpty()) {
//            Map<String, String> map = dataListT.get(0);
//            List<String> com = data("A", map.size() - 1);
//            for (int i = 0; i < list.size(); i++) {
//                Map<String, String> returnMap = list.get(i);
//                for (String str : com) {
//                    boolean flag = true;
//                    for (Entry<String, String> entry : returnMap.entrySet()) {
//                        if (entry.getKey().contains(str)) {
//                            //有
//                            flag = false;
//                            break;
//                        }
//                    }
//                    if (flag) {
//                        //没有
//                        returnMap.put(str + (i + 2), null);
//                    }
//                }
//            }
//        }
//        return list;
//    }
//
//    public static void main(String[] args) throws Exception {
//        long l = System.currentTimeMillis();
//        MyExcel2007ForPaging_high reader = new MyExcel2007ForPaging_high("C:\\3test.xlsx", 0, 60000);
//        reader.getMyDataList();
//        System.out.println(System.currentTimeMillis() - l);
//    }
//
//    /**
//     * 封装数据
//     *
//     * @param str
//     * @param counts
//     * @return
//     */
//    public static List<String> data(String str, int counts) {
//        List<String> list = new ArrayList<>();
//        list.add(str);
//        for (int i = 0; i < counts; i++) {
//            strChar = str.toCharArray();
//            jinwei(0);
//            str = new String(strChar);
//            list.add(str);
//        }
//        return list;
//    }
//
//    //数字进位
//    public static void jinwei(int index) {
//        char a = 'A';
//        int aint = (int) ('A');
//        if ((strChar.length - 1) - index >= 0) {
//            int sc = (int) strChar[(strChar.length - 1) - index];
//            if (sc - 25 >= aint) {
//                jinwei(index + 1);
//                strChar[(strChar.length - 1) - index] = a;
//            } else {
//                strChar[strChar.length - 1 - index] = (char) (sc + 1);
//            }
//        } else {
//            strChar[(strChar.length - 1) - index + 1] = a;
//            StringBuilder str = new StringBuilder();
//            str.append('A');
//            str.append(strChar);
//            strChar = str.toString().toCharArray();
//        }
//    }
//}