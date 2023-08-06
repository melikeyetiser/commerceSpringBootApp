package com.allianz.commercespringboot.service;

import com.allianz.commercespringboot.database.entity.BillEntity;
import com.allianz.commercespringboot.database.entity.OrderEntity;
import com.allianz.commercespringboot.database.entity.ProductEntity;
import com.allianz.commercespringboot.database.repository.BillRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Service
public class BillService {

    @Autowired
    BillRepository billRepository;

    public BillEntity createBill(Long customerID, OrderEntity order) {
        BillEntity bill = new BillEntity();
        bill.setCustomerID(customerID);
        BigDecimal totalPriceWithoutTax = BigDecimal.ZERO;
        BigDecimal totalPriceWithTax = BigDecimal.ZERO;

        for (ProductEntity product : order.getProductEntityList()) {
            totalPriceWithoutTax = totalPriceWithoutTax.add(product.getTaxFreePrice());
            totalPriceWithTax = totalPriceWithTax.add(product.getTaxAddedPrice());
        }

        bill.setTotalPriceWithoutTax(totalPriceWithoutTax);
        bill.setTotalPriceWithTax(totalPriceWithTax);

        return bill;
    }

    public List<BillEntity> getAll() {
        return billRepository.findAll();
    }
}
