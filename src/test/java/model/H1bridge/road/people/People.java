package model.H1bridge.road.people;

import model.H1bridge.road.AbstractRoad;

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