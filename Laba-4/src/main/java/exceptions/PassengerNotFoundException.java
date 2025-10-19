package exceptions;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(String name) {
        super("Пасажир " + name + " не знайдений у транспортному засобі.");
    }
}