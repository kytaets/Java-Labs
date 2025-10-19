package core;

import people.Human;
import vehicle.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class Road {

    public List<Vehicle<? extends Human>> vehiclesOnRoad = new ArrayList<>();

    public void addVehicleToRoad(Vehicle<? extends Human> vehicle) {
        vehiclesOnRoad.add(vehicle);
    }

    public int getCountOfHumans() {
        int humanCount = 0;
        for (Vehicle<? extends Human> vehicle : vehiclesOnRoad) {
            humanCount += vehicle.getCurrentOccupancy();
        }
        return humanCount;
    }
}
