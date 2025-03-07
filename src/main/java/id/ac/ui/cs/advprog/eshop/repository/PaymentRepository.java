package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.List;

public interface PaymentRepository {
    public void addPayment(Payment payment);
    public void setStatus(Payment payment, String status);
    public Payment getPaymentById(String id);
    public List<Payment> getAllPayments();
}
