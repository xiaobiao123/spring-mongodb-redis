package model.J1adapter.test;

/**
 * Created by Administrator on 2017/6/22.
 */
public class ClassV extends ClassB {

    private ClassAA aa;

    public ClassV(ClassAA aa) {
        this.aa = aa;
    }

    public void doClassV() {
        System.out.println("class v............");
        this.aa.doSomething();
    }
}
