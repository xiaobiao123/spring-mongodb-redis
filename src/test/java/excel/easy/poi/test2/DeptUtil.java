package excel.easy.poi.test2;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.util.List;

@ExcelTarget("deptUtil")
@Data
public class DeptUtil {

    @Excel(name = "部门编号", width = 30, needMerge = true)
    private Integer id;

    @Excel(name = "部门名称", width = 30, needMerge = true)
    private String deptName;

    @ExcelCollection(name = "员工信息")
    private List<EmpUtil> emps;
}