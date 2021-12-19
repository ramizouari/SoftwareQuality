package SRP.exercice;
import java.util.Arrays;

/**
 * Pour bien appliquer le SRP, on déplace les voitures vers le CarDAO, on les accéde avec "list"
 */
public class CarManager {

    private CarDAO _dao ;
    private CarRater _rater;
    private CarFormatter _formatter;

    public CarManager(CarDAO dao, CarRater rater, CarFormatter formatter) {
        _dao = dao;
        _rater = rater;
        _formatter = formatter;
    }

    public Car getFromDb(final String carId) {
        return _dao.get(carId);
    }
    public String getCarsNames() {
        return _formatter.getCarsNames(_dao.list());
    }
    public Car getBestCar() {
        return _rater.getBestCar(_dao.list());
    }
}
