package com.allianz.commercespringboot.model;
import com.allianz.commercespringboot.database.entity.OrderEntity;
import java.util.List;

public class Customer {
    private Long id;
    private String name;
    private List<OrderEntity> orderList;
}
