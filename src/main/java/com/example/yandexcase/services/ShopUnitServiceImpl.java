package com.example.yandexcase.services;


import com.example.yandexcase.ShopUnit;
import com.example.yandexcase.ShopUnitImport;
import com.example.yandexcase.ShopUnitImportRequest;
import com.example.yandexcase.ShopUnitType;
import com.example.yandexcase.entity.ShopUnitDTO;
import com.example.yandexcase.entity.ShopUnitImportDTO;
import com.example.yandexcase.mappers.ShopUnitMapper;
import com.example.yandexcase.repositories.ShopUnitRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


import java.time.OffsetDateTime;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ShopUnitServiceImpl {


    private final ShopUnitRepository shopUnitRepository;

    private final ShopUnitMapper shopUnitMapper;

    public void initShopUnit(){
        ShopUnitDTO shopUnit = new ShopUnitDTO();
        shopUnit.setId(UUID.randomUUID());
        shopUnit.setName("Bname");
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        shopUnit.setDate(OffsetDateTime.now().minusDays(200));
        shopUnit.setPrice(4477L);
        shopUnit.setType(ShopUnitType.CATEGORY);
        shopUnitRepository.save(shopUnit);
        shopUnit.setId(UUID.randomUUID());
        shopUnit.setName("Aname");
        shopUnit.setDate(OffsetDateTime.now());
        shopUnit.setPrice(122L);
        shopUnit.setType(ShopUnitType.CATEGORY);
        shopUnitRepository.save(shopUnit);
    }
    public List<ShopUnitDTO> getAllShopUnits(){
        return shopUnitRepository.findAll();
    }

    public void addShopUnits(ShopUnitImportRequest shopUnitImportRequest){
        shopUnitRepository.saveAll(shopUnitImportRequest.getItems().stream()
                .map(shopUnitMapper::mapToEntity).peek(s->s.setDate(shopUnitImportRequest.getUpdateDate())).collect(Collectors.toList()));
    }
}
