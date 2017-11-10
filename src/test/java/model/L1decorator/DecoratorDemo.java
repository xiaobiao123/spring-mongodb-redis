package model.L1decorator;

/**
 * 在不必改变原类文件和使用继承的情况下，动态地扩展一个对象的功能。它是通过创建一个包装对象，
 * 也就是装饰来包裹真实的对象
 * <p>
 * Decorator装饰设计模式是动态给一个对象添加一些额外的职责，但同时又不影响对象本身已有的功能。
 * <p>
 * Decorator装饰设计模式中的两种角色：
 * Decoratee被装饰者：即需要功能增强的原始对象，即目标对象。
 * Decorator装饰者：为原始对象提供功能增强的对象。
 * <p>
 * (1).Proxy代理模式中，客户端不直接调用服务端程序，而是通过一个代理对象来调用服务端程序，类似一个请求转发的作用。
 * (2). Decorator装饰设计模式中，被装饰对象可以不用添加任何装饰而直接使用，也可以通过装饰器的包装动态增强功能。
 * JDK中装饰者模式的应用：
 * •java.io包
 * •java.util.Collections#synchronizedList(List)
 * •AWT和Swing图形组件
 */
public class DecoratorDemo {
    public static void main(String[] args) {
        //画一个普通的TextArea
        TextArea textArea = new TextArea();
        textArea.draw();
        //画一个带滚动条的TextArea
        ScrollDecorator scroll = new ScrollDecorator(new TextArea());
        scroll.draw();
        //画一个带边框的TextArea
        BorderDecorator border = new BorderDecorator(new TextArea());
        border.draw();
        //画一个既带边框又带滚动条的TextArea
        BorderDecorator border2 = new BorderDecorator(new ScrollDecorator(new TextArea()));
        border2.draw();
    }
}  