package com.example.demo.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<ItemTable, String> {
    @Query(value = "select * from item_table where url = :url ", nativeQuery = true)
    public ItemTable findByUrl(String url);
}
