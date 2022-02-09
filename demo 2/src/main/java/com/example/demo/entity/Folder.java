package com.example.demo.entity;

import com.example.demo.repository.table.FolderTable;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

@Data
public class Folder {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @NotNull
    private final String folder;
    private final String note;
    @NotNull
    private final Long userId;

    public FolderTable toFolderTable() {
        return new FolderTable(this.id, this.folder, this.note, this.userId);
    }
}
