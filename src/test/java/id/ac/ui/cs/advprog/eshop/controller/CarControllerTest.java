package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.exception.CarNotFoundException;
import id.ac.ui.cs.advprog.eshop.exception.CarNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockitoBean
    private CarService carService;

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
    void testCreateCarPage() throws Exception {
        mvc.perform(get("/car/create"))
                .andExpectAll(status().isOk(),
                        view().name("createCar"),
                        model().attributeExists("car"));
    }

    @Test
    void testCreateCarPost() throws Exception {
        when(carService.create(car1)).thenReturn(car1);
        mvc.perform(post("/car/create").flashAttr("car", car1))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));
        verify(carService).create(car1);
    }

    @Test
    void testCreateCarPostIfNotValid() throws Exception {
        mvc.perform(post("/car/create")
                        .param("carName", "A")
                        .param("carQuantity", ""))
                .andExpectAll(status().isOk(),
                        view().name("createCar"));
    }

    @Test
    void testCreateCarPostIfEmptyForm() throws Exception {
        mvc.perform(post("/car/create"))
                .andExpectAll(status().isOk(),
                        view().name("createCar"));
    }

    @Test
    void testCarListPage() throws Exception {
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        when(carService.getAll()).thenReturn(cars);

        mvc.perform(get("/car/list"))
                .andExpectAll(status().isOk(),
                        view().name("carList"),
                        model().attributeExists("cars"),
                        model().attribute("cars", hasSize(2)),
                        model().attribute("cars", hasItem(car1)),
                        model().attribute("cars", hasItem(car2)));

        verify(carService).getAll();
    }

    @Test
    void testCarListPageIfEmpty() throws Exception {
        List<Car> cars = new ArrayList<>();
        when(carService.getAll()).thenReturn(cars);

        mvc.perform(get("/car/list"))
                .andExpectAll(status().isOk(),
                        view().name("carList"),
                        model().attribute("cars", hasSize(0)));

        verify(carService).getAll();
    }

    @Test
    void testUpdateCarPage() throws Exception {
        when(carService.getById(car1.getCarId())).thenReturn(car1);
        mvc.perform(get("/car/edit/" + car1.getCarId()))
                .andExpectAll(status().isOk(),
                        view().name("editCar"),
                        model().attributeExists("car"));

        verify(carService).getById(car1.getCarId());
    }

    @Test
    void testUpdateCarPageNotFound() throws Exception {
        when(carService.getById(car1.getCarId())).thenThrow(CarNotFoundException.class);
        mvc.perform(get("/car/edit/" + car1.getCarId()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));
        verify(carService).getById(car1.getCarId());
    }

    @Test
    void testUpdateCarPost() throws Exception {
        when(carService.update(car1)).thenReturn(car1);
        mvc.perform(post("/car/edit").flashAttr("car", car1))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));
        verify(carService).update(car1);
    }

    @Test
    void testUpdateCarPostIfNotFound() throws Exception {
        when(carService.update(car1)).thenThrow(CarNotFoundException.class);
        mvc.perform(post("/car/edit").flashAttr("car", car1))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));
        verify(carService).update(car1);
    }

    @Test
    void testUpdateCarPostIfNotValid() throws Exception {
        mvc.perform(post("/car/edit"))
                .andExpectAll(status().isOk(),
                        view().name("editCar"));
    }

    @Test
    void testDeleteCarPost() throws Exception {
        when(carService.getById(car1.getCarId())).thenReturn(car1);
        when(carService.delete(car1)).thenReturn(car1);

        mvc.perform(post("/car/delete").param("carId", car1.getCarId()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));

        verify(carService).delete(car1);
        verify(carService).getById(car1.getCarId());
    }

    @Test
    void testDeleteCarPostIfIdNotFound() throws Exception {
        when(carService.getById(car1.getCarId())).thenThrow(CarNotFoundException.class);

        mvc.perform(post("/car/delete").param("carId", car1.getCarId()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));

        verify(carService).getById(car1.getCarId());
    }

    @Test
    void testDeleteCarPostIfNotValid() throws Exception {
        when(carService.getById(car1.getCarId())).thenReturn(car1);
        when(carService.delete(car1)).thenThrow(CarNotFoundException.class);

        mvc.perform(post("/car/delete").param("carId", car1.getCarId()))
                .andExpectAll(status().is3xxRedirection(),
                        redirectedUrl("list"));

        verify(carService).getById(car1.getCarId());
        verify(carService).delete(car1);
    }
}
