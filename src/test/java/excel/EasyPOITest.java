//package excel;
//
//import cn.afterturn.easypoi.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.excel.ExcelImportUtil;
//import cn.afterturn.easypoi.excel.entity.ExportParams;
//import cn.afterturn.easypoi.excel.entity.ImportParams;
//import org.apache.poi.ss.usermodel.*;
//import org.junit.Test;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
///**
// * Created by gwb on 2018-09-13.
// */
//public class EasyPOITest {
//
//    @Test
//    public void testReadExcel() {
//        ImportParams params = new ImportParams();
//        //params.setTitleRows(2);
//        //params.setHeadRows(2);
//        //params.setSheetNum(9);
//        params.setNeedSave(true);
//        long st = System.currentTimeMillis();
//        List list = ExcelImportUtil.importExcel(new File("c:\\3test.xlsx"), GoodsExcelVo.class, params);
//
//        System.out.println(System.currentTimeMillis() - st);
//    }
//
//    @Test
//    public void bigDataExport() throws Exception {
//        List<Person> list = new ArrayList<Person>();
//        Workbook workbook = null;
//        Date start = new Date();
//        ExportParams params = new ExportParams("大数据测试", "测试");
//        //for (int i = 0; i < 100; i++) {  //一百万数据量
//        Person person1 = new Person("路飞", "1", new Date(), 2);
//        Person person2 = new Person("娜美", "2", new Date(), 3);
//        Person person3 = new Person("索隆", "1", new Date(), 4);
//        Person person4 = new Person("小狸猫", "1", new Date(), 5);
//        list.add(person3);
//        //if (list.size() == 10000) {
//        workbook = ExcelExportUtil.exportBigExcel(params, Person.class, list);
//
//        Sheet testSheet = workbook.createSheet("test");
//        Row row = testSheet.createRow(1);
//        Cell cell = row.createCell(0);
//        cell.setCellType(CellType.STRING);
//        cell.setCellValue("xxxxxxxxxxxx");
//
//
//        //ExportParams params2 = new ExportParams("大数据测试", "test");
//        //workbook = ExcelExportUtil.exportBigExcel(params2, Person.class, list);
//        //Sheet sheet= workbook.createSheet("测试3");
//        //Sheet sheet2= workbook.createSheet("测试2");
//        //CellStyle cellStyle = workbook.createCellStyle();
//        //Name name = workbook.createName();
//        list.clear();
//        //}
//        //}
//        ExcelExportUtil.closeExportBigExcel();
//        System.out.println(new Date().getTime() - start.getTime());
//        File savefile = new File("D:/excel/");
//        if (!savefile.exists()) {
//            savefile.mkdirs();
//        }
//        FileOutputStream fos = new FileOutputStream("D://big.xlsx");
//        workbook.write(fos);
//        workbook.close();
//        fos.close();
//    }
//}
