package model.M1bridge.road.people;

public class Man extends People {
    @Override
    public void Run() {
        System.out.println("男人开着");
        super.getRoad().Run();
    }
}