package com.example.yandexcase.mappers;

import com.example.yandexcase.ShopUnit;
import com.example.yandexcase.ShopUnitImport;
import com.example.yandexcase.entity.ShopUnitDTO;
import com.example.yandexcase.entity.ShopUnitStatistic;
import com.example.yandexcase.entity.ShopUnitStatisticUnitDTO;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class ShopUnitMapper {
    @BeforeMapping
    void generateId( @MappingTarget ShopUnitStatistic shopUnitStatistic){
        if(shopUnitStatistic.getStatisticId()==null){
            shopUnitStatistic.setStatisticId(UUID.randomUUID());
        }
    }
    public  abstract ShopUnitDTO mapFromShopUnitImportToShopUnit (ShopUnitImport shopUnitImport);
    public  abstract ShopUnitImport mapFromShopUnitDTOToShopUnitImport(ShopUnitDTO shopUnit);

    public  abstract ShopUnitStatistic mapFromShopUnitImportToShopUnitStatistic(ShopUnitImport shopUnitImport);
    public  abstract ShopUnitImport mapFromShopUnitStatisticToShopUnitImport(ShopUnitStatistic shopUnitStatistic);


    public  abstract ShopUnitStatistic mapFromShopUnitStatisticUnitDTOToShopUnitStatistic(ShopUnitStatisticUnitDTO shopUnitStatisticUnitDTO);
    public  abstract ShopUnitStatisticUnitDTO mapFromShopUnitStatisticToShopUnitStatisticUnitDTO(ShopUnitStatistic shopUnitStatistic);

}
