package model.memento;

import java.util.Stack;

// Caretaker
class Caretaker {
    private Stack<Memento> mementos = new Stack<Memento>();

    public void addMemento(Memento m) {
        mementos.push(m);
    }

    public Memento getMemento() {
        if (!mementos.empty()) {
            return mementos.pop();
        }
        return null;
    }
}  