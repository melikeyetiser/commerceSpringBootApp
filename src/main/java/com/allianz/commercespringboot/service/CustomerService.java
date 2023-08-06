package com.allianz.commercespringboot.service;

import com.allianz.commercespringboot.database.entity.CustomerEntity;
import com.allianz.commercespringboot.database.entity.OrderEntity;
import com.allianz.commercespringboot.database.repository.BaseRepository;
import com.allianz.commercespringboot.database.repository.CustomerRepository;
import com.allianz.commercespringboot.util.dbutil.BaseEntity;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity createCustomer(String name, Long id) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setName(name);
        customerEntity.setId(id);
        // save new customer to the database
        customerRepository.save(customerEntity);
        return customerEntity;
    }

    // update an existing customer
    public CustomerEntity updateCustomerByID(Long customerID, CustomerEntity updatedCustomer) {
        // get customer by ID then update it
        // we cannot use optional here to update fields, so we need to create another CustomerEntity
        // via getting it from the optional value when present.
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerID);
        if (customerEntity != null) {
            CustomerEntity customer = customerEntity.get();
            customer.setName(updatedCustomer.getName());
            // save updated customer
            customerRepository.save(customer);
            return customer;
        } else {
            return null;
        }
    }

    public List<CustomerEntity> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<CustomerEntity> getCustomersByNameContaining(String key) {
        return customerRepository.findAllByNameContainingIgnoreCase(key);
    }

    @Transactional
    @Modifying
    public void deleteCustomerByID(Long id) {
        customerRepository.deleteById(id);
    }

    // will be used to check existence before deleting a customer
    public boolean exists(Long id) {
        return customerRepository.existsById(id);
    }


    // add order to the customer's list
    public boolean addOrderToCustomer(OrderEntity order, Long customerId) {
        Optional<CustomerEntity> customerEntity = customerRepository.findById(customerId);
        if (customerEntity.isPresent()) {
            CustomerEntity customer = customerEntity.get();
            List<OrderEntity> orders = customer.getOrderList();
            orders.add(order);
            customer.setOrderList(orders);
            return true;
        } else {
            return false;
        }
    }
}
