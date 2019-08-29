package com.cn.excel.poi;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * https://www.cnblogs.com/LiZhiW/p/4313789.html
 */
public class Test00 {
    private static String filePath = "d:/com.cn.excel/sample.xlsx";//文件路径

    private XSSFWorkbook workbook = null;
    private XSSFSheet    sheet    = null;


    @Before
    public void testBefore() {
        workbook = new XSSFWorkbook();
        sheet = workbook.createSheet();//创建工作表(Sheet)
    }

    @After
    public void testAfter() throws Exception {
        FileOutputStream out = new FileOutputStream(filePath);
        workbook.write(out);//保存Excel文件
        out.close();//关闭文件流
        System.out.println("OK!");
    }


    /**
     * 创建Workbook和Sheet
     *
     * @throws Exception
     */
    @Test
    public void createSheet() throws Exception {
        workbook.createSheet("sheet2");
    }

    /**
     * 创建单元格
     *
     * @throws Exception
     */
    @Test
    public void testCreatecell() throws Exception {
        XSSFRow  row  = sheet.createRow(0);// 创建行,从0开始
        XSSFCell cell = row.createCell(0);// 创建行的单元格,也是从0开始
        cell.setCellValue("李志伟");// 设置单元格内容
        row.createCell(1).setCellValue(false);// 设置单元格内容,重载
        row.createCell(2).setCellValue(new Date());// 设置单元格内容,重载
        row.createCell(3).setCellValue(12.345);// 设置单元格内容,重载
    }

    /**
     * 设置格式
     */
    @Test
    public void createType() {

        XSSFRow row = sheet.createRow(0);
        //设置日期格式--使用Excel内嵌的格式
        XSSFCell cell = row.createCell(0);
        cell.setCellValue(new Date());
        XSSFCellStyle  style  = workbook.createCellStyle();
        XSSFDataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("yyyy年m月d日"));
        cell.setCellStyle(style);

