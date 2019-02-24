package akademia.cars.service;

import akademia.cars.expections.AllReadyExist;
import akademia.cars.expections.NotFoundExceptions;
import akademia.cars.mappers.CarMapper;
import akademia.cars.model.Car;
import akademia.cars.model.dtos.CarDTO;
import akademia.cars.repository.CarRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CarService {


    private CarRepository carRepository;
    private CarMapper carMapper;

    //Dependency injection - nie uzywaj autowired


    public CarService(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public Car getCarByPlate(String plate) {
        return carRepository.findCarByPlate(plate).get();
    }


    //---------DTO-------------------------------------------------

    public Optional<Car> getCarByPlateOptional(String plate) {
        return carRepository.findCarByPlate(plate);
    }



    public List<CarDTO> getCarsDTO() {

        List<CarDTO> carDTOS = new ArrayList<>();
        for (Car car : carRepository.findAll()) {
            carDTOS.add(carMapper.map(car));
        }

        return carDTOS;
    }


    public boolean deleteCar(String plate) throws NotFoundExceptions {
        Optional<Car> car = carRepository.findCarByPlate(plate);
        if (car.isPresent()) {
            carRepository.deleteById(car.get().getId());
            return true;
        } else {
            return false;
        }
    }


    public boolean addCar(CarDTO carDTO) throws AllReadyExist {

        Optional<Car> car = carRepository.findCarByPlate(carDTO.getPlate());

//        Optional.of(carRepository.findCarByPlate(carDTO.getPlate())
//                .orElseGet(() -> carRepository.save()))
        if (car.isPresent()) {
            throw new AllReadyExist("Car exist");
        } else {
//            Car carDao = new Car(
//                    carDTO.getBrand(),
//                    carDTO.getModel(),
//                    carDTO.getPower(),
//                    carDTO.getPlate());
//
//            carRepository.save(carDao);
//

            carRepository.save(
                    Car.builder()
                            .brand(carDTO.getBrand())
                            .model(carDTO.getModel())
                            .power(carDTO.getPower())
                            .plate(carDTO.getPlate())
                            .build()
            );
            return true;
        }
    }

    public CarDTO updateCarByPlate(String plate, CarDTO carDTO) throws NotFoundExceptions{
        Optional<Car> car = carRepository.findCarByPlate(plate);

        if(car.isPresent()){

            car.get().setBrand(carDTO.getBrand());
            car.get().setModel(carDTO.getModel());
            car.get().setPower(carDTO.getPower());
            car.get().setPlate(carDTO.getModel());

            carRepository.save(car.get());
            return carMapper.map(car.get());
        }
        throw new NotFoundExceptions("Car not exist!");
    }

}
