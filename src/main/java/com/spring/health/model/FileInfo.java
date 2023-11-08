package com.spring.health.model;

import lombok.*;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Document(collection = "files")
public class FileInfo extends BaseClass{

    private String filename;
    private String contentType;
    private long size;
    private String filePath;
    private String fileLocation;
    private String fileType;
    private Date uploadDate;
    private String entity;
    private ObjectId entityRowId;

}