        //设置保留2位小数--使用Excel内嵌的格式
        cell = row.createCell(1);
        cell.setCellValue(12.3456789);
        style = workbook.createCellStyle();
        style.setDataFormat(2);
        cell.setCellStyle(style);
        //设置货币格式--使用自定义的格式
        cell = row.createCell(2);
        cell.setCellValue(12345.6789);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("￥#,##0"));
        cell.setCellStyle(style);
        //设置百分比格式--使用自定义的格式
        cell = row.createCell(3);
        cell.setCellValue(0.123456789);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00%"));
        cell.setCellStyle(style);
        //设置中文大写格式--使用自定义的格式
        cell = row.createCell(4);
        cell.setCellValue(12345);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("[DbNum2][$-804]0"));
        cell.setCellStyle(style);
        //设置科学计数法格式--使用自定义的格式
        cell = row.createCell(5);
        cell.setCellValue(12345);
        style = workbook.createCellStyle();
        style.setDataFormat(workbook.createDataFormat().getFormat("0.00E+00"));
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue(12345);
    }

    /**
     * 合并单元格
     */
    @Test
    public void 合并单元格() {
        XSSFRow row = sheet.createRow(0);
        //合并列
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("合并列");
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(region);
        //合并行
        cell = row.createCell(6);
        cell.setCellValue("合并行");
        region = new CellRangeAddress(0, 5, 6, 6);
        sheet.addMergedRegion(region);
    }

    /**
     * 单元格对其
     * <p>
     * <p>
     * 水平对齐相关参数
     * 如果是左侧对齐就是   XSSFCellStyle.ALIGN_FILL;
     * 如果是居中对齐就是   XSSFCellStyle.ALIGN_CENTER;
     * 如果是右侧对齐就是   XSSFCellStyle.ALIGN_RIGHT;
     * 如果是跨列举中就是   XSSFCellStyle.ALIGN_CENTER_SELECTION;
     * 如果是两端对齐就是   XSSFCellStyle.ALIGN_JUSTIFY;
     * 如果是填充就是       XSSFCellStyle.ALIGN_FILL;
     * <p>
     * 垂直对齐相关参数
     * 如果是靠上就是     XSSFCellStyle.VERTICAL_TOP;
     * 如果是居中就是     XSSFCellStyle.VERTICAL_CENTER;
     * 如果是靠下就是     XSSFCellStyle.VERTICAL_BOTTOM;
     * 如果是两端对齐就是 XSSFCellStyle.VERTICAL_JUSTIFY;
     */
    @Test
    public void 单元格对齐() {
        XSSFRow  row  = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("单元格对齐");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);//水平居中
        style.setVerticalAlignment(VerticalAlignment.BOTTOM);//垂直居中
        style.setWrapText(true);//自动换行
        style.setIndention((short) 5);//缩进
        style.setRotation((short) 60);//文本旋转，这里的取值是从-90到90，而不是0-180度。
        cell.setCellStyle(style);
    }

    /**
     * 不起作用
     * <p>
     * 创建页眉和页脚
     */
    @Test
    public void 创建页眉和页脚() {
        Header header = sheet.getHeader();//得到页眉
        header.setLeft("页眉左边");
        header.setRight("页眉右边");
        header.setCenter("页眉中间");
        Footer footer = sheet.getFooter();//得到页脚
        footer.setLeft("页脚左边");
        footer.setRight("页脚右边");
        footer.setCenter("页脚中间");
    }

    /**
     * 使用边框
     * <p>
     * 边框和其他单元格设置一样也是调用CellStyle接口，CellStyle有2种和边框相关的属性，分别是:
     * 边框相关属性
     * <p>
     * 说明
     * <p>
     * 范例
     * <p>
     * Border+方向
     * <p>
     * 边框类型
     * <p>
     * BorderLeft, BorderRight等
     * <p>
     * 方向+BorderColor
     * <p>
     * 边框颜色
     * <p>
     * TopBorderColor,BottomBorderColor等
     */
    @Test
    public void 使用边框() {
        XSSFRow  row  = sheet.createRow(0);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("设置边框");
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);//上边框
        style.setBorderBottom(BorderStyle.DOUBLE);//下边框
        style.setBorderLeft(BorderStyle.DOUBLE);//左边框
        style.setBorderRight(BorderStyle.DOUBLE);//右边框
        style.setTopBorderColor(IndexedColors.BLACK.index);//上边框颜色
        style.setBottomBorderColor(IndexedColors.BLUE.index);//下边框颜色
        style.setLeftBorderColor(IndexedColors.GREEN.index);//左边框颜色
        style.setRightBorderColor(IndexedColors.PINK.index);//右边框颜色
        cell.setCellStyle(style);
    }

    /**
     * 设置字体
     * <p>
     * 下划线选项值：
     * 单下划线 FontFormatting.U_SINGLE
     * 双下划线 FontFormatting.U_DOUBLE
     * 会计用单下划线 FontFormatting.U_SINGLE_ACCOUNTING
     * 会计用双下划线 FontFormatting.U_DOUBLE_ACCOUNTING
     * 无下划线 FontFormatting.U_NONE
     * 上标下标选项值：
     * 上标 FontFormatting.SS_SUPER
     * 下标 FontFormatting.SS_SUB
     * 普通，默认值 FontFormatting.SS_NONE
     */
    @Test
    public void 设置字体() {
        XSSFRow  row  = sheet.createRow(0);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("设置字体");
        XSSFCellStyle style = workbook.createCellStyle();
        XSSFFont      font  = workbook.createFont();
        font.setFontName("华文行楷");//设置字体名称
        font.setFontHeightInPoints((short) 28);//设置字号
        font.setColor(Font.COLOR_RED);//设置字体颜色
        font.setUnderline(FontFormatting.U_SINGLE);//设置下划线
        font.setTypeOffset(FontFormatting.SS_SUPER);//设置上标下标
        font.setStrikeout(true);//设置删除线
        style.setFont(font);
        cell.setCellStyle(style);
    }

    /**
     * 背景和纹理
     */
    @Test
    public void 背景和纹理() {
        XSSFRow       row   = sheet.createRow(0);
        XSSFCell      cell  = row.createCell(1);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(IndexedColors.PINK1.index);//设置图案颜色
        style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.index);//设置图案背景色
        style.setFillPattern(FillPatternType.BRICKS);//设置图案样式
        cell.setCellStyle(style);
    }

    @Test
    public void 设置高度宽度() {
        XSSFRow  row  = sheet.createRow(1);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue("123456789012345678901234567890");
        sheet.setColumnWidth(1, 31 * 256);//设置第一列的宽度是31个字符宽度
        row.setHeightInPoints(50);//设置行的高度是50个点

    }

    @Test
    public void 判断单元格是否为日期() {
        XSSFRow  row  = sheet.createRow(0);
        XSSFCell cell = row.createCell(1);
        cell.setCellValue(new Date());//设置日期数据
        System.out.println(DateUtil.isCellDateFormatted(cell));//输出：false
        XSSFCellStyle style = workbook.createCellStyle();

        XSSFDataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("yyyy年m月d日"));

        cell.setCellStyle(style);//设置日期样式
        System.out.println(DateUtil.isCellDateFormatted(cell));//输出：true
    }

    @Test
    public void 遍历sheet() throws IOException {
        String          filePath = "D:\\com.cn.excel\\big.xlsx";
        FileInputStream stream   = new FileInputStream(filePath);
        XSSFWorkbook    workbook = new XSSFWorkbook(stream);//读取现有的Excel
        workbook.getNumberOfSheets();
        workbook.getSheetAt(0);
        XSSFSheet sheet = workbook.getSheetAt(0);//得到指定名称的Sheet
        for (Row row : sheet) {
            for (Cell cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 下面对CreateFreezePane的参数作一下说明：
     * 第一个参数表示要冻结的列数；
     * 第二个参数表示要冻结的行数，这里只冻结列所以为0；
     * 第三个参数表示右边区域可见的首列序号，从1开始计算；
     * 第四个参数表示下边区域可见的首行序号，也是从1开始计算，这里是冻结列，所以为0；
     */
    @Test
    public void 锁定列() {
        sheet.createFreezePane(2, 3, 15, 25);//冻结行列
    }

    @Test
    public void 数据有效性() {
        XSSFRow  row  = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("日期列");
        CellRangeAddressList regions = new CellRangeAddressList(1, 65535, 0, 0);//选定一个区域

        String[] datas = new String[]{"21993-01-01", "2014-12-31"};

        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);

        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(datas);
        XSSFDataValidation           validation   = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        validation.createErrorBox("错误", "你必须输入一个时间！");
        sheet.addValidationData(validation);
    }


    @Test
    public void 生成下拉() {
        XSSFRow  row  = sheet.createRow(0);
        XSSFCell cell = row.createCell(0);
        cell.setCellValue("生成下拉");


        CellRangeAddressList         regions      = new CellRangeAddressList(0, 65535, 0, 0);//选定一个区域
        String[]                     datas        = new String[]{"java", "mysql", "oracle"};
        XSSFDataValidationHelper     dvHelper     = new XSSFDataValidationHelper(sheet);
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createExplicitListConstraint(datas);
        XSSFDataValidation           validation   = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        validation.setShowErrorBox(true);
        validation.setSuppressDropDownArrow(true);
        validation.createErrorBox("错误", "你必须从下拉选择！");
        sheet.addValidationData(validation);
    }
}