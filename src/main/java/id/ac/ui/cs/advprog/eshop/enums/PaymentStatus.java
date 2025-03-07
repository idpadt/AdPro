package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    WAITING_PAYMENT("WAITING_PAYMENT"),
    REJECTED("REJECTED"),
    SUCCESS("SUCCESS");

    private final String value;

    PaymentStatus(String value) {
        this.value = value;
    }

    public static boolean contains(String param){
        for(PaymentStatus paymentStatus : PaymentStatus.values()){
            if(paymentStatus.name().equals(param)){
                return true;
            }
        }
        return false;
    }
}
