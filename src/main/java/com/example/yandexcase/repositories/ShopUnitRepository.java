package com.example.yandexcase.repositories;

import com.example.yandexcase.entity.ShopUnitDTO;
import com.example.yandexcase.entity.ShopUnitImportDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopUnitRepository extends JpaRepository<ShopUnitDTO,String> {
}
