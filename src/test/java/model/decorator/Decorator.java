package model.decorator;

//抽象Decorator
abstract class Decorator implements VisualComponent {
    protected VisualComponent component;

    public Decorator(VisualComponent component) {
        this.component = component;
    }

    public void draw() {
        component.draw();
    }
}  