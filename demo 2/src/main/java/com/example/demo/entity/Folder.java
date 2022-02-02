package com.example.demo.entity;

import com.example.demo.repository.table.FolderTable;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Folder {
    private final Long id;
    @NotNull
    private final String folder;
    private final String note;
    private final Long phone;

    public FolderTable toFolderTable() {
        return new FolderTable(this.id, this.folder, this.note, this.phone);
    }
}
