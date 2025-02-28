package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exception.CarNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

@ExtendWith(MockitoExtension.class)
public class CarRepositoryTest {

    @InjectMocks
    CarRepositoryImpl carRepository;

    private Car car1;
    private Car car2;

    @BeforeEach
    void setUp() {
        this.car1 = new Car();
        car1.setCarId("7erw-erwt987-7wer-7698ew");
        car1.setCarName("Honda");
        car1.setCarColor("Blue");
        car1.setCarQuantity(55);

        this.car2 = new Car();
        car2.setCarId("nb321-mnb5-vcb1432-vb432");
        car2.setCarName("Suzuki");
        car2.setCarColor("Red");
        car2.setCarQuantity(120);
    }

    @Test
    void testCreateAndGet() {
        carRepository.create(car1);

        Iterator<Car> carIterator = carRepository.getAll();
        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());
        assertEquals(car1.getCarName(), savedCar.getCarName());
        assertEquals(car1.getCarColor(), savedCar.getCarColor());
        assertEquals(car1.getCarQuantity(), savedCar.getCarQuantity());
    }

    @Test
    void testCreateIfNullId(){
        car1.setCarId(null);
        carRepository.create(car1);

        Iterator<Car> carIterator = carRepository.getAll();
        Car savedCar = carIterator.next();
        assertNotNull(savedCar.getCarId());
    }

    @Test
    void testGetAllIfEmpty(){
        Iterator<Car> carIterator = carRepository.getAll();
        assertFalse(carIterator.hasNext());
    }

    @Test
    void testGetAllIfMoreThanOneCar(){
        carRepository.create(car1);
        carRepository.create(car2);

        Iterator<Car> carIterator = carRepository.getAll();
        assertTrue(carIterator.hasNext());

        Car savedCar = carIterator.next();
        assertEquals(car1.getCarId(), savedCar.getCarId());

        savedCar = carIterator.next();
        assertEquals(car2.getCarId(), savedCar.getCarId());

        assertFalse(carIterator.hasNext());
    }

    @Test
    void testGetById(){
        carRepository.create(car1);
        carRepository.create(car2);

        assertEquals(car1, carRepository.getById(car1.getCarId()));
        assertEquals(car2, carRepository.getById(car2.getCarId()));
    }

    @Test
    void testGetByIdIfNotFound(){
        assertThrows(CarNotFoundException.class, ()->carRepository.getById(car1.getCarId()));
    }

    @Test
    void testUpdate(){
        carRepository.create(car1);

        car1.setCarName("Angkot");
        car1.setCarColor("Coklat");
        car1.setCarQuantity(420);
        carRepository.update(car1);

        assertEquals(car1, carRepository.getById(car1.getCarId()));
    }

    @Test
    void testUpdateIfNotFound(){
        assertThrows(CarNotFoundException.class, ()->carRepository.update(car1));
    }

    @Test
    void testDelete(){
        carRepository.create(car1);
        assertEquals(car1, carRepository.getById(car1.getCarId()));

        carRepository.delete(car1);
        assertThrows(CarNotFoundException.class, ()->carRepository.getById(car1.getCarId()));
    }

    @Test
    void testDeleteIfNotFound(){
        assertThrows(CarNotFoundException.class, ()->carRepository.delete(car1));
    }
}
