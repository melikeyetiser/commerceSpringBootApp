package com.allianz.commercespringboot.controller;

import com.allianz.commercespringboot.database.entity.BillEntity;
import com.allianz.commercespringboot.database.entity.CustomerEntity;
import com.allianz.commercespringboot.database.entity.OrderEntity;
import com.allianz.commercespringboot.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bill")
public class BillController {

    @Autowired
    BillService billService;

    @PostMapping("create-bill/{customerID}")
    public ResponseEntity<BillEntity> createBillForOrder(@RequestBody OrderEntity order, @PathVariable Long customerID) {
        BillEntity bill = billService.createBill(customerID, order);
        return new ResponseEntity<>(bill, HttpStatus.CREATED);
    }

    @GetMapping("list-all")
    public ResponseEntity<List<BillEntity>> getAllBills() {
        List<BillEntity> billEntities = billService.getAll();
        return new ResponseEntity<>(billEntities, HttpStatus.OK);
    }
}
