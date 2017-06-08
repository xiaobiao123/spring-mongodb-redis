package model.state.state;

public class ConcreteStateB extends State {
    /// <summary>
    /// 设置ConcreteStateB的下一个状态是ConcreteSateA
    /// </summary>
    /// <param name="context"></param>
    public void Handle(Context context) {
        System.out.println("当前状态是 B.");
        context.setState(new ConcreteStateA());
    }
}