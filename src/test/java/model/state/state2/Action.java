package model.state.state2;

/**
 * Created by Administrator on 2017/6/8.
 */
public class Action {

    private IState state;

    public IState getState() {
        return state;
    }

    public Action(IState state){
        this.state=state;
    }

    public void setState(IState state) {
        this.state = state;
    }

    public void getResult(){
        state.handle(this);
    }
}
