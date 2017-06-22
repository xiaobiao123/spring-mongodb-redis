package model.command.test;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Invo {
    private ICom iCom;

    public Invo(ICom iCom){
        this.iCom=iCom;
    }

    public  void invok(){
        this.iCom.exit();
    }

}
