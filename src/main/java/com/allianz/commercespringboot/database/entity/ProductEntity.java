package com.allianz.commercespringboot.database.entity;

import com.allianz.commercespringboot.util.dbutil.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table
@Data
public class ProductEntity extends BaseEntity {
    @Column
    @AttributeOverride(
            name = "id",
            column = @Column(
                    name = "product_id"
            )
    )
    @Id
    private Long id;

    @Column
    private String name;

    @Column
    private int stockAmount;

    @Column
    private BigDecimal taxFreePrice;

    @Column
    private BigDecimal taxAddedPrice;

    @Column
    private double taxRate;

    @JsonIgnore
    @ManyToMany(mappedBy = "productEntityList")
    private List<OrderEntity> orderEntityList;
}
