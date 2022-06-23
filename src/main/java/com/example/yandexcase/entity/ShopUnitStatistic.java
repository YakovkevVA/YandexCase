package com.example.yandexcase.entity;

import com.example.yandexcase.ShopUnitType;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;
@Entity
@Data
public class ShopUnitStatistic {
    @Id
    @Column(nullable = false)
    private UUID statisticId;

    @Column(nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private UUID parentId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShopUnitType type;

    @Column
    private Long price;

    @Column(nullable = false)
    private OffsetDateTime date;
}
