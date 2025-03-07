package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderService orderService;

    private Map<String, String> paymentOrderRelation = new HashMap<String, String>();

    public Payment addPayment(Order order, String paymentMethod, Map<String, String> paymentData) {
        String orderId = order.getId();
        String paymentId = UUID.randomUUID().toString();
        paymentOrderRelation.put(paymentId, orderId);

        String paymentStatus = PaymentStatus.WAITING_PAYMENT.getValue();
        Payment payment = new Payment(paymentId, paymentMethod, paymentStatus, paymentData );
        paymentRepository.addPayment(payment);

        return payment;
    }

    public Payment setStatus(Payment payment, String status) {
        paymentRepository.setStatus(payment, status);

        if(status.equals(PaymentStatus.SUCCESS.getValue()) || status.equals(PaymentStatus.REJECTED.getValue())) {
            changeOrderStatus(payment.getId(), status);
        }

        return payment;
    }

    public Payment getPayment(String id) {
        return paymentRepository.getPaymentById(id);
    }

    public List<Payment> getAllPayments(){
        return paymentRepository.getAllPayments();
    }

    private void changeOrderStatus(String paymentId, String status) {
        String orderId = paymentOrderRelation.get(paymentId);
        if (status.equals(PaymentStatus.SUCCESS.getValue())) {
            orderService.updateStatus(orderId, OrderStatus.SUCCESS.getValue());
        } else {
            orderService.updateStatus(orderId, OrderStatus.FAILED.getValue());
        }
    }
}
