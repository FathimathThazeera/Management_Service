package com.example.demo.entity;

import com.example.demo.repository.table.ItemTable;
import lombok.Data;

@Data
public class Item {
    private final String url;
    private final String site;
    private final String user;
    private final String password;
    private final String notes;
    private final String folder;

    public ItemTable toItemTable(String folder) {
        return new ItemTable(this.url, this.site, this.user, this.password, this.notes, folder);
    }
}
