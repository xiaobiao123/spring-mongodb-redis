package model.bridge.test3;

/**
 * Created by Administrator on 2017/7/3.
 */
public class BehaverA implements IBehave {
    private IAction iAction;

    public BehaverA(IAction iAction) {
        this.iAction = iAction;
    }

    @Override
    public void Behaver() {
        this.doAtion();
    }

    private void doAtion() {
        this.iAction.doSomething();
    }
}
