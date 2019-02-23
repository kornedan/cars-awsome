package akademia.cars.service;
import akademia.cars.model.Car;
import akademia.cars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CarService {


    private CarRepository carRepository;

        //Dependency injection - nie uzywaj autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getCars(){
        return carRepository.findAll();
    }

}
