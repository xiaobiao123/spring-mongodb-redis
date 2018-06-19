package model.Q1observer.SubjectA;

/**
 * Created by gwb on 2018/6/15.
 * <p>
 * 被观察者具体实现
 */
public class SubjectAImp extends SubjectA {

    public void dosometings() {
        System.out.println("i want do somethings");
        super.notifyObservers();
    }
}
