package akademia.cars.expections;

public class AllReadyExist extends Exception {

    String massage = "Car exist";

    public AllReadyExist(String message) {
        super(message);
    }
}
