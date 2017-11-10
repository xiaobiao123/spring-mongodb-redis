package model.M1bridge.test2;

/**
 * Created by Administrator on 2017/6/22.
 */
public class Client {
    public static void main(String[] args) {
        IAction action=new ActionA();

        IBehavior behavior=new BehaviorA(action);
        behavior.behavior();

    }
}
