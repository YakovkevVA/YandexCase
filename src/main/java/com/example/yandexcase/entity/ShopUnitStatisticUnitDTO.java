package com.example.yandexcase.entity;

import com.example.yandexcase.ShopUnitType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
public class ShopUnitStatisticUnitDTO {
    @Id
    private UUID id;

    @Column
    private String name;

    @Column
    private UUID parentId;

    @Column
    private ShopUnitType type;

    @Column
    private Long price;

    @Column
    private OffsetDateTime date;

}
