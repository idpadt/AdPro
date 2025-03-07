package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import id.ac.ui.cs.advprog.eshop.exception.PaymentException;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {

    private String paymentId = "7a098fd-11kg4j-k7g4j-s97f";
    private String paymentMethod = "Payment by Voucher";
    private String paymentStatus = "WAITING_PAYMENT";
    private Map<String, String> paymentData = new HashMap<String, String>();

    @BeforeEach
    void setUp() {
        paymentData.put("voucherCode", "DISKON WOW");
    }

    @Test
    void testCreatePayment() {
        Payment payment = new Payment(paymentId, paymentMethod, paymentStatus, paymentData);
        assertEquals(paymentId, payment.getId());
        assertEquals(paymentMethod, payment.getMethod());
        assertEquals(paymentStatus, payment.getStatus());
        assertEquals(paymentData, payment.getData());
    }

    @Test
    void testCreatePaymentInvalidMethod(){
        paymentMethod = "Barter";
        assertThrows(PaymentException.class, () ->{
            Payment payment = new Payment(paymentId, paymentMethod, paymentStatus, paymentData);
        });
    }

    @Test
    void testCreatePaymentInvalidStatus(){
        paymentStatus = "SomeOtherStatus";
        assertThrows(PaymentException.class, () ->{
            Payment payment = new Payment(paymentId, paymentMethod, paymentStatus, paymentData);
        });
    }

    @Test
    void testSetPaymentStatus() {
        Payment payment = new Payment(paymentId, paymentMethod, paymentStatus, paymentData);
        paymentStatus = "SUCCESS";
        payment.setStatus(paymentStatus);
        assertEquals(paymentStatus, payment.getStatus());
    }

    @Test
    void testSetPaymentStatusInvalidStatus() {
        Payment payment = new Payment(paymentId, paymentMethod, paymentStatus, paymentData);
        paymentStatus = "SomeOtherStatus";
        assertThrows(PaymentException.class, () -> {
            payment.setStatus(paymentStatus);
        });
    }
}
