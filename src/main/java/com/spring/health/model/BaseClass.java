package com.spring.health.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.*;


import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
public class BaseClass {

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
    private Boolean activeStatus = true;


}
