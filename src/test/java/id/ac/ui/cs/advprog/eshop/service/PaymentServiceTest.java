package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.exception.PaymentException;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    PaymentServiceImpl paymentService;

    private String paymentId = UUID.randomUUID().toString();
    private String paymentMethod = PaymentMethod.BY_VOUCHER.getValue();
    private String paymentStatus = PaymentStatus.WAITING_PAYMENT.getValue();
    private Map<String, String> paymentData = new HashMap<>();
    private Order order1;
    private Payment payment1;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1788560800L, "Safira Sudrajat");

        payment1 = new Payment(paymentId, paymentMethod, paymentStatus, paymentData);
    }

    @Test
    void testAddPayment() {
        Payment payment = paymentService.addPayment(order1, paymentMethod, paymentData);

        assertEquals(paymentMethod, payment.getMethod());
        assertEquals(paymentData, payment.getData());

        verify(paymentRepository).addPayment(any(Payment.class));
    }

    @Test
    void testAddPaymentInvalidMethod(){
        assertThrows(PaymentException.class, () ->
                paymentService.addPayment(order1, "Barter", paymentData));
    }

    @Test
    void testSetStatus(){
        Payment payment = paymentService.setStatus(payment1, PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());

        verify(paymentRepository).setStatus(payment1, PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testSetStatusInvalidPayment(){
        assertThrows(PaymentException.class, () ->
                paymentService.setStatus(payment1, PaymentStatus.SUCCESS.getValue()));
    }

    @Test
    void testSetStatusInvalidMethod(){
        assertThrows(PaymentException.class, () ->
                paymentService.setStatus(payment1, "Lunas"));
    }

    @Test
    void testGetPayment(){
        Payment payment = paymentService.getPayment(payment1.getId());
        assertEquals(payment1, payment);

        verify(paymentRepository).getPaymentById(payment1.getId());
    }

    @Test
    void testGetPaymentInvalidId(){
        assertThrows(PaymentException.class, () ->
                paymentService.getPayment(payment1.getId()));
    }

    @Test
    void testGetAllPayments(){
        List<Payment> payments = paymentService.getAllPayments();
        assertTrue(payments.contains(payment1));

        verify(paymentRepository).getAllPayments();
    }

    @Test
    void testGetAllPaymentsEmpty(){
        List<Payment> payments = paymentService.getAllPayments();
        assertTrue(payments.isEmpty());

        verify(paymentRepository).getAllPayments();
    }
}
