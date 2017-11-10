package model.L1decorator.test;

/**
 * Created by Administrator on 2017/6/14.
 */
public class ComponentBImp extends AbsComponent {
    public ComponentBImp(IComponent component) {
        super(component);
    }

    public void draw() {
        super.draw();
        doSomething();
    }

    private void doSomething() {
        System.out.println("i want to do something for b.....................");
    }
}
