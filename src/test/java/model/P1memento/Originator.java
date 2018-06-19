package model.P1memento;

/**
 * Memento备忘录设计模式是一个保存另外一个对象内部状态拷贝的对象，这样以后就可以将该对象恢复到以前保存的状态。
 *
 * Memento备忘录设计模式有以下3个重要角色：
 * Originator：需要保存内部状态的对象。
 * Caretaker：为Originator保存并恢复状态的对象。
 * Memento：存放Originator内部状态的对象，由Originator进行读写，并且Caretaker进行维护。
 */
// Originator  
public class Originator {
    private String state;

    public Originator(String state) {
        this.state = state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public Memento createMemento(String state) {
        return new Memento(state);
    }

    public static void main(String[] args) {
        Originator originator = new Originator("State1");
        Caretaker caretaker = new Caretaker();
        caretaker.addMemento(originator.createMemento(originator.getState()));
        originator.setState("State2");
        String restore = caretaker.getMemento().getState();
        System.out.println("Current originator C3state=" + originator.getState() + ",restore C3state=" + restore);
    }
}  