package model.Q1observer.SubjectA;

/**
 * Created by gwb on 2018/6/15.
 */
public class TestClinet {
    public static void main(String[] args) {
        SubjectAImp subjectA=new SubjectAImp();

        ObserverA observerA=new OneObserverA();

        subjectA.addObserverA(observerA);

        subjectA.dosometings();

    }
}
