package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.exception.CarNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;

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
    void testCreate() {
        when(carRepository.create(car1)).thenReturn(car1);
        assertEquals(car1, carService.create(car1));
        verify(carRepository).create(car1);
    }

    @Test
    void testGetAll(){
        List<Car> mockcars = new ArrayList<>();
        mockcars.add(car1);
        mockcars.add(car2);
        when(carRepository.getAll()).thenReturn(mockcars.iterator());

        List<Car> carList = carService.getAll();
        assertTrue(carList.contains(car1));
        assertTrue(carList.contains(car2));
        verify(carRepository).getAll();
    }

    @Test
    void testGetAllIfEmpty(){
        List<Car> mockcars = new ArrayList<>();
        when(carRepository.getAll()).thenReturn(mockcars.iterator());

        List<Car> carList = carService.getAll();
        assertTrue(carList.isEmpty());
        verify(carRepository).getAll();
    }

    @Test
    void testGetById() {
        when(carRepository.getById(car1.getCarId())).thenReturn(car1);
        when(carRepository.getById(car2.getCarId())).thenReturn(car2);

        assertEquals(car2, carService.getById(car2.getCarId()));
        assertEquals(car1, carService.getById(car1.getCarId()));

        verify(carRepository).getById(car1.getCarId());
        verify(carRepository).getById(car2.getCarId());
    }

    @Test
    void testGetByIdIfNotFound() {
        when(carRepository.getById(car1.getCarId())).thenThrow(CarNotFoundException.class);
        assertThrows(CarNotFoundException.class, () -> carService.getById(car1.getCarId()));
        verify(carRepository).getById(car1.getCarId());
    }

    @Test
    void testUpdate(){
        when(carRepository.update(car1)).thenReturn(car1);
        assertEquals(car1, carService.update(car1));
        verify(carRepository).update(car1);
    }

    @Test
    void testUpdateIfNotFound(){
        when(carRepository.update(car1)).thenThrow(CarNotFoundException.class);
        assertThrows(CarNotFoundException.class, () -> carService.update(car1));
        verify(carRepository).update(car1);
    }

    @Test
    void testDelete(){
        when(carRepository.delete(car1)).thenReturn(car1);
        assertEquals(car1, carService.delete(car1));
        verify(carRepository).delete(car1);
    }

    @Test
    void testDeleteIfNotFound(){
        when(carRepository.delete(car1)).thenThrow(CarNotFoundException.class);
        assertThrows(CarNotFoundException.class, () -> carService.delete(car1));
        verify(carRepository).delete(car1);
    }

}
