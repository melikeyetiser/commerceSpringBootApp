package com.allianz.commercespringboot.controller;

import com.allianz.commercespringboot.database.entity.CustomerEntity;
import com.allianz.commercespringboot.service.CustomerService;
import com.allianz.commercespringboot.util.dbutil.BaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("add-customer")
    public ResponseEntity<CustomerEntity> createCustomer(@RequestBody CustomerEntity customerEntity) {
        CustomerEntity newCustomer = customerService.createCustomer(customerEntity.getName(), customerEntity.getId());
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @GetMapping("list-all")
    public ResponseEntity<List<CustomerEntity>> getAllCustomers() {
        List<CustomerEntity> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("{customerName}")
    public ResponseEntity<List<CustomerEntity>> findByName(@PathVariable String customerName) {
        List<CustomerEntity> customers = customerService.getCustomersByNameContaining(customerName);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @PutMapping("{customerID}")
    public ResponseEntity<CustomerEntity> updateCustomerByID(@PathVariable Long customerID,
                                                             @RequestBody CustomerEntity updatedCustomer) {
        CustomerEntity customer = customerService.updateCustomerByID(customerID, updatedCustomer);
        if (customer != null) {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{customerID}")
    public ResponseEntity<Long> deleteCustomer(@PathVariable Long customerID) {

        boolean exists = customerService.exists(customerID);
        if (exists) {
            customerService.deleteCustomerByID(customerID);
            return new ResponseEntity<>(customerID, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
