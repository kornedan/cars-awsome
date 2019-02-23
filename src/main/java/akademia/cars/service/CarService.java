package akademia.cars.service;
import akademia.cars.mappers.CarMapper;
import akademia.cars.model.Car;
import akademia.cars.model.dtos.CarDTO;
import akademia.cars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CarService {


    private CarRepository carRepository;
    private CarMapper carMapper;

        //Dependency injection - nie uzywaj autowired


    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<Car> getCars(){
        return carRepository.findAll();
    }

    public Car getCarByPlate(String plate){
        return carRepository.findCarByPlate(plate).get();
    }


    //---------DTO-------------------------------------------------


    public List<CarDTO> getCarsDTO(){

        List<CarDTO> carDTOS = new ArrayList<>();
        for (Car car: carRepository.findAll()) {
            carDTOS.add(carMapper.map(car));
        }

        return carDTOS;
    }


}
