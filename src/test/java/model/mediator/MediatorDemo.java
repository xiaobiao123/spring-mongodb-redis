package model.mediator;

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