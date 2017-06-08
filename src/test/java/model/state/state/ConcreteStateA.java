package model.state.state;

/// <summary>
/// 具体状态类，每一个子类实现一个与Context的一个状态相关的行为
/// </summary>
public class ConcreteStateA extends State {
    /// <summary>
    /// 设置ConcreteStateA的下一个状态是ConcreteStateB
    /// </summary>
    /// <param name="context"></param>
    @Override
    public void Handle(Context context) {
        System.out.println("当前状态是 A.");
        context.setState(new ConcreteStateB());
    }
}