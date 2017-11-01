package model.H1bridge.test;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Cilent {
    public static void main(String[] args) {
        IBehavior behavior=new BehaviorA(new ActionA());

        behavior.having();

        behavior=new BehaviorA(new ActionB());

        behavior.having();

        System.out.println("====================");

        IBehavior behavior1=new BehaviorB( new ActionA());
        behavior1.having();
        behavior1=new BehaviorB(new ActionB());
        behavior1.having();
    }
}
