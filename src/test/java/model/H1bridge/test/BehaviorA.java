package model.H1bridge.test;

/**
 * Created by Administrator on 2017/6/14.
 */
public class BehaviorA implements IBehavior {
    private IAction action;

    public BehaviorA(IAction action) {
        this.action = action;
    }

    @Override
    public void having() {
        action.doSomething();
    }
}
