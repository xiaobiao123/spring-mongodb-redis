package model.bridge;

class Circle implements Shape {
    private Drawing draw;

    public Circle(Drawing draw) {
        this.draw = draw;
    }

    public void doDraw() {
        draw.draw();
    }
}