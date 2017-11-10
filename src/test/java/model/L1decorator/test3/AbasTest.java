package model.L1decorator.test3;

/**
 * Created by Administrator on 2017/6/19.
 */
abstract class AbasTest {

    public IElement element;

    public AbasTest(IElement element) {
        this.element = element;
    }

    public abstract void doSomething();

}
