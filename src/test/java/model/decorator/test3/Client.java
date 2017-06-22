package model.decorator.test3;

import org.junit.Test;

/**
 * Created by Administrator on 2017/6/19.
 */
public class Client {
    public static void main(String[] args) {
        IElement element = new ElementA();

        AbasTest test = new TestA(element);

        test.doSomething();
    }
}
