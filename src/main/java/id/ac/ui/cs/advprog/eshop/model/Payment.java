package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> data;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
    }

    public void setStatus(String status){
    }
}
