package com.allianz.commercespringboot.model;

import com.allianz.commercespringboot.database.entity.ProductEntity;
import com.allianz.commercespringboot.model.enums.OrderStatusEnum;

import java.util.List;

public class Order {
    private int orderID;
    private Customer customer;
    private OrderStatusEnum orderStatusEnum;
    private List<ProductEntity> productEntityList;

}
