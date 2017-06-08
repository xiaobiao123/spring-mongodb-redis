package model.bridge;

/**
 * Bridge桥接设计模式是将一组功能(实现)与另一组使用该功能的其他对象(行为)分离开来,以便达到单一因素变化，
 * 然后使用对象调用的方式将这两组关联系起来，将问题的行为和实现分离开来实现，通过用聚合代替继承来解决子类爆炸性增长的问题
 */
public class BridgeDemo {
    public static void main(String[] args) {
        //画一个实线的圆
        Drawing draw1 = new SolidDrawing();
        Shape shape1 = new Circle(draw1);
        shape1.doDraw();
        //画一个虚线的矩形
        Drawing draw2 = new DashDrawing();
        Shape shape2 = new Rectangle(draw2);
        shape2.doDraw();
    }
}  