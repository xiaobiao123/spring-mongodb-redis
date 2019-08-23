package re;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.Set;

/**
 * 量房分页查询
 *
 * @author gwb
 * @date 2019/7/26 16:31
 */
@Data
public class PageRecordRequest  {

    private static final long    serialVersionUID = -9119968510921938002L;
    /**
     * 产品来源（H5、小程序、官网、APP、AIC（客服新增的客户）
     */
    private              Integer sourceType;
    /**
     * 推广渠道（自有渠道或外部渠道（如今日头条、杭州地铁等）
     */
    private              Integer channel;
    /**
     * 客户姓名
     */
    private              String  name;

    /**
     * 客户手机号
     */
    private String phone;

    /**
     * 楼盘名称
     */
    private String buildingName;

    /**
     * 楼盘省code
     */
    private String consumerProvinceCode;

    /**
     * 楼盘市code
     */
    private String consumerCityCode;

    /**
     * 楼盘区code
     */
    private String consumerDistrictCode;

    /**
     * 状态（0：待确认   1：获客有效   2：获客无效）
     */
    private Integer status;

    /**
     * 是否为新用户：1是，0否
     */
    private Integer isNewUser;

    /**
     * 经济人名称
     */
    private String  brokerName;
    /**
     * 经济手机号
     */
    private String  brokerPhone;
    /**
     * 经济人渠道
     */
    private Integer brokerChannel;

    /**
     * 活动类型：1免费量房，2免费设计
     */
    private Integer   type;
    /**
     * pageSize
     */
    private Integer   pageSize;
    /**
     * pageNo
     */
    private Integer   pageNo;
    /**
     * 经济人ids
     */
    private Set<Long> brokerIds;

}
