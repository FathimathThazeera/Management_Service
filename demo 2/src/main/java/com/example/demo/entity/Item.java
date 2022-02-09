package com.example.demo.entity;

import com.example.demo.repository.table.ItemTable;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private final Long id;
    @NotBlank
    @URL
    private final String url;
    @NotBlank
    private final String site;
    @NotBlank
    private final String user;
    @NotBlank
    private final String password;
    private final String notes;
    @NotNull
    private final Long folder;
    @NotNull
    private final Long userId;

    public ItemTable toItemTable(Long folder) {
        return new ItemTable(this.id, this.url, this.site, this.user, this.password, this.notes, folder, this.userId);
    }
}
