package model.H8build.build2;


/**
 * Builder建造者模式将一个复杂对象的构建与它的表示分离，使得同样的构建过程可以创建不同的表示。Builder模式是一步一步创建一个复杂的对象，
 * 它允许用户可以只通过指定复杂对象的类型和内容就可以构建它们，用户不需要了解所构建对象的内部具体构建细节，
 * Builder建造设计模式的目的是为了将构造复杂对象的过程和它的部件解耦。
 * <p>
 * Director指导者和Builder建造者。Director指导者相当于设计师或架构师，
 * 拥有整个产品各个部件之间关系的构建蓝图。Builder建造者是部件的具体创建者，
 * Builder建造者根据Director指导者的指示创建产品的各个部件，最终由Director构建出完整产品
 * <p>
 * 1. builder：给出一个抽象接口，以规范产品对象的各个组成成分的建造。这个接口规定要实现复杂对象的哪些部分的创建，并不涉及具体的对象部件的创建。
 * 2. ConcreteBuilder：实现Builder接口，针对不同的商业逻辑，具体化复杂对象的各部分的创建。 在建造过程完成后，提供产品的实例。
 * 3. Director：调用具体建造者来创建复杂对象的各个部分，在指导者中不涉及具体产品的信息，只负责保证对象各部分完整创建或按某种顺序创建。
 * 4. Product：要创建的复杂对象。
 * <p>
 * 使用建造者模式的好处：
 * 1.使用建造者模式可以使客户端不必知道产品内部组成的细节。
 * 2.具体的建造者类之间是相互独立的，对系统的扩展非常有利。
 * 3.由于具体的建造者是独立的，因此可以对建造过程逐步细化，而不对其他的模块产生任何影响。
 * 使用建造模式的场合：
 * 1.创建一些复杂的对象时，这些对象的内部组成构件间的建造顺序是稳定的，但是对象的内部组成构件面临着复杂的变化。
 * 2.要创建的复杂对象的算法，独立于该对象的组成部分，也独立于组成部分的装配方法时
 */
//Director类
class Director {
    public Builder Construct(Builder builder) {
        builder.BuildHead();
        builder.BuildBody();
        builder.BuildHand();
        builder.BuildFeet();
        return builder;

    }

    public static void main(String[] args) {
        Director director = new Director();
        Builder b1 = new FatPersonBuilder();
        Builder b2 = new ThinPersonBuilder();

        director.Construct(b2);
        Product p1 = b2.GetResult();
        p1.Show();
        //return 0;
    }
};
 
