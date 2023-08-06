package com.allianz.commercespringboot.model;

import com.allianz.commercespringboot.database.entity.OrderEntity;

import java.math.BigDecimal;
import java.util.List;

public class Product {
    private Long id;
    private String name;
    private int stockAmount;
    private BigDecimal taxFreePrice;
    private BigDecimal taxAddedPrice;
    private double taxRate;
    private List<OrderEntity> orderEntityList;
}
