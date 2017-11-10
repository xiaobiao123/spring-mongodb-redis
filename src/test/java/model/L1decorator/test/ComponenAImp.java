package model.L1decorator.test;

/**
 * Created by gwb on 2017/6/14.
 */
public class ComponenAImp extends AbsComponent {
    public ComponenAImp(IComponent component) {
        super(component);
    }

    public void draw() {
        super.draw();
        doSomething();
    }

    private void doSomething() {
        System.out.println("i want to do something............ for a");
    }
}
