package model.decorator.test;

/**
 * Created by gwb on 2017/6/14.
 */
abstract class AbsComponent implements IComponent {

    private IComponent iComponent;

    public AbsComponent(IComponent component) {
        this.iComponent = component;
    }

    public void draw() {
        this.iComponent.draw();
    }

}
