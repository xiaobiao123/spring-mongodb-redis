package model.chainOfResponsibility.test2;

import javax.swing.plaf.PanelUI;

/**
 * Created by Administrator on 2017/6/19.
 */
abstract class AHandlera {

    private AHandlera aHandlera;

    public AHandlera(){

    }
    public AHandlera(AHandlera aHandlera) {
        this.aHandlera = aHandlera;
    }

    public AHandlera getaHandlera() {
        return aHandlera;
    }

    public void setaHandlera(AHandlera aHandlera) {
        this.aHandlera = aHandlera;
    }

    public abstract void handler(String name);


}
