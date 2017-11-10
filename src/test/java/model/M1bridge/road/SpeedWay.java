package model.M1bridge.road;

//高速公路
public class SpeedWay extends AbstractRoad {
    @Override
    public void Run() {
        car.Run();
        System.out.println("高速公路上行驶");
    }
}