package model.T1mediator.test;

/**
 * Created by Administrator on 2017/6/7.
 */
public class USA extends Country {

    public USA(UnitedNations mediator) {
        super(mediator);
    }

    public void declare(String message) {
        mediator.declare(message, this);
    }

    public void getDeclare(String message) {
        System.out.println("美国获取对方的信息:" + message);
    }
}
