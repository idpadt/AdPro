package id.ac.ui.cs.advprog.eshop.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product {
    private String productId;

    @NotBlank(message = "Please input a product name.")
    private String productName;

    @NotNull(message = "Please input a product quantity.")
    @PositiveOrZero(message = "Product quantity must be greater than 0.")
    private Integer productQuantity;
}
