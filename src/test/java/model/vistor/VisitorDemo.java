package model.vistor;

/**
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