package model.bridge.test2;

/**
 * Created by Administrator on 2017/6/22.
 */
public class BehaviorA implements IBehavior {

    private IAction action;

    public BehaviorA(IAction action) {
        this.action = action;
    }


    @Override
    public void behavior() {

        System.out.println("first is behaviorA.........");
        action.action();

    }
}
