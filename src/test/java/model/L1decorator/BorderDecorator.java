package model.L1decorator;

//边框Decorator
class BorderDecorator extends Decorator {
    public BorderDecorator(VisualComponent component) {
        super(component);
    }

    public void draw() {
        super.draw();
        drawBorder();
    }

    public void drawBorder() {
        System.out.println("Draw border for TextArea …");
    }
}  