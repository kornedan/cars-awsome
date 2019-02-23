package akademia.cars.controller;

import akademia.cars.mappers.CarMapper;
import akademia.cars.model.Car;
import akademia.cars.model.dtos.CarDTO;
import akademia.cars.repository.CarRepository;
import akademia.cars.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin //udostępniamy wszystki połączenia na serwerze czyli wyłączyliśmy firewalll
@RequestMapping("/api")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    //@RequestMapping(value = "/cars", method = RequestMethod.GET)
    @GetMapping("/cars")
    public List<Car> getCar(){

        return carService.getCars();
    }


    @GetMapping("/cars/{plate}")
    public Car getCarByPlate(@PathVariable String plate){

        return carService.getCarByPlate(plate);
    }

    //---------DTO-------------------------------------------------

    @GetMapping("/dto/cars")
    public List<CarDTO> getCarsDTO(){

        return carService.getCarsDTO();
    }



}
