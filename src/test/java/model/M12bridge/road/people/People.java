package model.M12bridge.road.people;

import model.M12bridge.road.AbstractRoad;

public  abstract class People {
    AbstractRoad road;

    public AbstractRoad getRoad() {
        return road;
    }

    public void setRoad(AbstractRoad road) {
        this.road = road;
    }

    public abstract void Run();

}