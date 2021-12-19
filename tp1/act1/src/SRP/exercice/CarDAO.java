package SRP.exercice;

import java.util.Arrays;
import java.util.List;

public class CarDAO {

    private List<Car> _carsDb = Arrays
            .asList(new Car("1", "Golf III", "Volkswagen"), new Car("2", "Multipla", "Fiat"),
                    new Car("3", "Megane", "Renault"));

    public CarDAO() {}
    public CarDAO(List<Car> cars) {
        _carsDb = cars;
    }

    public List<Car> list() {
        return this._carsDb;
    }

    public Car get(final String carId) {
        for (Car car : _carsDb) {
            if (car.getId().equals(carId)) {
                return car;
            }
        }
        return null;
    }
}
