package com.allianz.commercespringboot.controller;

import com.allianz.commercespringboot.database.entity.OrderEntity;
import com.allianz.commercespringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("place-order/{customerID}")
    public ResponseEntity<OrderEntity> placeOrder(@RequestBody OrderEntity orderEntity,
                                                  @PathVariable Long customerID) {
        if (orderService.placeOrder(customerID, orderEntity) == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(orderEntity, HttpStatus.OK);
        }
    }

    @PutMapping("finalize-order/{orderID}")
    public ResponseEntity<Boolean> finalizeOrder(@PathVariable Long orderID) {
        if (orderService.findById(orderID) == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            OrderEntity orderEntity = orderService.findById(orderID);
            if (orderService.checkStocksForOrderConfirmation(orderEntity)) {
                orderService.updateStocks(orderEntity);
                return new ResponseEntity<>(true, HttpStatus.OK);
            } else {
                System.out.println("Order cannot be approved!");
                return new ResponseEntity<>(false, HttpStatus.NOT_ACCEPTABLE);
            }
        }

    }

    @DeleteMapping("delete-order/{orderID}")
    public ResponseEntity<Boolean> deleteOrder(@PathVariable Long orderID) {
        if (orderService.deleteOrder(orderID)) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

    }
}
