package model.templateMethod;

/**
 * 举个例子，以准备去学校所要做的工作（prepareGotoSchool）为例，假设需要分三步：
 * 穿衣服（dressUp），吃早饭（eatBreakfast），带上东西（takeThings）。
 * 学生和老师要做得具体事情肯定有所区别。
 * 抽象类AbstractClass
 */


public class Client {
    public static void main(String[] args) {
        Student student = new Student();
        student.prepareGotoSchool();

        Teacher teacher = new Teacher();
        teacher.prepareGotoSchool();
    }
    /**
     * 优点
     * 模板方法模式通过把不变的行为搬移到超类，去除了子类中的重复代码。
     * 子类实现算法的某些细节，有助于算法的扩展。
     * 通过一个父类调用子类实现的操作，通过子类扩展增加新的行为，符合“开放-封闭原则”。
     * <p>
     * 缺点
     * 每个不同的实现都需要定义一个子类，这会导致类的个数的增加，设计更加抽象。
     * <p>
     * 适用场景
     * 在某些类的算法中，用了相同的方法，造成代码的重复。
     * 控制子类扩展，子类必须遵守算法规则。
     */
}  