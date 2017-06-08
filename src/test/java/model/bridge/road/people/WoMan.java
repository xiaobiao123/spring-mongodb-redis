package model.bridge.road.people;

class WoMan extends People {
    @Override
    public void Run() {
        System.out.println("女人开着");
        super.getRoad().Run();
    }
}