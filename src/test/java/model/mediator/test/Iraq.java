package model.mediator.test;

/**
 * Created by Administrator on 2017/6/7.
 */
public class Iraq extends Country {
    public Iraq(UnitedNations mediator) {
        super(mediator);
    }

    public void declare(String message) {
        mediator.declare(message, this);
    }

    public void getDeclare(String message) {
        System.out.println("伊拉克获取对方的信息:" + message);
    }
}
