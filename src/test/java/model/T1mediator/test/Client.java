package model.T1mediator.test;

/**
 * Created by gwb on 2017/6/7.
 */
public class Client {
    public static void main(String[] args) {
        UnitedNations council=new UnitedNationsSecurityCouncil();

        Country usa=new USA(council);

        Country iraq=new Iraq(council);

        council.setIraq(iraq);

        council.setUsa(usa);

        usa.declare("不准研制核武器，否则发动战争");

        iraq.declare("我们没有核武器也不怕战争");
    }
}
