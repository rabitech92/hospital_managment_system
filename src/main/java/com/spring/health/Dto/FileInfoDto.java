package com.spring.health.Dto;

import lombok.Data;



@Data
public class FileInfoDto {
    private String id;
    private String filename;
    private String contentType;
    private long size;
}
