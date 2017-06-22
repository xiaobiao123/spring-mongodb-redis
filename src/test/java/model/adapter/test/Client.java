package model.adapter.test;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Client {
    public static void main(String[] args) {
        ClassV classC = new ClassV(new ClassAA());


        classC.doClassV();
        classC.bDoSomething();
    }
}
