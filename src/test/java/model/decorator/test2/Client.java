package model.decorator.test2;

/**
 * Created by Administrator on 2017/6/16.
 */
public class Client {
    public static void main(String[] args) {
        ICom iCom = new ComImp();

        AbstDecort decort = new DecortA(iCom);

        decort.decort();
    }
}