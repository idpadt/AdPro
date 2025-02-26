package id.ac.ui.cs.advprog.eshop.exception;

public class CarNotFoundException extends RuntimeException {
    public CarNotFoundException(String message){
        super(message);
    }
}