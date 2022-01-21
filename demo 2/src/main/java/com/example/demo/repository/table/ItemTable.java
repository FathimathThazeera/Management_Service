package com.example.demo.repository.table;

import com.example.demo.entity.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Data
@Table(name = "item_table")
@NoArgsConstructor
public class ItemTable {
    private String url;
    @Id
    private String site;
    private String user;
    private String password;
    private String notes;
    private String folder;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public ItemTable(String url, String site, String user, String password, String notes, String folder) {
        this.url = url;
        this.site = site;
        this.user = user;
        this.password = password;
        this.notes = notes;
        this.folder = folder;
    }

    public Item toItem() {
        return new Item(this.url, this.site, this.user, this.password, this.notes, this.folder);
    }
}
