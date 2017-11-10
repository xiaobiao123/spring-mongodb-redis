package model.M1bridge.road;

/**
 * Created by Administrator on 2017/6/7.
 */

import model.M1bridge.road.people.*;

public class MainTest {

    public static void main(String[] args) {
        //小汽车在高速公路上行驶;
        AbstractRoad Road1 = new Street();
        Road1.setCar(new Car());
        Road1.Run();
        System.out.println();
        System.out.println("=========================");

        //公共汽车在高速公路上行驶;
        AbstractRoad Road2 = new SpeedWay();
        Road2.setCar(new Bus());
        Road2.Run();


        //男人开着公共汽车在高速公路上行驶;
        System.out.println("=========================");

        AbstractRoad Road3 = new SpeedWay();
        Road3.setCar(new Bus());

        People p = new Man();
        p.setRoad(Road3);
        p.Run();
    }
}
