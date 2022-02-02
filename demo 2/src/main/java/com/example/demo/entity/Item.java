package com.example.demo.entity;

import com.example.demo.repository.table.ItemTable;
import lombok.Data;

@Data
public class Item {
    private final Long id;
    private final String url;
    private final String site;
    private final String user;
    private final String password;
    private final String notes;
    private final Long folder;
    private final Long phone;

    public ItemTable toItemTable(Long folder) {
        return new ItemTable(this.id, this.url, this.site, this.user, this.password, this.notes, folder, this.phone);
    }
}
