package com.example.demo.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<FolderTable, String> {
    @Query(value = "select * from folder_table where folder = :folder", nativeQuery = true)
    public FolderTable getFolder(String folder);
}
