package com.kpi.korolova.shop.service;

import com.kpi.korolova.shop.entities.Order;
import com.kpi.korolova.shop.entities.OrderedProduct;
import com.kpi.korolova.shop.exceptions.*;
import com.kpi.korolova.shop.model.OrderStatus;
import com.kpi.korolova.shop.repository.OrderRepository;
import com.kpi.korolova.shop.repository.OrderedProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private OrderedProductRepository orderedProductRepository;

    public void addOrder(Order order, boolean currCustomer) throws InvalidParamsException, CustomerNotFoundException, NotAuthorizedException, GenerateOrderNumberException {
        if(order == null || (currCustomer && order.getCustomerId() < 0) ||
                order.getDeliveryId() < 0 || order.getProducts() == null || order.getProducts().isEmpty()) {
            throw new InvalidParamsException(order == null
                    ? "Заказ не определён"
                    : order.toString());
        }
        if(currCustomer) {
            order.setCustomerId(customerService.getCurrentCustomer().getId());
        }
        order.setNumber(generateOrderNumber());
        order.setOrderDate(new Date());
        order.setStatus(OrderStatus.PENDING);
        for(OrderedProduct op : order.getProducts()) {
            op.setOrderId(order.getId());
        }
        orderRepository.save(order);
    }

    private int generateOrderNumber() throws GenerateOrderNumberException {
        Random random = new Random();
        int result = 0;
        int i = 0;
        do {
            if (++i == 10) {
                throw new GenerateOrderNumberException("Не получилось сгенерировать номер заказа. Попробуйте снова");
            }
            result = random.nextInt(Integer.MAX_VALUE / 1_000_000) + 1_000_000;
        } while (orderRepository.existsOrderByNumber(result));
        return result;
    }

    public List<Order> getOrdersByCustomerId(int customerId) {
        if(customerId < 1) {
            //TODO:
        }
        List<Order> result = orderRepository.getOrdersByCustomerId(customerId);
        for(Order order : result) {
            order.getCustomer().setUser(null);
        }
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void editOrder(Order order) throws InvalidParamsException {
        if(order == null || order.getId() < 1) {
            throw new InvalidParamsException("");
        }
        orderedProductRepository.removeAllByOrderId(order.getId());
        for(OrderedProduct op : order.getProducts()) {
            if(op.getProductId() < 1) {
                op.setProductId(op.getProduct().getId());
            }
        }
        orderRepository.save(order);
    }

    public List<Order> getAllOrdersByStatus(OrderStatus status, String sort, boolean direction, int page, int size) {
        Sort s = Sort.by(direction ? Sort.Direction.ASC : Sort.Direction.DESC, sort);
        return orderRepository.getAllByStatus(status, PageRequest.of(page, size, s)).getContent();
    }

    public Order getOrderByOrderNumber(Integer number) throws InvalidParamsException, OrderNotFoundException {
        if(number == null || number < 1) {
            throw new InvalidParamsException(String.format("number %s", number));
        }
        if(!orderRepository.existsOrderByNumber(number)) {
            throw new OrderNotFoundException("Order was not found");
        }
        return orderRepository.getOrderByNumber(number);
    }
}
