package model.adapter;


//既可以画圆形，又可以画方形，适配器
public class HybridShape extends Cirecle {
    private Square square;

    public HybridShape(Square square) {
        this.square = square;
    }

    public void drawSquare() {
        square.drawSquare();
    }
}   