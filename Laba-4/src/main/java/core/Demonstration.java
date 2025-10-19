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

        bus.boardPassenger(new Human("Пас_Бус_1"));
        bus.boardPassenger(new Human("Пас_Бус_2"));

        fireTruck.boardPassenger(new Firefighter("Пож_Маш_1"));

        policeCar.boardPassenger(new Policeman("Пол_Маш_1"));

        System.out.println("--- СТАН ТРАНСПОРТУ ---");
        System.out.println("Автобус: " + bus.getCurrentOccupancy() + " пасажири.");
        System.out.println("Пожежна машина: " + fireTruck.getCurrentOccupancy() + " пасажир.");
        System.out.println("Поліцейська машина: " + policeCar.getCurrentOccupancy() + " пасажир.");


        System.out.println("\n--- ДОДАВАННЯ НА ДОРОГУ (Road.addVehicleToRoad) ---");

        road.addVehicleToRoad(bus);
        System.out.println("Додано Bus (Vehicle<Human>)");

        road.addVehicleToRoad(fireTruck);
        System.out.println("Додано FireTruck (Vehicle<Firefighter>)");

        road.addVehicleToRoad(policeCar);
        System.out.println("Додано PoliceCar (Vehicle<Policeman>)");

        int totalHumans = road.getCountOfHumans();
        int expectedCount = 4;

        System.out.println("\n--- РЕЗУЛЬТАТ ПІДРАХУНКУ ---");
        System.out.println("Загальна кількість людей на Road: " + totalHumans);

        if (totalHumans == expectedCount) {
            System.out.println("✅ Успіх: Підрахунок коректний (Очікується: " + expectedCount + ")");
        } else {
            System.out.println("❌ Помилка: Очікується " + expectedCount + ", отримано " + totalHumans);
        }
    }
}