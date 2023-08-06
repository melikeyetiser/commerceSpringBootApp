package com.allianz.commercespringboot.model;
import java.math.BigDecimal;

public class Bill {
    private int id;
    private Long customerID;
    private BigDecimal totalPriceWithoutTax;
    private BigDecimal totalPriceWithTax;
}
