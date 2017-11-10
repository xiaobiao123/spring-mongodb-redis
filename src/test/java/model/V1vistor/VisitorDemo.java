package model.V1vistor;

/**
 * Visitor访问者设计模式是在不修改已有程序结构的前提下，通过添加额外的“访问者”来完成对已有代码功能的提升。
 * Visitor访问者设计模式的角色：
 * (1) 访问者角色（Visitor）：声明一个访问接口。接口的名称和方法的参数标识了向访问者发送请求的元素角色。
 * 这样访问者就可以通过该元素角色的特定接口直接访问它。
 * (2) 具体访问者角色（Concrete Visitor）：实现访问者角色（Visitor）接口
 * (3) 元素角色（Element）：定义一个Accept操作，它以一个访问者为参数。
 * (4) 具体元素角色（Concrete Element）：实现元素角色（Element）接口。
 * (5) 对象结构角色（Object Structure）：具体元素的集合，提供一个高层的接口允许访问者角色访问它的元素。
 * <p>
 * 表示一个作用于某对象结构中的各元素的操作。它使你可以在不改变各元素类的前提下定义作用于这些元素的新操作。
 * <p>
 * (1).对元素的访问不是访问者主动发起的，而是通过元素接收访问者来访问自己。
 * (2).对元素的操作不是元素自己主动调用，而是通过访问者的访问方法来操作元素。
 * <p>
 * JDK中访问者模式的应用：
 * •javax.lang.model.element.Element和javax.lang.model.element.ElementVisitor
 * •javax.lang.model.type.TypeMirror和javax.lang.model.type.TypeVisitor
 * •javax.lang.model.element.AnnotationValue和javax.lang.model.element.AnnotationValueVisitor
 */
public class VisitorDemo {
    public static void main(String[] args) {
        IElement[] list = {new ElementA(), new ElementB(), new ElementC()};
        IVisitor visitor = new MyVisitor();
        for (int i = 0; i < list.length; i++) {
            list[i].accept(visitor);
        }
    }
}  