package model.bridge;

class Rectangle implements Shape {
    private Drawing draw;

    public Rectangle(Drawing draw) {
        this.draw = draw;
    }

    public void doDraw() {
        draw.draw();
    }
}  