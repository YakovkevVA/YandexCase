package com.example.yandexcase.repositories;

import com.example.yandexcase.entity.ShopUnitStatistic;
import com.example.yandexcase.entity.ShopUnitStatisticUnitDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface ShopUnitStatisticUnitRepository extends JpaRepository<ShopUnitStatistic, OffsetDateTime> {
    public List<ShopUnitStatistic> findAllByIdAndDateAfterAndDateBefore(UUID id, OffsetDateTime after, OffsetDateTime before);
}
