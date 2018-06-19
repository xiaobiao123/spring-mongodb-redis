package model.J10adapter;

/**
 * Created by gwb on 2017/6/7.
 * <p>
 * Adapter适配器模式是将两个不兼容的类组合在一起使用。生活中笔记本电脑和手机等数码产品
 * 的充电器就是一个适配器，将家用220V的交流电转换为笔记本或手机正常工作所需的目标电压和
 * 电流。适配器起到一种转换和包装的作用
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
