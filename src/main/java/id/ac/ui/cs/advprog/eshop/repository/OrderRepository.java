package id.ac.ui.cs.advprog.eshop.repository;

import org.springframework.stereotype.Repository;
import id.ac.ui.cs.advprog.eshop.model.Order;

import java.util.List;
import java.util.ArrayList;

@Repository
public class OrderRepository {
    private List<Order> orderData = new ArrayList<>();

    public Order save(Order order) {
        int ii = 0;
        for(Order savedOrder : orderData) {
            if(savedOrder.getId().equals(order.getId())) {
                orderData.remove(ii);
                orderData.add(ii, order);
                return order;
            }
            ii += 1;
        }

        orderData.add(order);
        return order;
    }

    public Order findById(String id){
        for(Order savedOrder : orderData) {
            if (savedOrder.getId().equals(id)) {
                return savedOrder;
            }
        }
        return null;
    }
    public List<Order> findAllByAuthor(String author){
        List<Order> result = new ArrayList<>();
        for(Order savedOrder : orderData) {
            if(savedOrder.getAuthor().equals(author)) {
                result.add(savedOrder);
            }
        }

        return result;
    }
}
