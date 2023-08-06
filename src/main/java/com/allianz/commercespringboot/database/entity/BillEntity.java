package com.allianz.commercespringboot.database.entity;

import com.allianz.commercespringboot.util.dbutil.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.HashMap;

@Entity
@Table
@Data
public class BillEntity extends BaseEntity {
    @Column
    @AttributeOverride(
            name = "id",
            column = @Column(
                    name = "bill_id"
            )
    )
    @Id
    private int id;

    @Column
    private Long customerID;

    @Column
    private BigDecimal totalPriceWithoutTax;

    @Column
    private BigDecimal totalPriceWithTax;


}
