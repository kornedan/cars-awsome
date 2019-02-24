package akademia.cars.controller;

import akademia.cars.expections.AllReadyExist;
import akademia.cars.expections.NotFoundExceptions;
import akademia.cars.mappers.CarMapper;
import akademia.cars.model.Car;
import akademia.cars.model.dtos.CarDTO;
import akademia.cars.repository.CarRepository;
import akademia.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @PostMapping("/dto/cars")
    public ResponseEntity<?> addCar(@RequestBody CarDTO carDTO) throws AllReadyExist {
        if(!carService.addCar(carDTO)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);//409
        }else {
            return new ResponseEntity<>(HttpStatus.CREATED);//200
        }
    }

    @PostMapping("/dto/cars/param")
    public ResponseEntity<?> addCarParams(@RequestParam String brand,
                                          @RequestParam String model,
                                          @RequestParam String power,
                                          @RequestParam String plate) throws AllReadyExist {




        if(!carService.addCar(new CarDTO(brand,model,power,plate))){
            return new ResponseEntity<>(HttpStatus.CONFLICT);//409
        }else {
            return new ResponseEntity<>(HttpStatus.CREATED);//200
        }
    }

    @DeleteMapping("/dto/cars/{plate}")
    public ResponseEntity<?> deleteCar(@PathVariable String plate) throws NotFoundExceptions {

        if(carService.deleteCar(plate)){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else {
           // throw new NotFoundExceptions("Car not Exist");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/dto/cars/{plate}")
    public CarDTO updateCarByPlate(@PathVariable(value = "plate") String plate, @RequestBody CarDTO carDto) throws NotFoundExceptions{
        return carService.updateCarByPlate(plate,carDto);
    }

}
