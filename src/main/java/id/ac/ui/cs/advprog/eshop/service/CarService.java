package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService {
    public Car create(Car car);
    public Car update(Car car);
    public Car delete(Car car);
    public Car getById(String id);
    public List<Car> getAll();
}
;