package model.chainOfResponsibility.test;

/**
 * Created by Administrator on 2017/6/15.
 */
abstract class HandlerTest {

    private HandlerTest handlerTest;

    public HandlerTest(HandlerTest handlerTest) {
        this.handlerTest = handlerTest;
    }

    public HandlerTest() {
    }

    public HandlerTest getHandlerTest() {
        return handlerTest;
    }

    public void setHandlerTest(HandlerTest handlerTest) {
        this.handlerTest = handlerTest;
    }

    public void handler(Type type) {

    }
}
