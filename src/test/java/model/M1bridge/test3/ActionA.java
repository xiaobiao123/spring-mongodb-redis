package model.M1bridge.test3;

/**
 * Created by Administrator on 2017/7/3.
 */
public class ActionA implements IAction {
    @Override
    public void doSomething() {
        this.doAction();
    }

    public void doAction() {
        System.out.println("action A ..........");
    }
}
