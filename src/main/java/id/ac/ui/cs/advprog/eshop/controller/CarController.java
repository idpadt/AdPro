package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.exception.CarNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;

import java.util.List;

@Controller
@RequestMapping("/car")
public class CarController {

    private final String redirectList = "redirect:list";

    @Autowired
    private CarService service;

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
        service.create(car);
        return redirectList;
    }

    @GetMapping("/list")
    public String carListPage(Model model){
        List<Car> allCars = service.getAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/edit/{carId}")
    public String editCarPage(@PathVariable String carId, Model model){
        try {
            Car car = service.getById(carId);
            model.addAttribute("car", car);
            return "editCar";
        } catch (CarNotFoundException ex) {
            return redirectList;
        }
    }

    @PostMapping("/edit")
    public String editCarPost(@ModelAttribute @Valid Car car, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "editCar";
        }
        try {
            service.update(car);
            return redirectList;
        } catch (CarNotFoundException ex) {
            return redirectList;
        }
    }

    @PostMapping("/delete")
    public String deleteCar(@RequestParam("carId") String carId){
        try {
            Car car = service.getById(carId);
            service.delete(car);
            return redirectList;
        } catch (CarNotFoundException ex) {
            return redirectList;
        }
    }

}