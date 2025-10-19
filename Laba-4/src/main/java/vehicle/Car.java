package vehicle;

import people.Human;

public abstract class Car<T extends Human> extends Vehicle<T> {
    public Car(int maxCapacity) {
        super(maxCapacity);
    }
}