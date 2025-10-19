package exceptions;

public class FullVehicleException extends RuntimeException {
    public FullVehicleException() {
        super("Всі місця зайняті.");
    }
}