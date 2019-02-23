package akademia.cars.controller;

import akademia.cars.model.Car;
import akademia.cars.service.CarService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin //udostępniamy wszystki połączenia na serwerze czyli wyłączyliśmy firewalll
@RequestMapping("/api")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/cars")
    //@RequestMapping(value = "/cars", method = RequestMethod.GET)
    public List<Car> getCars(){

        return carService.getCars();
    }


}
