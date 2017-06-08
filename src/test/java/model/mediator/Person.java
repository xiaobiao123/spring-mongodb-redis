package model.mediator;

//抽象同事
abstract class Person {
    //同事和中介者打交道
    protected Mediator mediator;

    public Person(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void send(String s);

    public abstract void greeting(String msg);
}