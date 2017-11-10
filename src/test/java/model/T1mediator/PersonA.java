package model.T1mediator;

//具体同事  
class PersonA extends Person {
    public PersonA(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void send(String msg) {
        mediator.send(msg, this);
    }

    @Override
    public void greeting(String msg) {
        System.out.println("PersonA: " + msg);
    }
}

