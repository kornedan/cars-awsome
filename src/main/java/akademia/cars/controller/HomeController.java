package akademia.cars.controller;

import akademia.cars.expections.AllReadyExist;
import akademia.cars.expections.NotFoundExceptions;
import akademia.cars.model.Car;
import akademia.cars.model.dtos.CarDTO;
import akademia.cars.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class HomeController {

    private CarService carService;

    public HomeController(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping("home")
    public String getHomePage(Model model) {
        String welcome = "Welcome to my awsome Cars App!";
        model.addAttribute("welcome", welcome);
        model.addAttribute("car", carService.getCars());
        return "index";
    }

    @PostMapping("add")
    public String addCar(@Valid @ModelAttribute CarDTO carDTO) throws AllReadyExist {
        carService.addCar(carDTO);

        return "redirect:/home";
    }

    @GetMapping("del")
    public String deleteCar(@RequestParam(value = "plate")String plate) throws NotFoundExceptions {

        carService.deleteCar(plate);

        return "redirect:/home";
    }

    @PostMapping("update")
    public String updateCar(@Valid @ModelAttribute CarDTO carDTO,@RequestParam(value = "plate")String plate) throws AllReadyExist, NotFoundExceptions {
        carService.updateCarByPlate(plate,carDTO);
        return "redirect:/home";
    }

    @GetMapping("redirect/update")
    public String getRedirectToUpdate(@RequestParam(value = "plate")String plate){
        return "update";
    }

}
