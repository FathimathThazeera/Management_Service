package com.example.demo.repository.table;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<FolderTable, Long> {
    @Query(value = "select * from folder_table where folder = :folder and phone = :phone", nativeQuery = true)
    public List<FolderTable> getByFolderList(String folder, Long phone);

    @Query(value = "select * from folder_table where id = :id and phone = :phone", nativeQuery = true)
    public FolderTable getFolderByIdAndPhone(Long id, Long phone);

    @Query(value = "select * from folder_table where phone = :phone ", nativeQuery = true)
    public List<FolderTable> findByPhone(Long phone);
}
