package model.mediator.mediator1;

//抽象同事类
abstract class AbstractColleague {
    protected AbstractMediator mediator;

    /**
     * 既然有中介者，那么每个具体同事必然要与中介者有联系，
     * 否则就没必要存在于 这个系统当中，这里的构造函数相当
     * 于向该系统中注册一个中介者，以取得联系
     */
    public AbstractColleague(AbstractMediator mediator) {
        this.mediator = mediator;
    }

    // 在抽象同事类中添加用于与中介者取得联系（即注册）的方法  
    public void setMediator(AbstractMediator mediator) {
        this.mediator = mediator;
    }
}  