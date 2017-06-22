package model.command.test;

/**
 * Created by Administrator on 2017/6/16.
 */
public class ComImp implements ICom {

    private Rec rec;

    public ComImp(Rec rec){
        this.rec=rec;
    }

    @Override
    public void exit() {
        rec.doSometing();
    }
}
