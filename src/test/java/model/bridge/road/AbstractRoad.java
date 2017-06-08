package model.bridge.road;

public abstract class AbstractRoad {
    protected AbstractCar car;

    public void setCar(AbstractCar car) {
        this.car = car;
    }

    public abstract void Run();
}