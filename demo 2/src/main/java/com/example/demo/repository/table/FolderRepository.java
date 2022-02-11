package com.example.demo.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<FolderTable, Long> {
    @Query(value = "select * from folder_table where folder = :folder and userId = :userId", nativeQuery = true)
    public List<FolderTable> getByFolderList(String folder, Long userId);

    @Query(value = "select * from folder_table where id = :id and userId = :userId", nativeQuery = true)
    public FolderTable getFolderByIdAndUserId(Long id, Long userId);

    @Query(value = "select * from folder_table where userId = :userId ", nativeQuery = true)
    public List<FolderTable> findByUserId(Long userId);
}
