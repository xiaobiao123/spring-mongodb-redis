package model.J10adapter.ex;

/**
 * Created by gwb on 2017/6/7.
 * <p>
 * 通过继承关系的适配器
 */
public class ExAdapterApplication {

    public static void main(String[] args) {
        ICircle circle = new Cirecle();
        ISquare square = new Square();


        HybridShape shape = new HybridShape(square);
        shape.drawSquare();


        HybridShape cir = new HybridShape(circle);
        cir.drawCircle();
    }

}
