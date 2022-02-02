package com.example.demo.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemTable, Long> {
    @Query(value = "select * from item_table where url = :url and phone = :phone ", nativeQuery = true)
    public List<ItemTable> findByUrl(String url, Long phone);

    @Query(value = "select * from item_table where site = :site and phone = :phone ", nativeQuery = true)
    public List<ItemTable> findBySite(String site, Long phone);

    @Query(value = "select * from item_table where phone = :phone ", nativeQuery = true)
    public List<ItemTable> findByPhone(Long phone);

    @Query(value = "select * from item_table where id = :id and phone = :phone", nativeQuery = true)
    public ItemTable getItemByIdAndPhone(Long id, Long phone);
}
