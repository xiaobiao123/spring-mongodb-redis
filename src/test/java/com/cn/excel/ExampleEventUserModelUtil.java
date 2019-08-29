//package com.cn.excel;
//
//import org.apache.poi.openxml4j.opc.OPCPackage;
//import org.apache.poi.ss.usermodel.BuiltinFormats;
//import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.xssf.eventusermodel.XSSFReader;
//import org.apache.poi.xssf.model.SharedStringsTable;
//import org.apache.poi.xssf.model.StylesTable;
//import org.apache.poi.xssf.usermodel.XSSFCellStyle;
//import org.apache.poi.xssf.usermodel.XSSFRichTextString;
//import org.xml.sax.*;
//import org.xml.sax.helpers.DefaultHandler;
//import org.xml.sax.helpers.XMLReaderFactory;
//
//import java.io.BufferedWriter;
//import java.io.InputStream;
//import java.util.*;
//
//public class ExampleEventUserModelUtil {
//	private static String cs;
//	private static StylesTable stylesTable;
//	private static List dataList = new ArrayList();
//	private static List successList = new ArrayList();
//	private static List failList = new ArrayList();
//	private static Map map = new HashMap();;
////	@Value("${weight}")
////	public static String weight;
//	/**
//	 * 处理一个sheet
//	 *
//	 * @param filename
//	 * @throws Exception
//	 */
//	public void processOneSheet(String filename) throws Exception {
//
//		OPCPackage pkg = OPCPackage.open(filename);
//		XSSFReader r = new XSSFReader(pkg);
//		stylesTable = r.getStylesTable();
//		SharedStringsTable sst = r.getSharedStringsTable();
//
//		XMLReader parser = fetchSheetParser(sst);
//		Iterator<InputStream> sheets = r.getSheetsData();
//		while (sheets.hasNext()) {
//			InputStream sheet = sheets.next();
//			InputSource sheetSource = new InputSource(sheet);
//			parser.parse(sheetSource);
//			sheet.close();
//		}
//
//	}
//
//	/**
//	 * 处理所有sheet
//	 *
//	 * @param filename
//	 * @throws Exception
//	 */
//	public void processAllSheets(String filename) throws Exception {
//
//		OPCPackage pkg = OPCPackage.open(filename);
//		XSSFReader r = new XSSFReader(pkg);
//		SharedStringsTable sst = r.getSharedStringsTable();
//
//		XMLReader parser = fetchSheetParser(sst);
//
//		Iterator<InputStream> sheets = r.getSheetsData();
//		while (sheets.hasNext()) {
//			System.out.println("Processing new sheet:\n");
//			InputStream sheet = sheets.next();
//			InputSource sheetSource = new InputSource(sheet);
//			parser.parse(sheetSource);
//			sheet.close();
//			System.out.println("");
//		}
//	}
//
//	/**
//	 * 获取解析器
//	 *
//	 * @param sst
//	 * @return
//	 * @throws org.xml.sax.SAXException
//	 */
//	public XMLReader fetchSheetParser(SharedStringsTable sst)
//			throws SAXException {
//		XMLReader parser = XMLReaderFactory
//				.createXMLReader("org.apache.xerces.parsers.SAXParser");
//		ContentHandler handler = new SheetHandler(sst);
//		parser.setContentHandler(handler);
//		return parser;
//	}
//
//	/**
//	 * 自定义解析处理器 See org.xml.sax.helpers.DefaultHandler javadocs
//	 */
//	private static class SheetHandler extends DefaultHandler {
//		/**
//		 * 共享字符串表
//		 */
//		private SharedStringsTable sst;
//		/**
//		 * 上一次的内容
//		 */
//		private String lastContents;
//		/**
//		 * 字符串标识
//		 */
//		private boolean nextIsString;
//		/**
//		 * 行集合
//		 */
//		private List<String> rowlist = new ArrayList<String>();
//		/**
//		 * 当前行
//		 */
//		private int curRow = 0;
//		/**
//		 * 当前列
//		 */
//		private int curCol = 0;
//
//		// 定义前一个元素和当前元素的位置，用来计算其中空的单元格数量，如A6和A8等
//		private String preRef = null, ref = null;
//		// 定义该文档一行最大的单元格数，用来补全一行最后可能缺失的单元格
//		private String maxRef = null;
//
//		private CellDataType nextDataType = CellDataType.SSTINDEX;
//		private final DataFormatter formatter = new DataFormatter();
//		private short formatIndex;
//		private String formatString;
//
//		// 用一个enum表示单元格可能的数据类型
//		enum CellDataType {
//			BOOL, ERROR, FORMULA, INLINESTR, SSTINDEX, NUMBER, DATE, NULL
//		}
//
//		private SheetHandler(SharedStringsTable sst) {
//			this.sst = sst;
//		}
//
//		/**
//		 * 解析一个element的开始时触发事件
//		 */
//		public void startElement(String uri, String localName, String name,
//				Attributes attributes) throws SAXException {
//
//			map.put("flag", "start");
//			// c => cell
//			if (name.equals("c")) {
//				// 前一个单元格的位置
//				if (preRef == null) {
//					preRef = attributes.getValue("r");
//				} else {
//					preRef = ref;
//				}
//				// 当前单元格的位置
//				ref = attributes.getValue("r");
//
//				this.setNextDataType(attributes);
//
//				// Figure out if the value is an index in the SST
//				String cellType = attributes.getValue("t");
//				// if(cellType != null && cellType.equals("s")) {
//				// nextIsString = true;
//				// } else {
//				// nextIsString = false;
//				// }
//				if (cellType == null) { //处理空单元格问题
//					nextIsString = true;
//					cs = "x";
//				} else if (cellType != null && cellType.equals("s")) {
//					cs = "s";
//					nextIsString = true;
//				} else {
//					nextIsString = false;
//					cs = "";
//				}
//
//			}
//			// Clear contents cache
//			lastContents = "";
//		}
//
//		/**
//		 * 根据element属性设置数据类型
//		 *
//		 * @param attributes
//		 */
//		public void setNextDataType(Attributes attributes) {
//
//			nextDataType = CellDataType.NUMBER;
//			formatIndex = -1;
//			formatString = null;
//			String cellType = attributes.getValue("t");
//			String cellStyleStr = attributes.getValue("s");
//			if ("b".equals(cellType)) {
//				nextDataType = CellDataType.BOOL;
//			} else if ("e".equals(cellType)) {
//				nextDataType = CellDataType.ERROR;
//			} else if ("inlineStr".equals(cellType)) {
//				nextDataType = CellDataType.INLINESTR;
//			} else if ("s".equals(cellType)) {
//				nextDataType = CellDataType.SSTINDEX;
//			} else if ("str".equals(cellType)) {
//				nextDataType = CellDataType.FORMULA;
//			}
//			if (cellStyleStr != null) {
//				int styleIndex = Integer.parseInt(cellStyleStr);
//				XSSFCellStyle style = stylesTable.getStyleAt(styleIndex);
//				formatIndex = style.getDataFormat();
//				formatString = style.getDataFormatString();
//				if ("m/d/yy" == formatString) {
//					nextDataType = CellDataType.DATE;
//					// full format is "yyyy-MM-dd hh:mm:ss.SSS";
//					formatString = "yyyy-MM-dd";
//				}
//				if (formatString == null) {
//					nextDataType = CellDataType.NULL;
//					formatString = BuiltinFormats.getBuiltinFormat(formatIndex);
//				}
//			}
//		}
//
//		/**
//		 * 解析一个element元素结束时触发事件
//		 */
//		public void endElement(String uri, String localName, String name)
//				throws SAXException {
//			// Process the last contents as required.
//			// Do now, as characters() may be called more than once
//			String flag =(String)map.get("flag");
//
//			if (nextIsString) {
//				if ("s".equals(cs)) {
//					int idx = Integer.parseInt(lastContents);
//					lastContents = new XSSFRichTextString(sst.getEntryAt(idx))
//							.toString();
//					nextIsString = false;
//				}
//				if ("c".equals(name) && "x".equals(cs)) {
//					if("start".equals(flag)){
//						rowlist.add(curCol, "");
//						curCol++;
//					}
//
//				}
//			}
//
//			map.put("flag", "end");
//
//			// v => contents of a cell
//			// Output after we've seen the string contents
//			if ("v".equals(name) || "t".equals(name)) {
//				String value = this.getDataValue(lastContents.trim(), "");
//				// 补全单元格之间的空单元格
//				if (!ref.equals(preRef)) {
//					int len = countNullCell(ref, preRef);
//					for (int i = 0; i < len; i++) {
//						rowlist.add(curCol, "");
//						curCol++;
//					}
//				}
//				rowlist.add(curCol, value);
//				curCol++;
//			} else {
//				// 如果标签名称为 row，这说明已到行尾，调用 optRows() 方法
//				if (name.equals("row")) {
//					String value = "";
//					// 默认第一行为表头，以该行单元格数目为最大数目
//					if (curRow == 0) {
//						maxRef = ref;
//					}
//					// 补全一行尾部可能缺失的单元格
//					if (maxRef != null) {
//						int len = countNullCell(maxRef, ref);
//
//						for (int i = 0; i <= len; i++) {
//							//rowlist.add(curCol, "");
//							//curCol++;
//						}
//					}
//					// 拼接一行的数据
//					for (int i = 0; i < rowlist.size(); i++) {
//						if (rowlist.get(i).contains(",")) {
//							value += "\"" + rowlist.get(i) + "\",";
//
//						} else {
//							if (i == rowlist.size() - 1) {
//								value += rowlist.get(i);
//							} else {
//								value += rowlist.get(i) + ",";
//							}
//						}
//					}
//					// 加换行符
//					value += "\n";
//					// try {
//					// writer.write(value);
//					// } catch (IOException e) {
//					// e.printStackTrace();
//					// }
//					curRow++;
//					// System.out.println(curRow + rowlist.toString()+"------");
//					// 一行的末尾重置一些数据
//					dataList.add(value);
//					rowlist.clear();
//					curCol = 0;
//					preRef = null;
//					ref = null;
//				}
//			}
//		}
//
//		/**
//		 * 根据数据类型获取数据
//		 *
//		 * @param value
//		 * @param thisStr
//		 * @return
//		 */
//		public String getDataValue(String value, String thisStr)
//
//		{
//			switch (nextDataType) {
//			// 这几个的顺序不能随便交换，交换了很可能会导致数据错误
//			case BOOL:
//				char first = value.charAt(0);
//				thisStr = first == '0' ? "FALSE" : "TRUE";
//				break;
//			case ERROR:
//				thisStr = "\"ERROR:" + value.toString() + '"';
//				break;
//			case FORMULA:
//				thisStr = '"' + value.toString() + '"';
//				break;
//			case INLINESTR:
//				XSSFRichTextString rtsi = new XSSFRichTextString(
//						value.toString());
//				thisStr = rtsi.toString();
//				rtsi = null;
//				break;
//			case SSTINDEX:
//				String sstIndex = value.toString();
//				thisStr = value.toString();
//				break;
//			case NUMBER:
//				if (formatString != null) {
//					thisStr = formatter.formatRawCellContents(
//							Double.parseDouble(value), formatIndex,
//							formatString).trim();
//				} else {
//					thisStr = value;
//				}
//				thisStr = thisStr.replace("_", "").trim();
//				break;
//			case DATE:
//				try {
//					thisStr = formatter.formatRawCellContents(
//							Double.parseDouble(value), formatIndex,
//							formatString);
//				} catch (NumberFormatException ex) {
//					thisStr = value.toString();
//				}
//				thisStr = thisStr.replace(" ", "");
//				break;
//			default:
//				thisStr = "";
//				break;
//			}
//			return thisStr;
//		}
//
//		/**
//		 * 获取element的文本数据
//		 */
//		public void characters(char[] ch, int start, int length)
//				throws SAXException {
//			lastContents += new String(ch, start, length);
//		}
//
//		/**
//		 * 计算两个单元格之间的单元格数目(同一行)
//		 *
//		 * @param ref
//		 * @param preRef
//		 * @return
//		 */
//		public int countNullCell(String ref, String preRef) {
//			// excel2007最大行数是1048576，最大列数是16384，最后一列列名是XFD
//			String xfd = ref.replaceAll("\\d+", "");
//			String xfd_1 = preRef.replaceAll("\\d+", "");
//
//			xfd = fillChar(xfd, 3, '@', true);
//			xfd_1 = fillChar(xfd_1, 3, '@', true);
//
//			char[] letter = xfd.toCharArray();
//			char[] letter_1 = xfd_1.toCharArray();
//			int res = (letter[0] - letter_1[0]) * 26 * 26
//					+ (letter[1] - letter_1[1]) * 26
//					+ (letter[2] - letter_1[2]);
//			return res - 1;
//		}
//
//		/**
//		 * 字符串的填充
//		 *
//		 * @param str
//		 * @param len
//		 * @param let
//		 * @param isPre
//		 * @return
//		 */
//		String fillChar(String str, int len, char let, boolean isPre) {
//			int len_1 = str.length();
//			if (len_1 < len) {
//				if (isPre) {
//					for (int i = 0; i < (len - len_1); i++) {
//						str = let + str;
//					}
//				} else {
//					for (int i = 0; i < (len - len_1); i++) {
//						str = str + let;
//					}
//				}
//			}
//			return str;
//		}
//	}
//
//	static BufferedWriter writer = null;
//
//	public static void main(String[] args) throws Exception {
//		dataList = new ArrayList();
//		ExampleEventUserModelUtil example = new ExampleEventUserModelUtil();
//		String filename = "C:\\2000.xlsx";
//		System.out.println("-- 程序开始 --");
//		long time_1 = System.currentTimeMillis();
//		try {
//			// writer = new BufferedWriter(new OutputStreamWriter(new
//			// FileOutputStream("C:\\users40.xlsx")));
//			example.processOneSheet(filename);
//		} finally {
//			// writer.close();
//		}
//		long time_2 = System.currentTimeMillis();
//		System.out.println(dataList.size());
//		System.out.println("-- 程序结束1 --");
//		System.out.println("-- 耗时1 --" + (time_2 - time_1) );
//
//		doOneRowCheck();
//		System.out.println("-- 程序结束2 --");
//		System.out.println("-- 耗时2--" + (time_2 - time_1) );
//
////		Connection connection = null;
////		try {
////			Class.forName("com.mysql.jdbc.Driver");
////			connection = DriverManager.getConnection(
////					"jdbc:mysql://127.0.0.1:3306/testpoi", "root", "root123");
////
////			connection.setAutoCommit(false);
////			PreparedStatement cmd = connection
////					.prepareStatement("insert into poinew values(?,?,?,?,?,?,?,?,?,?"
////							+ ",?,?,?,?,?,?,?,?,?,?"
////							+ ",?,?,?,?,?,?,?,?,?,?"
////							+ ",?,?,?,?,?,?,?,?,?)");
////
////			// for (int i = 0; i < 500000; i++) {//100万条数据
////			// cmd.setString(1, String.valueOf(i));
////			// cmd.setString(2, "test"+i);
////			// cmd.addBatch();
////			// if(i%5000==0){
////			// cmd.executeBatch();
////			// }
////			// }
////
////			int num = 0;
////			for (int i = 0; i < successList.size(); i++) {
////
////				String s = (String) successList.get(i);
////				String[] record = s.split(",");
////				num++;
////				int k = 0;
////
////				cmd.setString(++k, null);
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));// 10
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));// 20
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));// 30
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////				cmd.setString(++k, String.valueOf(record[k - 2]));
////
////				cmd.addBatch();
////				if (num % 3000 == 0) {
////					cmd.executeBatch();
////				}
////			}
////			cmd.executeBatch();
////			connection.commit();
////
////			cmd.close();
////			connection.close();
////		} catch (Exception e) {
////			e.printStackTrace();
////			if (connection != null) {
////				try {
////					connection.rollback();
////				} catch (SQLException e1) {
////
////				}
////			}
////		}
////
////		time_2 = System.currentTimeMillis();
////		System.out.println("-- 程序结束3 --");
////		System.out.println("-- 耗时3--" + (time_2 - time_1) / 1000 + "s");
//
//	}
//
//	private static void doOneRowCheck() {
//	//	CustomizePropertyPlaceHolder holder = new CustomizePropertyPlaceHolder();
//
//		//String str = holder.getProperty("test", "1");
//	//System.out.println("BBBBBBBBBBBBBB"+weight);
//
//
//
//		int num = 0;
//		for (int i = 0; i < dataList.size(); i++) {
//			//FileErr err = new FileErr();
//			//if (i == 0) {
//			//	continue;
//			//}
//			//String s = (String) dataList.get(i);
//			//String[] record = s.split(",");
//			//num++;
//			//
//			//boolean bool = true;
//			////操作日期
//			//String   operationDate = record[8];
//			//
//			//if(bool&&!CheckUtils.isValidDate(operationDate)){
//			//	err.setErrNo(String.valueOf(num));
//			//	err.setErrMsg("操作日期格式不正确");
//			//	err.setUserId(null);
//			//	bool = false;
//			//}
//            //
//			//
//			////计费重量
//			//String weight = record[14];
//            //
//			//if(bool&&!CheckUtils.isFloatNumber(weight)){
//			//	err.setErrNo(String.valueOf(num));
//			//	err.setErrMsg("计算重量格式不正确");
//			//	err.setUserId(null);
//			//	bool = false;
//			//}
//			//
//			//if(bool){
//			//	successList.add(s);
//			//} else {
//			//	failList.add(err);
//			//}
//
//		}
//
//		System.out.println(successList.toString());
//		System.out.println("failList:"+failList.toString());
//
//
//	}
//
//}
