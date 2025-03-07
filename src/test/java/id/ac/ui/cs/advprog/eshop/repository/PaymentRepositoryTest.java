package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.exception.PaymentException;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    PaymentRepository paymentRepository;

    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        String id = "a08-kb43h-ds9h-ogfdiuya-524k";
        String method = PaymentMethod.BY_VOUCHER.getValue();
        String status = PaymentStatus.WAITING_PAYMENT.getValue();
        Map<String, String> data = new HashMap<>();
        payment1 = new Payment(id, method, status, data);

        id = "sd09g-kh4jg6-t5tuy-mnb86v9";
        method = PaymentMethod.BY_BANK_TRANSFER.getValue();
        status = PaymentStatus.WAITING_PAYMENT.getValue();
        data = new HashMap<>();
        payment2 = new Payment(id, method, status, data);
    }

    @Test
    void testAddAndGetPayment(){
        paymentRepository.addPayment(payment1);
        paymentRepository.addPayment(payment2);

        assertEquals(payment1, paymentRepository.getPaymentById(payment1.getId()));
        assertEquals(payment2, paymentRepository.getPaymentById(payment2.getId()));
    }

    @Test
    void testGetPaymentInvalidId(){
        paymentRepository.addPayment(payment1);

        assertThrows(PaymentException.class, () ->
                paymentRepository.getPaymentById(payment2.getId()));

    }

    @Test
    void testGetPaymentIfEmpty(){
        assertThrows(PaymentException.class, () ->
                paymentRepository.getPaymentById(payment1.getId()));
    }

    @Test
    void testGetALlPayments(){
        paymentRepository.addPayment(payment1);
        paymentRepository.addPayment(payment2);

        List<Payment> savedPayments = paymentRepository.getAllPayments();
        assertTrue(savedPayments.contains(payment1));
        assertTrue(savedPayments.contains(payment2));
    }

    @Test
    void testGetAllPaymentsIfEmpty(){
        List<Payment> savedPayments = paymentRepository.getAllPayments();
        assertTrue(savedPayments.isEmpty());
    }

    @Test
    void testSetStatus(){
        paymentRepository.addPayment(payment1);
        String status = PaymentStatus.SUCCESS.getValue();
        paymentRepository.setStatus(payment1, status);

        Payment savedPayment = paymentRepository.getPaymentById(payment1);
        payment1.setStatus(status);
        assertEquals(payment1, savedPayment);
    }

    @Test
    void testSetStatusInvalidId(){
        paymentRepository.addPayment(payment1);

        assertThrows(PaymentException.class, () ->
                paymentRepository.setStatus(payment2, PaymentStatus.SUCCESS.getValue()));
    }

    @Test
    void testSetStatusInvalidStatus(){
        paymentRepository.addPayment(payment1);

        assertThrows(PaymentException.class, () ->
                paymentRepository.setStatus(payment1, "Lunas"));
    }
}
