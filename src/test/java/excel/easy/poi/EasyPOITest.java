package excel.easy.poi;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.params.ExcelExportEntity;
import cn.afterturn.easypoi.excel.export.ExcelExportService;
import com.google.common.collect.Lists;
import excel.GoodsExcelVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class EasyPOITest {

    @Test
    public void testReadExcel() {
        ImportParams params = new ImportParams();
        //params.setTitleRows(2);
        //params.setHeadRows(2);
        //params.setSheetNum(9);
        params.setNeedSave(false);
        long st   = System.currentTimeMillis();
        List list = ExcelImportUtil.importExcel(new File("c:\\3test.xlsx"), GoodsExcelVo.class, params);

        System.out.println(System.currentTimeMillis() - st);
    }

    @Test
    public void bigDataExport() throws Exception {
        List<Person> list     = new ArrayList<Person>();
        List<Person> list3    = new ArrayList<Person>();
        Workbook     workbook = null;
        Date         start    = new Date();
        ExportParams params   = new ExportParams("大数据测试", "测试");
        //for (int i = 0; i < 100; i++) {  //一百万数据量
        Person person1 = new Person("路飞", "1", new Date(), 2);
        Person person2 = new Person("娜美", "2", new Date(), 3);
        Person person3 = new Person("索隆", "1", new Date(), 4);
        Person person4 = new Person("小狸猫", "1", new Date(), 5);
        list.add(person3);
        list.add(person1);
        list.add(person2);
        list.add(person4);

        list3.add(person3);
        list3.add(person1);
        list3.add(person2);

        ExportParams empExportParams = new ExportParams();
        empExportParams.setSheetName("员工报表2");
        empExportParams.setType(ExcelType.XSSF);
        // 创建sheet2使用得map
        Map<String, Object> empExportMap = new HashMap<>();
        empExportMap.put("title", empExportParams);
        empExportMap.put("entity", Person.class);
        empExportMap.put("data", list);

        List<Map<String, Object>> list2 = Lists.newArrayList();
        list2.add(empExportMap);


        ExportParams empExportParams2 = new ExportParams();
        empExportParams2.setSheetName("员工报表2xxxxx");
        empExportParams2.setType(ExcelType.XSSF);
        // 创建sheet2使用得map
        Map<String, Object> empExportMap2 = new HashMap<>();
        empExportMap2.put("title", empExportParams2);
        empExportMap2.put("entity", Person.class);
        empExportMap2.put("data", list3);
        list2.add(empExportMap2);

//        workbook = ExcelExportUtil.exportExcel(params, Person.class, list);

//        HSSF是指2007年以前的,XSSF是指2007年版本以上的
        workbook = ExcelExportUtil.exportExcel(list2, ExcelType.XSSF);
        list.clear();
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D://excel//big.xlsx");
        workbook.write(fos);
        workbook.close();
        fos.close();
    }


    @Test
    public void testMult() {
        try {
            List<Map<String, Object>> sheets = new ArrayList<Map<String, Object>>();

            for (int i = 0; i < 10; i++) {
                Map<String, Object> sheet = new HashMap<String, Object>();

                List<ExcelExportEntity> entity = new ArrayList<ExcelExportEntity>();//构造对象等同于@Excel

                entity.add(new ExcelExportEntity("性别", "sex"));
                entity.add(new ExcelExportEntity("姓名", "name"));

                List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
                Map<String, Object>       h1   = new HashMap<String, Object>();
                h1.put("name", "name" + i);
                h1.put("sex", "sex" + i);
                Map<String, Object> h2 = new HashMap<String, Object>();
                h2.put("name", "name" + i + i);
                h2.put("sex", "sex" + i + i);
                list.add(h1);
                list.add(h2);


                sheet.put(NormalExcelConstants.CLASS, ExcelExportEntity.class);
                sheet.put(NormalExcelConstants.DATA_LIST, list);
                ExportParams exportParams = new ExportParams(null, "sheet" + i);
                exportParams.setType(ExcelType.XSSF);
                sheet.put(NormalExcelConstants.PARAMS, exportParams);
                sheet.put(NormalExcelConstants.MAP_LIST, entity);
                sheets.add(sheet);
            }
//            HSSF是指2007年以前的,XSSF是指2007年版本以上的
            XSSFWorkbook workbook = new XSSFWorkbook();

            for (Map<String, Object> map : sheets) {
                ExcelExportService server = new ExcelExportService();
                ExportParams       param  = (ExportParams) map.get("params");
                @SuppressWarnings("unchecked")
                List<ExcelExportEntity> entity = (List<ExcelExportEntity>) map.get("mapList");
                Collection<?> data = (Collection<?>) map.get("data");
                server.createSheetForMap(workbook, param, entity, data);
            }

            FileOutputStream fos = new FileOutputStream("D:/excel/ExcelExportForMap.tt.xlsx");
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
