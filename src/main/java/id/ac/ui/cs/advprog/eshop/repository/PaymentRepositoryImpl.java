package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exception.PaymentException;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepositoryImpl implements PaymentRepository {
    List<Payment> payments = new ArrayList<>();

    @Override
    public void addPayment(Payment payment) {
        payments.add(payment);
    }

    @Override
    public void setStatus(Payment payment, String status) {
        int index = getIndexById(payment.getId());
        Payment savedPayment = payments.get(index);
        savedPayment.setStatus(status);
        payments.set(index, savedPayment);
    }

    @Override
    public Payment getPaymentById(String id) {
        int index = getIndexById(id);
        return payments.get(index);
    }

    @Override
    public List<Payment> getAllPayments() {
        return payments;
    }

    private int getIndexById(String id) {
        for(int ii = 0; ii < payments.size(); ii++) {
            if (payments.get(ii).getId().equals(id)) {
                return ii;
            }
        }
        throw new PaymentException("Payment with id " + id + " not found");
    }
}
