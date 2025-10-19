package vehicle;

import exceptions.FullVehicleException;
import exceptions.PassengerNotFoundException;
import people.Human;

import java.util.ArrayList;
import java.util.List;

public abstract class Vehicle<T extends Human> {
    private final int maxCapacity;
    private final List<T> passengers;

    public Vehicle(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        this.passengers = new ArrayList<>();
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentOccupancy() {
        return passengers.size();
    }

    public void boardPassenger(T passenger) throws FullVehicleException {
        if (passengers.size() >= maxCapacity) {
            throw new FullVehicleException();
        }

        System.out.println(passenger.getName() + " сів(ла) у " + this.getClass().getSimpleName());

        passengers.add(passenger);
    }

    public void dropOffPassenger(String passengerName) throws PassengerNotFoundException {
        T passengerToRemove = null;
        for (T p : passengers) {
            if (p.getName().equals(passengerName)) {
                passengerToRemove = p;
                break;
            }
        }

        if (passengerToRemove != null) {
            passengers.remove(passengerToRemove);
            System.out.println(passengerName + " висів(ла) із " + this.getClass().getSimpleName());
        } else {
            throw new PassengerNotFoundException(passengerName);
        }
    }

    public List<? extends Human> getPassengers() {
        return new ArrayList<>(passengers);
    }
}