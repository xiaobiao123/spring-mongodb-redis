package model.adapter;

/**
 * Created by gwb on 2017/6/7.
 * <p>
 * 通过组合关系的适配器
 */
public class AdapterApplication {
    public static void main(String[] args) {

        HybridShape shape = new HybridShape(new Square());
        shape.drawCircle();
        shape.drawSquare();
    }
}
