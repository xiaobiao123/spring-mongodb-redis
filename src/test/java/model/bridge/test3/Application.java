package model.bridge.test3;

/**
 * Created by Administrator on 2017/7/3.
 */
public class Application  {
    public static void main(String[] args) {
        IAction iAction=new ActionA();

        IBehave behave=new BehaverA(iAction);
        behave.Behaver();



         behave=new BehaverA(new ActionB());
        behave.Behaver();
    }
}
