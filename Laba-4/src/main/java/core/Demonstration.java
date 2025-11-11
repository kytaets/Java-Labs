package core;

import people.Firefighter;
import people.Human;
import people.Policeman;
import vehicle.Bus;
import vehicle.FireTruck;
import vehicle.PoliceCar;

class Demonstration {

    public static void main(String[] args) {
        Road road = new Road();

        Bus bus = new Bus();
        FireTruck fireTruck = new FireTruck();
        PoliceCar policeCar = new PoliceCar();

        bus.boardPassenger(new Human("Іван"));
        bus.boardPassenger(new Human("Дмитро"));

        fireTruck.boardPassenger(new Firefighter("Полковник Іванов"));

        policeCar.boardPassenger(new Policeman("Офіцер Галушко"));

        System.out.println("--- СТАН ТРАНСПОРТУ ---");
        System.out.println("Автобус: " + bus.getCurrentOccupancy() + " пасажири.");
        System.out.println("Пожежна машина: " + fireTruck.getCurrentOccupancy() + " пасажир.");
        System.out.println("Поліцейська машина: " + policeCar.getCurrentOccupancy() + " пасажир.");


        System.out.println("\n--- ДОДАВАННЯ НА ДОРОГУ (Road.addVehicleToRoad) ---");

        road.addVehicleToRoad(bus);
        System.out.println("Додано Bus");

        road.addVehicleToRoad(fireTruck);
        System.out.println("Додано FireTruck");

        road.addVehicleToRoad(policeCar);
        System.out.println("Додано PoliceCar");

        int totalHumans = road.getCountOfHumans();
        int expectedCount = 4;

        System.out.println("\n--- РЕЗУЛЬТАТ ПІДРАХУНКУ ---");
        System.out.println("Загальна кількість людей на Road: " + totalHumans);
    }
}