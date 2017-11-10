package model.L1decorator;

//滚动条Decorator
class ScrollDecorator extends Decorator {
    public ScrollDecorator(VisualComponent component) {
        super(component);
    }

    public void draw() {
        super.draw();
        scrollTo();
    }

    public void scrollTo() {
        System.out.println("TextArea scroll to…");
    }
}  