package model.T1mediator;

//中介者
interface Mediator{  
    public void send(String msg, Person person);

    public void setPersonA(Person personA);

    public void setPersonB(Person personB);
}