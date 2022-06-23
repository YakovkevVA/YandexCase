package com.example.yandexcase.entity;

import com.example.yandexcase.ShopUnitType;
import com.example.yandexcase.utils.ShopUnitValidation;
import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "shop_unit")
@ShopUnitValidation
public class ShopUnitDTO {
    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private OffsetDateTime date;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ShopUnitType type;

    @Column
    private UUID parentId;

    @Column
    private Long price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parentId")
    private List<ShopUnitDTO> childrens;


}
