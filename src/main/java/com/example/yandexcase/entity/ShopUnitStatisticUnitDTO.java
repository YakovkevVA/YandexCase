package com.example.yandexcase.entity;

import com.example.yandexcase.ShopUnitType;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class ShopUnitStatisticUnitDTO {

    private UUID id;


    private String name;


    private UUID parentId;


    private ShopUnitType type;


    private Long price;


    private OffsetDateTime date;

}
