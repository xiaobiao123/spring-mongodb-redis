package model.state.state2;

/**
 * Created by Administrator on 2017/6/8.
 */
public class StateImp2 implements IState {
    @Override
    public void handle(Action action) {
        System.out.println("I am is stateImp2");
        action.setState(new StateImp1());
    }
}
