package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.exception.PaymentException;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> data;

    public Payment(String id, String method, String status, Map<String, String> data) {
        this.id = id;
        this.data = data;

        if (checkStatus(status)) {
            this.status = status;
        }

        if (checkMethod(method)) {
            this.method = method;
        }

    }

    public void setStatus(String status){
        if (checkStatus(status)) {
            this.status = status;
        }
    }

    private boolean checkStatus(String status){
        if(PaymentStatus.contains(status)){
            return true;
        }
        throw new PaymentException("Invalid status");
    }

    private boolean checkMethod(String method){
        if(PaymentMethod.contains(method)){
            return true;
        }
        throw new PaymentException("Invalid method");
    }
}
