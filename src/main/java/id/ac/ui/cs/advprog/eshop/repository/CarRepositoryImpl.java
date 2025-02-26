package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exception.CarNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class CarRepositoryImpl implements CarRepository {

    private List<Car> carData = new ArrayList<>();

    public Car create(Car car){
        if(car.getCarId() == null){
            UUID uuid = UUID.randomUUID();
            car.setCarId(uuid.toString());
        }
        carData.add(car);
        return car;
    }

    public Car update(Car car){
        String id = car.getCarId();
        int index = findIndexById(id);
        carData.set(index, car);
        return car;
    }

    public Car delete(Car car){
        String id = car.getCarId();
        int index = findIndexById(id);
        carData.remove(index);
        return car;
    }

    public Car getById(String id){
        int index = findIndexById(id);
        return carData.get(index);
    }

    public Iterator<Car> getAll(){
        return carData.iterator();
    }

    private int findIndexById(String id) throws CarNotFoundException {
        for(int ii = 0; ii < carData.size(); ii++){
            if(carData.get(ii).getCarId().equals(id)){
                return ii;
            }
        }
        throw new CarNotFoundException("Car with ID " + id + " not found.");
    }
}
