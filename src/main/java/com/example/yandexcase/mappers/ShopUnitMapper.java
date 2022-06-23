package com.example.yandexcase.mappers;

import com.example.yandexcase.ShopUnit;
import com.example.yandexcase.ShopUnitImport;
import com.example.yandexcase.ShopUnitStatisticUnit;
import com.example.yandexcase.entity.ShopUnitDTO;
import com.example.yandexcase.entity.ShopUnitStatistic;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class ShopUnitMapper {
    @BeforeMapping
    void generateId( @MappingTarget ShopUnitStatistic shopUnitStatistic){
        if(shopUnitStatistic.getStatisticUnitId()==null){
            shopUnitStatistic.setStatisticUnitId(UUID.randomUUID());
        }
    }
    public  abstract ShopUnitDTO mapFromShopUnitImportToShopUnitDTO(ShopUnitImport shopUnitImport);
    public  abstract ShopUnitImport mapFromShopUnitDTOToShopUnitImport(ShopUnitDTO shopUnit);
    @Mapping(source = "id", target = "shopUnitId")
    public  abstract ShopUnitStatistic mapFromShopUnitImportToShopUnitStatistic(ShopUnitImport shopUnitImport);
    @Mapping(source = "shopUnitId", target = "id")
    public  abstract ShopUnitImport mapFromShopUnitStatisticToShopUnitImport(ShopUnitStatistic shopUnitStatistic);

    @Mapping(source = "id", target = "shopUnitId")
    public  abstract ShopUnitStatistic mapFromShopUnitStatisticUnitToShopUnitStatistic(ShopUnitStatisticUnit shopUnitStatisticUnitDTO);
    @Mapping(source = "shopUnitId", target = "id")
    public  abstract ShopUnitStatisticUnit mapFromShopUnitStatisticToShopUnitStatisticUnit(ShopUnitStatistic shopUnitStatistic);


    public  abstract ShopUnitDTO mapFromShopUnitToShopUnitDTO (ShopUnit shopUnit);
    public  abstract ShopUnit mapFromShopUnitDTOToShopUnit(ShopUnitDTO shopUnit);

    public  abstract ShopUnitDTO mapFromShopUnitStatisticUnitToShopUnitDTO (ShopUnitStatisticUnit shopUnitStatisticUnit);
    public  abstract ShopUnitStatisticUnit mapFromShopUnitDTOToShopUnitStatisticUnit(ShopUnitDTO shopUnit);



}
