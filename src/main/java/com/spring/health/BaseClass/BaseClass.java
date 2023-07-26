package com.spring.health.BaseClass;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.yaml.snakeyaml.events.Event;

import javax.persistence.*;
import java.time.LocalDateTime;


@MappedSuperclass
public class BaseClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Event.ID id;
    private String statusCode;
    private boolean isDeleted;
    @CreatedBy
    private String createdBy;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedBy
    private String LastModifiedBy;
    @LastModifiedDate
    private LocalDateTime lastModifyDate;




}
