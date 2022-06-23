package com.example.yandexcase.repositories;

import com.example.yandexcase.entity.ShopUnitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
public interface ShopUnitRepository extends JpaRepository<ShopUnitDTO, UUID> {
    @Modifying
    @Transactional
    @Query(nativeQuery = true, value =
            "UPDATE shop_unit" +
            "   SET price = sub.avg" +
            "   FROM (WITH RECURSIVE r AS (" +
            "   SELECT DISTINCT id, parent_id, name,cast(price as numeric) as avg" +
            "   FROM shop_unit" +
            "   WHERE type = 'OFFER'" +
            "   UNION" +
            "   SELECT DISTINCT shop_unit.id, shop_unit.parent_id, shop_unit.name, AVG(r.avg) OVER (PARTITION by shop_unit.id) AS avg" +
            "   FROM shop_unit" +
            "      JOIN r" +
            "          ON shop_unit.id = r.parent_id" +
            ")" +
            "SELECT * FROM r) AS sub" +
            " WHERE shop_unit.id = sub.id")
    public void refresh();
}
