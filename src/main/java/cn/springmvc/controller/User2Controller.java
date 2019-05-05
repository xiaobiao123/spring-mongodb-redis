package cn.springmvc.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * Created by gwb on 2018-08-27.
 */
public class User2Controller {


    /**
     *
     * 2018-4-19
     */
    private static final long serialVersionUID = 1L;

    private static final String APPID = "wx4ce50a50243b92ee";
    private static final String SECRET = "c566ca7234916fc1aa59721b243815ce";
    private String code;

    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }

    //获取凭证校检接口
    public String login() {
        //微信的接口
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + APPID +
                "&secret=" + SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        //进行网络请求,访问url接口

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        //根据返回值进行后续操作
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            System.out.println(JSONObject.toJSONString(sessionData));
            //Gson gson = new Gson();
            ////解析从微信服务器获得的openid和session_key;
            //WeChatSession weChatSession = gson.fromJson(sessionData,WeChatSession.class);
            ////获取用户的唯一标识
            //String openid = weChatSession.getOpenid();
            ////获取会话秘钥
            //String session_key = weChatSession.getSession_key();
            //下面就可以写自己的业务代码了
            //最后要返回一个自定义的登录态,用来做后续数据传输的验证
        }

        return null;

    }

    public static void main(String[] args) {
        User2Controller user2Controller = new User2Controller();
        user2Controller.login();
    }
}
