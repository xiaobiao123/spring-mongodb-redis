package model.mediator.mediator3;

class ColleagueA extends AbstractColleague{
  
    public void setNumber(int number, AbstractMediator am) {  
        this.number = number;  
        am.AaffectB();  
    }  
}  
  