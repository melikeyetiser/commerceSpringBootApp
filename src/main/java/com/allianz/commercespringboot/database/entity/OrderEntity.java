package com.allianz.commercespringboot.database.entity;

import com.allianz.commercespringboot.model.enums.OrderStatusEnum;
import com.allianz.commercespringboot.util.dbutil.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table
@Data
public class OrderEntity extends BaseEntity {
    @Column
    @AttributeOverride(
            name = "id",
            column = @Column(
                    name = "order_id"
            )
    )
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @Column
    private OrderStatusEnum orderStatusEnum;

    @ManyToMany
    @JoinTable(name = "productEntityList",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<ProductEntity> productEntityList;
}
