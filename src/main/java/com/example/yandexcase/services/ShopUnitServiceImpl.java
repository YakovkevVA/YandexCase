package com.example.yandexcase.services;


import com.example.yandexcase.*;
import com.example.yandexcase.entity.ShopUnitDTO;
import com.example.yandexcase.entity.ShopUnitStatistic;
import com.example.yandexcase.mappers.ShopUnitMapper;
import com.example.yandexcase.repositories.ShopUnitRepository;
import com.example.yandexcase.repositories.ShopUnitStatisticUnitRepository;
import com.example.yandexcase.utils.StructureViolationException;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.time.OffsetDateTime;

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

    public ShopUnitStatisticResponse getShopUnits(OffsetDateTime dateStart, OffsetDateTime dateEnd) {
        ShopUnitStatisticResponse shopUnitStatisticResponse= new ShopUnitStatisticResponse();
        shopUnitStatisticResponse.setItems(shopUnitRepository.findAllByDateAfterAndDateBefore(dateStart,dateEnd).stream().map(shopUnitMapper::mapFromShopUnitDTOToShopUnitStatisticUnit).collect(Collectors.toList()));
        if(shopUnitStatisticResponse.getItems()==null || shopUnitStatisticResponse.getItems().isEmpty()) throw new NoSuchElementException("Item not found");
        return shopUnitStatisticResponse;
    }

    public ShopUnit getShopUnit(UUID id) {
        return shopUnitMapper.mapFromShopUnitDTOToShopUnit(shopUnitRepository.findById(id).orElseThrow(()-> new NoSuchElementException("Item not found")));
    }

    public void addShopUnits(ShopUnitImportRequest shopUnitImportRequest){
        checkShopUnitStructure(shopUnitImportRequest);

        shopUnitRepository.saveAll(shopUnitImportRequest.getItems().stream()
                .map(shopUnitMapper::mapFromShopUnitImportToShopUnitDTO).peek(s->s.setDate(shopUnitImportRequest.getUpdateDate())).collect(Collectors.toList()));

        shopUnitStatisticUnitRepository.saveAll(shopUnitImportRequest.getItems().stream()
                .map(shopUnitMapper::mapFromShopUnitImportToShopUnitStatistic).peek(s->s.setDate(shopUnitImportRequest.getUpdateDate())).collect(Collectors.toList()));

        shopUnitRepository.refresh();
    }

    public void deleteShopUnit(UUID id) {
        shopUnitRepository.deleteById(id);
        staticticUnitSafeDelete(id);
    }

    public ShopUnitStatisticResponse getStatistic(UUID id, OffsetDateTime after, OffsetDateTime before) {
        ShopUnitStatisticResponse shopUnitStatisticResponse= new ShopUnitStatisticResponse();
        shopUnitStatisticResponse.setItems(shopUnitStatisticUnitRepository.findAllByShopUnitIdAndDateAfterAndDateBeforeAndDeletedIsFalse(id,after,before).stream().map(shopUnitMapper::mapFromShopUnitStatisticToShopUnitStatisticUnit).collect(Collectors.toList()));
        if(shopUnitStatisticResponse.getItems()==null || shopUnitStatisticResponse.getItems().isEmpty()) throw new NoSuchElementException("Item not found");
        return shopUnitStatisticResponse;

    }

    private void checkShopUnitStructure(ShopUnitImportRequest shopUnitImportRequest){
        for (ShopUnitImport shopUnitImport:shopUnitImportRequest.getItems()) {
            ShopUnitDTO oldShopUnit = null;
            try {
                oldShopUnit = shopUnitMapper.mapFromShopUnitToShopUnitDTO(getShopUnit(shopUnitImport.getId()));
            } catch (NoSuchElementException e) {
                break;
            }
            if(!oldShopUnit.getType().equals(shopUnitImport.getType())){
                throw new StructureViolationException("Сhange tag is not allowed");
            }
            if(shopUnitImport.getParentId()!=null && oldShopUnit.getParentId()!=shopUnitImport.getParentId()){
                ShopUnitDTO newShopUnitParent=null;
                try {
                    newShopUnitParent = shopUnitMapper.mapFromShopUnitToShopUnitDTO(getShopUnit(shopUnitImport.getParentId()));
                } catch (NoSuchElementException e) {
                    break;
                }

                if(newShopUnitParent.getType().equals(ShopUnitType.OFFER)){
                    throw new StructureViolationException("Offer cannot be a parent");
                }
            }

        }
    }

    private void staticticUnitSafeDelete(UUID id){
        List<ShopUnitStatistic> shopUnitStatistics=shopUnitStatisticUnitRepository.findAllByShopUnitId(id);
        for (ShopUnitStatistic shopUnitStatistic:
                shopUnitStatistics) {
            if(shopUnitStatistic!=null){
                shopUnitStatistic.setDeleted(true);
                shopUnitStatisticUnitRepository.save(shopUnitStatistic);
            }
        }
    }
}
