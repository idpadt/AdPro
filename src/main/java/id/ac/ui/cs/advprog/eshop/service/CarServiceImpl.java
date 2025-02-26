package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car create(Car car){
        carRepository.create(car);
        return car;
    }

    @Override
    public Car update(Car car){
        carRepository.update(car);
        return car;
    }

    @Override
    public Car delete(Car car){
        carRepository.delete(car);
        return car;
    }

    @Override
    public List<Car> getAll(){
        Iterator<Car> carIterator = carRepository.getAll();
        List<Car> allCar = new ArrayList<Car>();
        carIterator.forEachRemaining(allCar::add);
        return allCar;
    }

    @Override
    public Car getById(String carId){
        return carRepository.getById(carId);
    }

}
