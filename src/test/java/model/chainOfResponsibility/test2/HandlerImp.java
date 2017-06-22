package model.chainOfResponsibility.test2;

/**
 * Created by Administrator on 2017/6/19.
 */
public class HandlerImp extends AHandlera {

    public HandlerImp(AHandlera aHandlera) {
        super(aHandlera);
    }

    public HandlerImp() {
        super();
    }

    @Override
    public void handler(String name) {


        System.out.println("i am handler a...i am going todo "+name);

        if ("".equals(name)) {
            System.out.println("handler imp has handler .....");
        } else {
            getaHandlera().handler(name);
        }
    }
}
