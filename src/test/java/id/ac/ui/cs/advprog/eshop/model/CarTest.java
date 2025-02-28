package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {

    private Car car;

    @BeforeEach
    void setUp(){
        this.car = new Car();
        this.car.setCarId("7erw-erwt987-7wer-7698ew");
        this.car.setCarName("Honda");
        this.car.setCarColor("Blue");
        this.car.setCarQuantity(55);
    }

    @Test
    void testGetCarId(){
        assertEquals("7erw-erwt987-7wer-7698ew", this.car.getCarId());
    }

    @Test
    void testGetCarName(){
        assertEquals("Honda", this.car.getCarName());
    }

    @Test
    void testGetCarColor(){
        assertEquals("Blue", this.car.getCarColor());
    }

    @Test
    void testGetCarQuantity(){
        assertEquals(55, this.car.getCarQuantity());
    }
}
