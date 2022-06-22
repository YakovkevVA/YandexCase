package com.example.yandexcase.mappers;

import com.example.yandexcase.ShopUnit;
import com.example.yandexcase.ShopUnitImport;
import com.example.yandexcase.entity.ShopUnitDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShopUnitMapper {
    ShopUnitDTO mapToEntity(ShopUnitImport shopUnitImport);
    ShopUnitImport mapToImport(ShopUnitDTO shopUnit);
}
