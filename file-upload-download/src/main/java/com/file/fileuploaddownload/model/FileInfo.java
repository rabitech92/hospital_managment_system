package com.file.fileuploaddownload.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String filename;
    private String contentType;
    private long size;
    private String filePath;
}
