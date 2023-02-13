package com.deepak.horsetrack.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="INVENTORY")
@Data
@NoArgsConstructor
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public Inventory(int denomination, int billCount) {
        this.denomination = denomination;
        this.billCount = billCount;
    }

    @Column
    private int denomination;

    @Column
    private int billCount;
}
