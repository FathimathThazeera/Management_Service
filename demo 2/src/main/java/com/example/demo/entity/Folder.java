package com.example.demo.entity;

import com.example.demo.repository.table.FolderTable;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class Folder {
    @NotNull
    private final String folder;
    private final String note;

    public FolderTable toFolderTable() {
        return new FolderTable(this.folder, this.note);
    }
}
