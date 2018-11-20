package model.M12bridge;

class Circle implements IShape {
    private IDrawing draw;

    public Circle(IDrawing draw) {
        this.draw = draw;
    }
    @Override
    public void doDraw() {
        System.out.println("i am circle............");
        draw.draw();
    }
}