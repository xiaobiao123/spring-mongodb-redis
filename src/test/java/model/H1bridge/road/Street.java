package model.H1bridge.road;

//市区街道
public class Street extends AbstractRoad {
    @Override
    public void Run() {
        car.Run();
        System.out.println("市区街道上行驶");
    }
}