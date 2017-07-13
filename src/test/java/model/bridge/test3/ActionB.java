package model.bridge.test3;

/**
 * Created by Administrator on 2017/7/3.
 */
public class ActionB implements IAction {
    @Override
    public void doSomething() {
        doActionB();
    }

    private void doActionB() {
        System.out.println("do action b.............");
    }
}
