package com.example.demo.repository.table;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemTable, Long> {
    @Query(value = "select * from item_table where url LIKE :url% and userId = :userId ", nativeQuery = true)
    public List<ItemTable> findByUrl(String url, Long userId);

    @Query(value = "select * from item_table where site = :site and userId = :userId ", nativeQuery = true)
    public List<ItemTable> findBySite(String site, Long userId);

    @Query(value = "select * from item_table where userId = :userId ", nativeQuery = true)
    public List<ItemTable> findByUserId(Long userId);

    @Query(value = "select * from item_table where userId = :userId ", nativeQuery = true)
    public List<ItemTable> pagination(Long userId, Pageable page);

    @Query(value = "select * from item_table where id = :id and userId = :userId", nativeQuery = true)
    public ItemTable getItemByIdAndUserId(Long id, Long userId);
}
