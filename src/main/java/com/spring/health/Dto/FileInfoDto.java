package com.spring.health.Dto;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FileInfoDto {
    private String id;
    private String filename;
    private String contentType;
    private long size;
    private String filePath;
}
