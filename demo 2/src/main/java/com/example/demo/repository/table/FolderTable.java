package com.example.demo.repository.table;

import com.example.demo.entity.Folder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@Table(name = "folder_table")
@NoArgsConstructor
public class FolderTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String folder;
    private String note;
    private Long phone;
    @CreationTimestamp
    @Column(name = "created_at")
    private Instant createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public FolderTable(Long id, String folder, String note, Long phone) {
        this.id = id;
        this.folder = folder;
        this.note = note;
        this.phone = phone;
    }

    public Folder toFolder() {
        return new Folder(this.id, this.folder, this.note, this.phone);
    }
}
