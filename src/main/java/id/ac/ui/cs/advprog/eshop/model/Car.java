package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.*;

@Getter @Setter
public class Car {
    private String carId;

    @NotBlank(message = "Please input a car name.")
    private String carName;

    @NotBlank(message = "Please input a car color.")
    private String carColor;

    @NotNull(message = "Please input a quantity")
    @PositiveOrZero(message = "Car quantity must be greater than 0.")
    private int carQuantity;
}
