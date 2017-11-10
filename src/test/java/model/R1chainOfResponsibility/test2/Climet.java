package model.R1chainOfResponsibility.test2;

/**
 * Created by Administrator on 2017/6/19.
 */
public class Climet {
    public static void main(String[] args) {

        AHandlera handlera = new HandlerImp();

        handlera.setaHandlera(new HanadlerImpB());

        handlera.handler("xxx");


    }
}
