package com.spring.health.model;


import lombok.Data;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;


import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BaseClass implements Serializable {

    @Id
    private ObjectId id;
    private boolean isDeleted;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedBy
    private String LastModifiedBy;
    @LastModifiedDate
    private LocalDateTime lastModifyDate;
    private Integer activeStatus;


}
