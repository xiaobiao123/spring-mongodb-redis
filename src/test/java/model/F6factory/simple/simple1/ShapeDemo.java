package model.F6factory.simple.simple1;

public class ShapeDemo {
    public static void draw(Shape shape) {
        shape.draw();
    }

    public static void main(String[] args) throws Exception {
        draw(ShapeFactory.createShape("model.F6factory.simple.simple1.Circle"));
        draw(ShapeFactory.createShape("model.F6factory.simple.simple1.Rectangle"));
    }
}  