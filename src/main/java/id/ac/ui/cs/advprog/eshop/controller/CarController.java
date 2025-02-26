package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.exception.CarNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    @Autowired
    private CarService carService;

    @GetMapping("/create")
    public String createCarPage(Model model){
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/create")
    public String createCarPost(@ModelAttribute @Valid Car car, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "createCar";
        }
        carService.create(car);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String carListPage(Model model){
        List<Car> allCars = carService.getAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/edit/{carId}")
    public String editCarPage(@PathVariable String carId, Model model){
        try {
            Car car = carService.getById(carId);
            model.addAttribute("car", car);
            return "editCar";
        } catch (CarNotFoundException ex) {
            return "redirect:list";
        }
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute @Valid Car car, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "editCar";
        }
        try {
            System.out.println(car.getCarId());
            carService.update(car);
            return "redirect:list";
        } catch (CarNotFoundException ex) {
            return "redirect:list";
        }
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") String carId){
        try {
            Car car = carService.getById(carId);
            carService.delete(car);
            return "redirect:list";
        } catch (CarNotFoundException ex) {
            return "redirect:list";
        }
    }

}