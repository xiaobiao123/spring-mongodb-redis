package model.mediator;

/**
 * 用一个中介者对象来封装一系列的对象交互。中介者使得各对象不需要显式地相互引用，
 * 从而使其松散耦合，而且可以独立地改变它们之间的交互。
 */
public class MediatorDemo {
    public static void main(String[] args) {
        //创建中介
        Mediator mediator = new ConcreteMediator();
        //创建同事，并为同事设置中介
        Person personA = new PersonA(mediator);
        Person personB = new PersonB(mediator);


        //向中介设置同事
        mediator.setPersonA(personA);
        mediator.setPersonB(personB);

        //开始聊天
        personA.send("Hi, B!");
        personB.send("Hello, A!");
    }
}   