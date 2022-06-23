package com.example.yandexcase.controllers;

import com.example.yandexcase.ShopUnit;
import com.example.yandexcase.ShopUnitImportRequest;
import com.example.yandexcase.ShopUnitStatisticResponse;
import com.example.yandexcase.ShopUnitStatisticUnit;
import com.example.yandexcase.entity.ShopUnitDTO;
import com.example.yandexcase.services.ShopUnitServiceImpl;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/openapi/v1.0/")
@RequiredArgsConstructor
@Validated
public class ShopUnitController {

    private final ShopUnitServiceImpl shopUnitService;

    @GetMapping("/sales")
    public ShopUnitStatisticResponse getSales(@RequestParam(name = "datestart", required = false)
                                          @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateStart,
                                      @RequestParam(name = "dateend", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateEnd){
        if (dateStart == null) dateStart = OffsetDateTime.from(Instant.ofEpochMilli(0).atZone(ZoneId.systemDefault()));
        if (dateEnd == null) dateEnd = OffsetDateTime.now();
        return shopUnitService.getShopUnits(dateStart, dateEnd);
    }

    @GetMapping("node/{id}/statistic")
    public ShopUnitStatisticResponse getStatistic(@PathVariable UUID id,
                                                  @RequestParam(name = "datestart", required = false)
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateStart,
                                                  @RequestParam(name = "dateend", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime dateEnd) {
        if (dateStart == null) dateStart = OffsetDateTime.from(Instant.ofEpochMilli(0).atZone(ZoneId.systemDefault()));
        if (dateEnd == null) dateEnd = OffsetDateTime.now();
        return shopUnitService.getStatistic(id, dateStart, dateEnd);
    }

    @GetMapping("/nodes/{id}")
    public ShopUnit getShopUnit(@PathVariable UUID id){
        return shopUnitService.getShopUnit(id);
    }

    @PostMapping("/imports")
    public void addShopUnits(@RequestBody ShopUnitImportRequest shopUnitImportRequest){
        shopUnitService.addShopUnits(shopUnitImportRequest);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteShopUnit(@PathVariable UUID id){
        shopUnitService.deleteShopUnit(id);
    }
}
