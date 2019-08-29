//package com.cn.excel.easy.poi.test2;
//
//import cn.afterturn.easypoi.com.cn.excel.ExcelExportUtil;
//import cn.afterturn.easypoi.com.cn.excel.entity.ExportParams;
//import cn.afterturn.easypoi.com.cn.excel.entity.enmus.ExcelType;
//import com.alibaba.fastjson.JSONArray;
//import com.google.common.collect.Lists;
//import org.apache.poi.ss.usermodel.Workbook;
//
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * @author gwb
// * @date 2019/7/16 15:38
// */
//public class Excel {
//    public String export() {
//
//        Workbook workBook = null;
//        try {
//            List<DeptUtil> exportList = Lists.newArrayList();
//            DeptUtil       deptUtil   = new DeptUtil();
//            deptUtil.setDeptName("test");
//            deptUtil.setId(10);
//            exportList.add(deptUtil);
//
//            System.err.println(JSONArray.toJSONString(exportList));
//
//            // 创建参数对象（用来设定excel得sheet得内容等信息）
//            ExportParams deptExportParams = new ExportParams();
//            // 设置sheet得名称
//            deptExportParams.setSheetName("员工报表1");
//            // 创建sheet1使用得map
//            Map<String, Object> deptExportMap = new HashMap<>();
//            // title的参数为ExportParams类型，目前仅仅在ExportParams中设置了sheetName
//            deptExportMap.put("title", deptExportParams);
//            // 模版导出对应得实体类型
//            deptExportMap.put("entity", DeptUtil.class);
//            // sheet中要填充得数据
//            deptExportMap.put("data", exportList);
//
//            ExportParams empExportParams = new ExportParams();
//            empExportParams.setSheetName("员工报表2");
//            // 创建sheet2使用得map
//            Map<String, Object> empExportMap = new HashMap<>();
//            empExportMap.put("title", empExportParams);
//            empExportMap.put("entity", DeptUtil.class);
//            empExportMap.put("data", exportList);
//
//            // 将sheet1、sheet2、sheet3使用得map进行包装
//            List<Map<String, Object>> sheetsList = new ArrayList<>();
//            sheetsList.add(deptExportMap);
//            sheetsList.add(empExportMap);
//            // 执行方法
//            workBook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
//            fileName = URLEncoder.encode("员工报表导出", "UTF-8");
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            workBook.write(outputStream);
//            outputStream.flush();
//            byte[] byteArray = outputStream.toByteArray();
//            excelStream = new ByteArrayInputStream(byteArray, 0, byteArray.length);
//            outputStream.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (workBook != null) {
//                try {
//                    workBook.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return "success";
//    }
//}
