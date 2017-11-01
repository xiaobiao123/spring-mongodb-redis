package model.H1bridge;

class Rectangle implements IShape {
    private IDrawing draw;

    public Rectangle(IDrawing draw) {
        this.draw = draw;
    }

    @Override
    public void doDraw() {
        System.out.println("i am rectangle...........");
        draw.draw();
    }
}  