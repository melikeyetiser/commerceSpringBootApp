package com.allianz.commercespringboot.database.entity;

import com.allianz.commercespringboot.util.dbutil.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class CustomerEntity extends BaseEntity {
    @Column
    @AttributeOverride(
            name = "id",
            column = @Column(
                    name = "customer_id"
            )
    )
    @Id
    private Long id;

    @Column
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<OrderEntity> orderList;

}
