package model.G7prototype;//具体原型类


/**
 * 用原型实例指定创建对象的种类，并通过拷贝这些原型创建新的对象
 * <p>
 * clone()方法是在Object中定义的,而且是protected的，只有实现了Cloneable接口的类才
 * 可以在其实例上调用clone()方法，否则会抛出CloneNotSupportException。
 * 为了获取对象的一份拷贝，我们可以利用Object类的clone()方法，也可以实现Cloneable接口，
 * 覆盖基类的clone()方法，在clone()方法中，调用super.clone()。
 * <p>
 * 克隆只是实现Prototype原型模式的一种方法，也可以直接通过new对象的方式创建原型实例，二者区别在于：
 * (1).通过new方式创建的原型和现存的实例对象没有任何联系。
 * (2).通过克隆方法创建的原型，虽然也是创建新对象，但是将原型实例对象的数据复制到了新的对象中，相当于使用被克隆对象的数据作为克隆对象的初始数据。
 * <p>
 * Prototype原型设计模式和Singleton单类设计模式是相对的，单类模式中，在整个程序生命周期里，
 * 单类模式类的实例对象只有一个。而Prototype原型设计模式则正好相反，每次都返回的是原型类的不同实例对象。
 * JDK中原型模式的应用：
 * java.lang.Object#clone()
 * java.lang.Cloneable
 */
public class Circle extends Shape {

    public Circle() {
        setShapeName("Circle shape");
    }

    public static void main(String[] args) {
        Shape shape = new Circle();
        System.out.println(shape.getShapeName());
        //通过clone()方法获得一个对象拷贝
        Shape shape2 = (Shape) shape.clone();


        System.out.println(shape2.getShapeName());
        shape.setShapeName("xxxxxxxxxxxxxx");
        System.out.println(shape.getShapeName());

    }


}