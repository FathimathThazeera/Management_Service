package com.example.demo.repository.table;

import com.example.demo.entity.Folder;
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
@Table(name = "folder_table")
@NoArgsConstructor
public class FolderTable {
    @Id
    private String folder;
    private String note;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public FolderTable(String folder, String note) {
        this.folder = folder;
        this.note = note;
    }

    public Folder toFolder() {
        return new Folder(this.folder, this.note);
    }
}
