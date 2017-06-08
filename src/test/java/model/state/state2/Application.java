package model.state.state2;


/**
 * Created by gwb on 2017/6/8.
 */
public class Application {
    public static void main(String[] args) {

        Action action = new Action(new StateImp2());
        action.getResult();
        action.getResult();
    }
}
