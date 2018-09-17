package excel;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import org.junit.Test;

import java.io.File;
import java.util.List;

/**
 * Created by gwb on 2018-09-13.
 */
public class EasyPOITest {

    @Test
    public void testReadExcel() {
        ImportParams params = new ImportParams();
        //params.setTitleRows(2);
        //params.setHeadRows(2);
        //params.setSheetNum(9);
        params.setNeedSave(true);
        long st = System.currentTimeMillis();
        List list = ExcelImportUtil.importExcel(new File("c:\\3test.xlsx"), GoodsExcelVo.class, params);

        System.out.println(System.currentTimeMillis() - st);
    }


}
