package com.cn.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import lombok.Data;

import java.io.Serializable;

/**
 * @program: gcs
 * @description: 货品excel导入，导出vo
 * @author: Mr.Guo
 * @create: 2018-08-15 15:09
 **/
@Data
@ExcelTarget("goodsExcelVo")
public class GoodsExcelVo implements Serializable {

    //货品名称
    @Excel(name = "货品名称", orderNum = "1")
    private String wareName;
    //货品编码
    @Excel(name = "货品编码", orderNum = "2")
    private String skuCode;
    //注册证名称
    @Excel(name = "注册证名称", orderNum = "3")
    private String registerName;
    /**
     * 规格型号
     */
    @Excel(name = "规格", orderNum = "4")
    private String modelSpec;
    /**
     * 型号
     */
    @Excel(name = "型号", orderNum = "5")
    private String spec;
    /**
     * 厂家货号
     */
    @Excel(name = "货号", orderNum = "6")
    private String articleNo;
    /**
     * 条形码
     */
    @Excel(name = "条形码", orderNum = "7")
    private String barcode;
    /**
     * 厂家
     */
    @Excel(name = "生产厂家", orderNum = "8")
    private String factoryName;
    /**
     * 生产地址(生产具体地址)
     */
    @Excel(name = "厂家地址", orderNum = "9")
    private String facAddr;// goods gsp
    /**
     * 品牌
     */
    @Excel(name = "品牌", orderNum = "10")
    private String brandName;

    @Excel(name = "基本单位", orderNum = "11")
    private String unitName;
    /**
     * 68分类id
     */
    @Excel(name = "器械68经营ID", orderNum = "12")
    private String category68Id;
    /**
     * 68分类名称
     */
    @Excel(name = "器械68经营范围", orderNum = "13")
    private String category68Name;

    /**
     * 状态：enabled-启用、disabled-停用
     */
    @Excel(name = "状态", orderNum = "14")
    private String status;

    /**
     * 是否需要要首营 1:是，0:否
     * 是否需要要首营
     */
    @Excel(name = "是否需要要首营", orderNum = "15")
    private String isFirstCheck;

    /**
     * 是否注册证商品：0-无需注册证、1-注册证商品、2-科研试剂
     */
    @Excel(name = "注册证类型", orderNum = "16")
    private String isRegisterGoods;

    @Excel(name = "注册证号", orderNum = "17")
    private String registerCode;

    /**
     * 开始日期
     */
    @Excel(name = "开始日期", orderNum = "18")
    private String registerStartDate; // GOODS GSP
    /**
     * 结束日期
     */
    @Excel(name = "结束日期", orderNum = "19")
    private String registerEndDate;// GOODS GSP
    @Excel(name = "存储条件", orderNum = "20")
    private String storageConditionsName;//存储条件
    @Excel(name = "温度范围", orderNum = "21")
    private String takeGoodsTemp;//  存储条件
    /**
     * 有效期
     */
    @Excel(name = "有效期", orderNum = "22")
    private String expiryDate; // goods extension
    /**
     * 近效期
     */
    @Excel(name = "近效期", orderNum = "23")
    private String nearlyEffective;// goods extension

    /**
     * 提前停售天数
     */
    @Excel(name = "提前停售天数", orderNum = "24")
    private String stopSellingDay;// goods extension

    /**
     * 养护方式名称
     */
    @Excel(name = "养护方式", orderNum = "25")
    private String curingWayName;
    /**
     * 成本方式名称
     */
    @Excel(name = "成本方式", orderNum = "26")
    private String costTypeName;
    /**
     * 出库规则
     */
    @Excel(name = "出库规则", orderNum = "27")
    private String exWarehouseRuleName;


    private String remark;

}
