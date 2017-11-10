package model.R1chainOfResponsibility.test;

/**
 * Created by Administrator on 2017/6/15.
 */
public class Application {
    public static void main(String[] args) {
        CharHandler handler = new CharHandler();
        handler.setHandlerTest(handler);

        Type type = new Type();
        type.setType("123");
        handler.handler(type);

    }
}
