package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.Iterator;

public interface CarRepository {
    public Car create(Car car);
    public Car update(Car car);
    public Car delete(Car car);
    public Car getById(String id);
    public Iterator<Car> getAll();
}
