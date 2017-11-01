package model.H1bridge.test;

/**
 * Created by Administrator on 2017/6/14.
 */
public class BehaviorB implements IBehavior {

    private IAction action;

    public BehaviorB(IAction action) {
        this.action = action;
    }

    @Override
    public void having() {
        action.doSomething();
    }
}
