package akademia.cars.expections;

public class NotFoundExceptions extends Exception {

    String massage = "Car not exist";

    public NotFoundExceptions(String message) {
        super(message);
    }
}
