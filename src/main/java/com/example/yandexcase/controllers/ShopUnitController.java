package com.example.yandexcase.controllers;

import com.example.yandexcase.ShopUnit;
import com.example.yandexcase.ShopUnitImportRequest;
import com.example.yandexcase.entity.ShopUnitDTO;
import com.example.yandexcase.services.ShopUnitServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openapi/v1.0/")
@RequiredArgsConstructor
public class ShopUnitController {

    private final ShopUnitServiceImpl shopUnitService;

    @PostMapping("/imports")
    public void addShopUnits(@RequestBody ShopUnitImportRequest shopUnitImportRequest){
        shopUnitService.addShopUnits(shopUnitImportRequest);
    }

    @GetMapping("/init")
    public void initShopUnit(){
        shopUnitService.initShopUnit();
    }

    @GetMapping("/nodes") //удоли
    public List<ShopUnitDTO> getAllShopUnits(){
        return shopUnitService.getAllShopUnits();
    }
}
