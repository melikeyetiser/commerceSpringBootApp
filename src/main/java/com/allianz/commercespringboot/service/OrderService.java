package com.allianz.commercespringboot.service;

import com.allianz.commercespringboot.database.entity.CustomerEntity;
import com.allianz.commercespringboot.database.entity.OrderEntity;
import com.allianz.commercespringboot.database.entity.ProductEntity;
import com.allianz.commercespringboot.database.repository.CustomerRepository;
import com.allianz.commercespringboot.database.repository.OrderRepository;
import com.allianz.commercespringboot.model.enums.OrderStatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerService customerService;

    public OrderEntity placeOrder(Long customerId, OrderEntity order) {
        Optional<CustomerEntity> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            // add order to the customer's order list
            if (checkStocksForOrderConfirmation(order)) {
                order.setOrderStatusEnum(OrderStatusEnum.IN_PROGRESS);
                orderRepository.save(order);
                customerService.addOrderToCustomer(order, customerId);
                return order;
            } else {
                // this is when check stock amount method returns false for some product in the order list
                System.out.println("There is not enough stock amount for this order!");
                return null;
            }
        } else {
            System.out.println("Customer not found!");
            return null;
        }

    }

    public boolean checkStocksForOrderConfirmation(OrderEntity orderEntity) {
        boolean flag = true;
        for (ProductEntity product : orderEntity.getProductEntityList()) {
            if (product.getStockAmount() < 1) {
                flag = false;
                break;
            }
        }
        if (flag) {
            //orderEntity.setOrderStatusEnum(OrderStatusEnum.CONFIRMED);
            return true;
        } else {
            orderEntity.setOrderStatusEnum(OrderStatusEnum.REJECTED);
            return false;
        }

    }

    public OrderEntity findById(Long id) {
        if (orderRepository.findById(id).isPresent()) {
            return orderRepository.findById(id).get();
        } else {
            return null;
        }

    }

    // update stock amount for each product in the order
    public void updateStocks(OrderEntity order) {
        for (ProductEntity product : order.getProductEntityList()) {
            product.setStockAmount(product.getStockAmount() - 1);
        }
    }

    public boolean deleteOrder(Long orderID) {
        if (findById(orderID) != null) {
            orderRepository.deleteById(orderID);
            System.out.println("Order by order ID: " + orderID + " is deleted!");
            return true;
        } else {
            System.out.println("Order could not be found!");
            return false;
        }
    }
}
