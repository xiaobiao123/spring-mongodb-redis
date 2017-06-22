package model.decorator.test;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Client {
    public static void main(String[] args) {
        IComponent component=new ClassC();

        component.draw();
        System.out.println(".....................................");
        AbsComponent bImp=new ComponentBImp(component);
        bImp.draw();
        System.out.println("===========================================");
        bImp=new ComponenAImp(component);
        bImp.draw();
    }
}
