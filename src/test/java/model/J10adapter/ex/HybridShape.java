package model.J10adapter.ex;

//既可以画圆形，又可以画方形，适配器
public class HybridShape implements ICircle, ISquare {
    private ISquare square;
    private ICircle circle;

    public HybridShape(ISquare square) {
        this.square = square;
    }

    public HybridShape(ICircle circle) {
        this.circle = circle;
    }

    public void drawSquare() {
        square.drawSquare();
    }

    public void drawCircle() {
        circle.drawCircle();
    }
}   