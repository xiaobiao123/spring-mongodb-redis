package model.state.state2;

/**
 * Created by Administrator on 2017/6/8.
 */
public class StateImp1 implements IState {
    @Override
    public void handle(Action action) {
        System.out.println("I am stateImp1");

        action.setState(new StateImp2());
    }
}
