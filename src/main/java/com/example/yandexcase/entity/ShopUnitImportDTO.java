package com.example.yandexcase.entity;

import com.example.yandexcase.ShopUnitType;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "shop_unit_import")
public class ShopUnitImportDTO {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column
    private OffsetDateTime date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShopUnitType type;

    @Column
    private UUID parentId;

    @Column
    private Long price;


}
