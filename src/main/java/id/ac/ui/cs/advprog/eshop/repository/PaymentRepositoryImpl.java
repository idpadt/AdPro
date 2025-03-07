package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    List<Payment> payments = new ArrayList<>();

    @Override
    public void addPayment(Payment payment) {

    }

    @Override
    public void setStatus(Payment payment, String status) {

    }

    @Override
    public Payment getPaymentById(String id) {
        return null;
    }

    @Override
    public List<Payment> getAllPayments() {
        return null;
    }
}
