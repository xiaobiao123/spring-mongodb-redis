package model.L1decorator.test3;

/**
 * Created by Administrator on 2017/6/19.
 */
public class TestA extends AbasTest {
    public TestA(IElement element) {
        super(element);
    }

    @Override
    public void doSomething() {
        System.out.println("testa is going to do something...........");
        this.element.action();
    }
}
