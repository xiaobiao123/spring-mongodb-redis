package model.S1command.test;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Client {
    public static void main(String[] args) {
        Rec rec = new Rec();

        ICom com = new ComImp(rec);

        Invo invo = new Invo(com);

        invo.invok();
    }
}
