import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.*;

import core.Road;
import exceptions.FullVehicleException;
import exceptions.PassengerNotFoundException;
import people.Firefighter;
import people.Human;
import people.Policeman;
import vehicle.Bus;
import vehicle.FireTruck;
import vehicle.PoliceCar;
import vehicle.Taxi;

class VehicleTest {

    private Human regularPassenger;
    private Firefighter firefighter1;
    private Policeman policeman1;

    @BeforeEach
    void setUp() {
        regularPassenger = new Human("Звичайний Пасажир");
        firefighter1 = new Firefighter("Іван Пожежник");
        policeman1 = new Policeman("Петро Поліцейський");
    }

    @Test
    @DisplayName("Автобус: Тест наповнення та FullVehicleException")
    @Tag("Bus")
    void testBusCapacityAndExceptions() {
        Bus bus = new Bus();

        assertDoesNotThrow(() -> bus.boardPassenger(regularPassenger));

        int spotsToFill = bus.getMaxCapacity() - bus.getCurrentOccupancy();

        for (int i = 0; i < spotsToFill; i++) {
            bus.boardPassenger(new Human("Пасажир" + i));
        }

        assertEquals(bus.getMaxCapacity(), bus.getCurrentOccupancy(), "Автобус має бути повним після циклу");

        assertThrows(FullVehicleException.class, () ->
                        bus.boardPassenger(new Human("Ще один"))
                , "Повинно кинути FullVehicleException для 51-го пасажира");
    }

    @Test
    @DisplayName("Таксі: Тест PassengerNotFoundException")
    @Tag("Taxi")
    void testTaxiPassengerNotFound() {
        Taxi taxi = new Taxi();
        assertDoesNotThrow(() -> taxi.boardPassenger(policeman1));

        assertThrows(PassengerNotFoundException.class, () ->
                taxi.dropOffPassenger("Неіснуючий Пасажир")
        );
    }

    @Test
    @DisplayName("Пожежна машина: Тест Generics обмежень (Firefighter)")
    @Tag("Constraints")
    void testFireTruckConstraints() {
        FireTruck fireTruck = new FireTruck();

        assertDoesNotThrow(() -> fireTruck.boardPassenger(firefighter1));
        assertEquals(1, fireTruck.getCurrentOccupancy());
    }

    @Test
    @DisplayName("Поліцейська машина: Тест Generics обмежень (Policeman)")
    @Tag("Constraints")
    void testPoliceCarConstraints() {
        PoliceCar policeCar = new PoliceCar();

        assertDoesNotThrow(() -> policeCar.boardPassenger(policeman1));
        assertEquals(1, policeCar.getCurrentOccupancy());
    }

    @Test
    @DisplayName("Road: Тест Wildcard та підрахунку людей")
    @Tag("Wildcard")
    void testRoadWildcardCount() {
        Road road = new Road();
        Bus bus = new Bus();
        FireTruck fireTruck = new FireTruck();
        PoliceCar policeCar = new PoliceCar();

        assertDoesNotThrow(() -> bus.boardPassenger(new Human("Пас_Бус_1")));
        assertDoesNotThrow(() -> fireTruck.boardPassenger(new Firefighter("Пож_Маш_1")));
        assertDoesNotThrow(() -> policeCar.boardPassenger(new Policeman("Пол_Маш_1")));

        road.addVehicleToRoad(bus);
        road.addVehicleToRoad(fireTruck);
        road.addVehicleToRoad(policeCar);

        int totalHumans = road.getCountOfHumans();
        assertEquals(3, totalHumans, "Кількість людей на дорозі має бути 3");
    }
}