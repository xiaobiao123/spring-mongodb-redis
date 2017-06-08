package model.state.state;

public class Context {

    private State state;

    /// <summary>
    /// 定义Context的初始状态
    /// </summary>
    /// <param name="state"></param>
    public Context(State state) {
        this.state = state;
    }

    /// <summary>
    /// 可读写的状态属性，用于读取和设置新状态
    /// </summary>


    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    /// <summary>
    /// 对请求做处理，并设置下一个状态
    /// </summary>
    public void Request() {
        state.Handle(this);
    }
}
