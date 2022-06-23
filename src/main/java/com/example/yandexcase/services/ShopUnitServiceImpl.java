package com.example.yandexcase.services;


import com.example.yandexcase.ShopUnit;
import com.example.yandexcase.ShopUnitImport;
import com.example.yandexcase.ShopUnitImportRequest;
import com.example.yandexcase.ShopUnitType;
import com.example.yandexcase.entity.ShopUnitDTO;
import com.example.yandexcase.entity.ShopUnitStatistic;
import com.example.yandexcase.entity.ShopUnitStatisticUnitDTO;
import com.example.yandexcase.mappers.ShopUnitMapper;
import com.example.yandexcase.repositories.ShopUnitRepository;
import com.example.yandexcase.repositories.ShopUnitStatisticUnitRepository;
import com.example.yandexcase.utils.StructureViolationException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;


import java.time.OffsetDateTime;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShopUnitServiceImpl {


    private final ShopUnitRepository shopUnitRepository;

    private final ShopUnitMapper shopUnitMapper;

    private final ShopUnitStatisticUnitRepository shopUnitStatisticUnitRepository;

    public List<ShopUnitDTO> getAllShopUnits(){
        return shopUnitRepository.findAll();
    }

    public ShopUnitDTO getShopUnit(UUID id) {
        return shopUnitRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Item not found"));
    }

    public void addShopUnits(ShopUnitImportRequest shopUnitImportRequest){
        checkShopUnitStructure(shopUnitImportRequest);

        shopUnitRepository.saveAll(shopUnitImportRequest.getItems().stream()
                .map(shopUnitMapper::mapFromShopUnitImportToShopUnit).peek(s->s.setDate(shopUnitImportRequest.getUpdateDate())).collect(Collectors.toList()));

        shopUnitStatisticUnitRepository.saveAll(shopUnitImportRequest.getItems().stream()
                .map(shopUnitMapper::mapFromShopUnitImportToShopUnitStatistic).peek(s->s.setDate(shopUnitImportRequest.getUpdateDate())).collect(Collectors.toList()));

        shopUnitRepository.refresh();
    }

    public void deleteShopUnit(UUID id) {
        shopUnitRepository.deleteById(id);
    }

    public List<ShopUnitStatisticUnitDTO> getStatistic(UUID id, OffsetDateTime after, OffsetDateTime before) {
        return shopUnitStatisticUnitRepository.findAllByIdAndDateAfterAndDateBefore(id,after,before).stream().map(shopUnitMapper::mapFromShopUnitStatisticToShopUnitStatisticUnitDTO).collect(Collectors.toList());
    }
    public void saveShopUnitStatistic(ShopUnitStatistic shopUnitStatistic) {
        shopUnitStatisticUnitRepository.save(shopUnitStatistic);
    }


    public void checkShopUnitStructure(ShopUnitImportRequest shopUnitImportRequest){
        for (ShopUnitImport shopUnitImport:shopUnitImportRequest.getItems()) {
            ShopUnitDTO oldShopUnit = null;
            try {
                oldShopUnit = getShopUnit(shopUnitImport.getId());
            } catch (NoSuchElementException e) {
                break;
            }
            if(!oldShopUnit.getType().equals(shopUnitImport.getType())){
                throw new StructureViolationException("Сhange tag is not allowed");
            }
            if(shopUnitImport.getParentId()!=null && oldShopUnit.getParentId()!=shopUnitImport.getParentId()){
                ShopUnitDTO newShopUnitParent=null;
                try {
                    newShopUnitParent = getShopUnit(shopUnitImport.getParentId());
                } catch (NoSuchElementException e) {
                    break;
                }

                if(newShopUnitParent.getType().equals(ShopUnitType.OFFER)){
                    throw new StructureViolationException("Offer cannot be a parent");
                }
            }

        }
    }


}
