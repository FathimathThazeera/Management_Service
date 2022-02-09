package com.example.demo.repository.table;

import com.example.demo.entity.Item;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Table(name = "item_table")
@NoArgsConstructor
public class ItemTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String site;
    private String user;
    private String password;
    private String notes;
    private Long folder;
    private Long userId;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public ItemTable(Long id, String url, String site, String user, String password, String notes, Long folder, Long userId) {
        this.id = id;
        this.url = url;
        this.site = site;
        this.user = user;
        this.password = password;
        this.notes = notes;
        this.folder = folder;
        this.userId = userId;
    }

    public Item toItem() {
        return new Item(this.id, this.url, this.site, this.user, this.password, this.notes, this.folder, this.userId);
    }
}
