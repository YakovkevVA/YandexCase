package com.example.yandexcase.repositories;

import com.example.yandexcase.entity.ShopUnitStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

public interface ShopUnitStatisticUnitRepository extends JpaRepository<ShopUnitStatistic, UUID> {
    List<ShopUnitStatistic> findAllByShopUnitIdAndDateAfterAndDateBeforeAndDeletedIsFalse(UUID id, OffsetDateTime after, OffsetDateTime before);

    List<ShopUnitStatistic> findAllByShopUnitId(UUID id);


}
