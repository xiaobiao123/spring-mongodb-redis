package model.mediator;

//具体中介者
class ConcreteMediator implements Mediator {
    //中介者所联系的交互对象  
    private Person personA;
    private Person personB;

    public void setPersonA(Person personA) {
        this.personA = personA;
    }

    public void setPersonB(Person personB) {
        this.personB = personB;
    }

    public void send(String msg, Person person) {
        if (person.equals(personA)) {
            personA.greeting(msg);
        } else {
            personB.greeting(msg);
        }
    }
}