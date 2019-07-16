//package excel;
//
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import org.apache.poi.hssf.usermodel.DVConstraint;
//import org.apache.poi.hssf.usermodel.HSSFCell;
//import org.apache.poi.hssf.usermodel.HSSFCellStyle;
//import org.apache.poi.hssf.usermodel.HSSFDataFormat;
//import org.apache.poi.hssf.usermodel.HSSFDataValidation;
//import org.apache.poi.hssf.usermodel.HSSFFont;
//import org.apache.poi.hssf.usermodel.HSSFRow;
//import org.apache.poi.hssf.usermodel.HSSFSheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.DataValidation;
//import org.apache.poi.ss.usermodel.Name;
//import org.apache.poi.ss.util.CellRangeAddressList;
//
//public class ExcelLinkage {
//
//    // 样式
//    private HSSFCellStyle cellStyle;
//
//    // 初始化省份数据
//    private List<String> province = new ArrayList<String>(Arrays.asList("湖南",
//            "广东"));
//    // 初始化数据（湖南的市区）
//    private List<String> hnCity = new ArrayList<String>(Arrays.asList("长沙市",
//            "邵阳市"));
//    // 初始化数据（广东市区）
//    private List<String> gdCity = new ArrayList<String>(Arrays.asList("深圳市",
//            "广州市"));
//
//    public void setDataCellStyles(HSSFWorkbook workbook, HSSFSheet sheet) {
//        cellStyle = workbook.createCellStyle();
//        // 设置边框
////        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
////        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
////        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
////        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
////        // 设置背景色
////        cellStyle.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
////        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
////        // 设置居中
////        cellStyle.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//        // 设置字体
//        HSSFFont font = workbook.createFont();
//        font.setFontName("宋体");
//        font.setFontHeightInPoints((short) 11); // 设置字体大小
//        cellStyle.setFont(font);// 选择需要用到的字体格式
//        // 设置单元格格式为文本格式（这里还可以设置成其他格式,可以自行百度）
//        HSSFDataFormat format = workbook.createDataFormat();
//        cellStyle.setDataFormat(format.getFormat("@"));
//    }
//
//    /**
//     * 创建数据域（下拉联动的数据）
//     *
//     * @param workbook
//     * @param hideSheetName
//     *            数据域名称
//     */
//    private void creatHideSheet(HSSFWorkbook workbook, String hideSheetName) {
//        // 创建数据域
//        HSSFSheet sheet = workbook.createSheet(hideSheetName);
//        // 用于记录行
//        int rowRecord = 0;
//        // 获取行（从0下标开始）
//        HSSFRow provinceRow = sheet.createRow(rowRecord);
//        // 创建省份数据
//        this.creatRow(provinceRow, province);
//        // 根据省份插入对应的市信息
//        rowRecord++;
//        for (int i = 0; i < province.size(); i++) {
//            List<String> list = new ArrayList<String>();
//            // 我这里是写死的 ， 实际中应该从数据库直接获取更好
//            if (province.get(i).toString().equals("湖南")) {
//                // 将省份名称放在插入市的第一列， 这个在后面的名称管理中需要用到
//                list.add(0, province.get(i).toString());
//                list.addAll(hnCity);
//            } else {
//                list.add(0, province.get(i).toString());
//                list.addAll(gdCity);
//            }
//            //获取行
//            HSSFRow Cityrow = sheet.createRow(rowRecord);
//            // 创建省份数据
//            this.creatRow(Cityrow, list);
//            rowRecord++;
//
//        }
//        //workbook.setSheetHidden(workbook.getSheetIndex(hideSheetName),true);
//
//    }
//
//    /**
//     * 创建一列数据
//     *
//     * @param currentRow
//     */
//    public void creatRow(HSSFRow currentRow, List<String> text) {
//        if (text != null) {
//            int i = 0;
//            for (String cellValue : text) {
//                // 注意列是从（1）下标开始
//                HSSFCell userNameLableCell = currentRow.createCell(i++);
//                userNameLableCell.setCellValue(cellValue);
//            }
//        }
//    }
//
//    /**
//     * 名称管理
//     *
//     * @param workbook
//     * @param hideSheetName
//     *            数据域的sheet名
//     */
//    private void creatExcelNameList(HSSFWorkbook workbook, String hideSheetName) {
//        Name name;
//        name = workbook.createName();
//        // 设置省名称
//        name.setNameName("province");
//        name.setRefersToFormula(hideSheetName + "!$A$1:$"
//                + this.getcellColumnFlag(province.size())+ "$1");
//        // 设置省下面的市
//
//        for (int i = 0; i < province.size(); i++) {
//            List<String> num = new ArrayList<String>();
//            if (province.get(i).toString().equals("湖南")) {
//                name = workbook.createName();
//                num.add(0,province.get(i).toString());
//                num.addAll(hnCity);
//                name.setNameName(province.get(i).toString());
//                name.setRefersToFormula(hideSheetName + "!$B$" + (i + 2) + ":$"
//                            + this.getcellColumnFlag(num.size()) + "$" + (i + 2));
//            } else {
//                name = workbook.createName();
//                num.add(0,province.get(i).toString());
//                num.addAll(gdCity);
//                name.setNameName(province.get(i).toString());
//                name.setRefersToFormula(hideSheetName + "!$B$" + (i + 2) + ":$"
//                            + this.getcellColumnFlag(num.size()) + "$" + (i + 2));
//            }
//        }
//    }
//
//    // 根据数据值确定单元格位置（比如：28-AB）
//    private String getcellColumnFlag(int num) {
//        String columFiled = "";
//        int chuNum = 0;
//        int yuNum = 0;
//        if (num >= 1 && num <= 26) {
//            columFiled = this.doHandle(num);
//        } else {
//            chuNum = num / 26;
//            yuNum = num % 26;
//
//            columFiled += this.doHandle(chuNum);
//            columFiled += this.doHandle(yuNum);
//        }
//        return columFiled;
//    }
//
//    private String doHandle(final int num) {
//        String[] charArr = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
//                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
//                "W", "X", "Y", "Z" };
//        return charArr[num - 1].toString();
//    }
//
//    /**
//     * 使用已定义的数据源方式设置一个数据验证
//     *
//     * @param formulaString
//     * @param naturalRowIndex
//     * @param naturalColumnIndex
//     * @return
//     */
//    public DataValidation getDataValidationByFormula(String formulaString,
//            int naturalRowIndex, int naturalColumnIndex) {
//
//        // 加载下拉列表内容
//        DVConstraint constraint = DVConstraint
//                .createFormulaListConstraint(formulaString);
//        // 设置数据有效性加载在哪个单元格上。
//        // 四个参数分别是：起始行、终止行、起始列、终止列
//        int firstRow = naturalRowIndex;
//        int lastRow = naturalRowIndex;
//        int firstCol = naturalColumnIndex - 1;
//        int lastCol = naturalColumnIndex - 1;
//        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
//                lastRow, firstCol, lastCol);
//        // 数据有效性对象
//        DataValidation data_validation_list = new HSSFDataValidation(regions,
//                constraint);
//        return data_validation_list;
//    }
//
//    /**
//     * 创建一列数据
//     *
//     * @param hssfSheet
//     */
//    public void creatAppRow(HSSFSheet hssfSheet, int naturalRowIndex) {
//        // 获取行
//        HSSFRow hssfRow = hssfSheet.createRow(naturalRowIndex);
//
//        HSSFCell province = hssfRow.createCell(0);
//        province.setCellValue("");
//        province.setCellStyle(cellStyle);
//
//        HSSFCell City = hssfRow.createCell(1);
//        City.setCellValue("");
//        City.setCellStyle(cellStyle);
//
//        // 得到验证对象
//        DataValidation data_validation_list1 = this.getDataValidationByFormula(
//                "province", naturalRowIndex, 1);
//        DataValidation data_validation_list2 = this
//                .getDataValidationByFormula("INDIRECT($A"
//                        + (naturalRowIndex + 1) + ")", naturalRowIndex, 2);
//        // 工作表添加验证数据
//        hssfSheet.addValidationData(data_validation_list1);
//        hssfSheet.addValidationData(data_validation_list2);
//    }
//
//    public void Export() {
//        try {
//            File file = new File("c:/excel.xls");
//            FileOutputStream outputStream = new FileOutputStream(file);
//            // 创建excel
//            HSSFWorkbook workbook = new HSSFWorkbook();
//            // 设置sheet 名称
//            HSSFSheet excelSheet = workbook.createSheet("excel");
//            // 设置样式
//            this.setDataCellStyles(workbook, excelSheet);
//            // 创建一个隐藏页和隐藏数据集
//            this.creatHideSheet(workbook, "shutDataSource");
//            // 设置名称数据集
//            this.creatExcelNameList(workbook, "shutDataSource");
//            // 创建一行数据
//            for (int i = 0; i < 50; i++) {
//                this.creatAppRow(excelSheet,i);
//
//            }
//            workbook.write(outputStream);
//            outputStream.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//        ExcelLinkage linkage = new ExcelLinkage();
//        linkage.Export();
//    }
//}