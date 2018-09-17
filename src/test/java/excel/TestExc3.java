package excel;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Name;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by gwb on 2018-09-07.
 */
public class TestExc3 {
    @Test
    public void export() {
        HttpServletResponse response = new MockHttpServletResponse();
        //模拟从数据库获取需要导出的数据
        List<Person> personList = new ArrayList<>();
        Person person1 = new Person("路飞", "1", new Date());
        Person person2 = new Person("娜美", "2", new Date());
        Person person3 = new Person("索隆", "1", new Date());
        Person person4 = new Person("小狸猫", "1", new Date());
        personList.add(person1);
        personList.add(person2);
        personList.add(person3);
        personList.add(person4);

        //导出操作
        FileUtil.exportExcel(personList, "花名册", "草帽一伙", Person.class, "c:\\海贼王.xls", response);


    }

    @Test
    public void importExcel() {
        String filePath = "F:\\海贼王.xls";
        //解析excel，
        List<Person> personList = FileUtil.importExcel(filePath, 1, 1, Person.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【" + personList.size() + "】行");

        //TODO 保存数据库
    }

    @Test
    public void imp() throws Exception {
        List<Person> list = new ArrayList<Person>();
        int count1 = 0;
        Person person1 = new Person("路飞", "1", new Date());
        Person person2 = new Person("娜美", "2", new Date());
        Person person3 = new Person("索隆", "1", new Date());
        Person person4 = new Person("小狸猫", "1", new Date());

        list.add(person4);
        list.add(person4);

        // 获取导出excel指定模版
        TemplateExportParams params = new TemplateExportParams();
        // 标题开始行
        params.setHeadingStartRow(0);
        // 标题行数
        params.setHeadingRows(2);
        // 设置sheetName，若不设置该参数，则使用得原本得sheet名称
        params.setSheetName("班级信息");

        params.setHeadingRows(2);
        params.setHeadingStartRow(2);
        params.setTempParams("t");
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("list", list);

        OutputStream outputStream = new FileOutputStream("c:\\easypoi-excel.xlsx");
        Workbook book = ExcelUtil.getWorkbook(params, data, "c:\\easypoi-excel.xlsx");
        //File f1 = new File("C:\\abc.txt");
        //f1.createNewFile();


        //File file = File.createTempFile("c:\\easypoi-excel惺惺惜惺惺想", "xlsx");
        //File.createTempFile("abc", ".txt",f1);
        //book.write(outputStream);
        System.out.println("zxxxxxx");
        //下载

        //ExcelUtil.export(response, book, "easypoi-excel.xls");
    }


    @Test
    public void bigDataExport() throws Exception {

        List<Person> list = new ArrayList<Person>();
        Workbook workbook = null;
        Date start = new Date();
        ExportParams params = new ExportParams("大数据测试", "测试");
        //for (int i = 0; i < 100; i++) {  //一百万数据量
        Person person1 = new Person("路飞", "1", new Date(), 2);
        Person person2 = new Person("娜美", "2", new Date(), 3);
        Person person3 = new Person("索隆", "1", new Date(), 4);
        Person person4 = new Person("小狸猫", "1", new Date(), 5);
        list.add(person3);
        //if (list.size() == 10000) {
        workbook = ExcelExportUtil.exportBigExcel(params, Person.class, list);

        //Sheet sheet= workbook.getSheet("测试");
        //CellStyle cellStyle = workbook.createCellStyle();
        //Name name = workbook.createName();
        list.clear();
        //}
        //}
        ExcelExportUtil.closeExportBigExcel();
        System.out.println(new Date().getTime() - start.getTime());
        File savefile = new File("D:/excel/");
        if (!savefile.exists()) {
            savefile.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream("D://big.xlsx");
        workbook.write(fos);
        fos.close();
        ImportParams params2 = new ImportParams();
        params2.setTitleRows(1);
        params2.setHeadRows(1);
        List<Object> objects = ExcelImportUtil.importExcel(new File("D://big.xlsx"), Person.class, params2);
        System.out.println(JSONObject.toJSONString(objects));

    }


    public static void main(String[] args) throws Exception {
        FileOutputStream out = null;

        out = new FileOutputStream("/tmp/java.txt");
        out.write("还是   12313123".getBytes("utf-8"));
        out.close();


        FileInputStream in = null;
        in = new FileInputStream("/tmp/java.txt");

        int len = 0;
        byte[] buf = new byte[4096];
        while ((len = in.read(buf)) != -1) {
            System.out.println(new String(buf, 0, len));
        }
        in.close();

    }

}
