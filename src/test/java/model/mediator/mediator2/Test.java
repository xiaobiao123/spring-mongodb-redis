package model.mediator.mediator2;

/**
 * User类统一接口，User1和User2分别是不同的对象，二者之间有关联，如果不采用中介者模式，
 * 则需要二者相互持有引用，这样二者的耦合度很高，为了解耦，引入了Mediator类，提供统一接口，
 * MyMediator为其实现类，里面持有User1和User2的实例，用来实现对User1和User2的控制。
 * 这样User1和User2两个对象相互独立，他们只需要保持好和Mediator之间的关系就行，剩下的全由
 * MyMediator类来维护！基本实现：
 */
public class Test {

    public static void main(String[] args) {
        Mediator mediator = new MyMediator();
        mediator.createMediator();
        mediator.workAll();
    }
}  