package model.mediator;

class PersonB extends Person {
    public PersonB(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String msg) {
        mediator.send(msg, this);
    }

    @Override
    public void greeting(String msg) {
        System.out.println("PersonB: " + msg);
    }
}   