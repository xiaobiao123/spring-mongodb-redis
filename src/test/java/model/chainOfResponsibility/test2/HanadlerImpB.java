package model.chainOfResponsibility.test2;

/**
 * Created by Administrator on 2017/6/19.
 */
public class HanadlerImpB extends AHandlera {

    public HanadlerImpB(AHandlera aHandlera) {
        super(aHandlera);
    }

    public HanadlerImpB() {
        super();
    }

    @Override
    public void handler(String name) {
        System.out.println("i am handler b...");
    }
}
