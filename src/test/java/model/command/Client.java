package model.command;

/**
 * (1).客户角色：创建了一个具体命令对象并确定其接收者。
 * (2).命令角色：声明了一个给所有具体命令类的抽象接口。这是一个抽象角色，通常由一个Java接口或java抽象类实现。
 * (3).具体命令角色：定义一个接受者和行为之间的弱耦合；实现execute方法，负责调用接收者的相应操作。execute方法通常叫做 执行方法。
 * (4).请求者（Invoke）角色：负责调用命令对象执行请求，相关的方法叫做行动方法。
 * (5).接收者（Receiver）角色：负责具体实施和执行一个请求。任何一个类都可以成为接收者，实施和执行请求的方法叫做行动方法。
 * <p>
 * Command命令设计模式的优缺点：
 * 优点: 解耦了命令请求者和接受者之间联系。请求者调用一个命令，接受者接受请求并执行相应的动作，因为使用Command模式解耦，
 *      请求者无需知道接受者任何接口。
 * 缺点: 造成出现过多的具体命令类。
 * JDK中命令模式的应用：
 * •java.lang.Runnable
 * •javax.swing.Action
 */

//客户端
public class Client {
    public static void main(String[] args) {
        //客户端创建命令接受者
        Receiver receiver = new Receiver();
        //客户端创建具体命令，并指定命令接受者  
        Command command = new ConcreteCommand(receiver);
        //客户端创建请求者，并给请求者指定具体命令  
        Invoker invoker = new Invoker(command);
        //命令请求者发出命令请求  
        invoker.action();
    }
}     