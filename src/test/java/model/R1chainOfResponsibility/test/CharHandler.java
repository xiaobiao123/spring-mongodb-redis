package model.R1chainOfResponsibility.test;

/**
 * Created by Administrator on 2017/6/15.
 */
public class CharHandler extends HandlerTest {


    public CharHandler() {

    }

    public void handler(Type type) {
        if (type.getType().equals("123")) {
            System.out.println("xxxxxxxxxxxxxxxx");
        } else {
            getHandlerTest().handler(type);
        }
    }
}
