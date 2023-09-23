package com.spring.health.mapper;

import com.spring.health.Dto.ImageDataDto;
import com.spring.health.model.ImageData;

import java.util.List;
import java.util.stream.Collectors;

public class FileStorageMapper {

    public ImageDataDto toDto(ImageData imageData){
        ImageDataDto imageDataDto=CommonMapper.mapDtoToEntity(imageData,ImageDataDto.class);
        return imageDataDto;
    }

    public ImageData toEntity(ImageDataDto imageDataDto){
        ImageData imageData =CommonMapper.mapDtoToEntity(imageDataDto,ImageData.class);
        return imageData;
    }

    public List<ImageDataDto> toDtoList(List<ImageData> imageDataList){
        return imageDataList.stream().map(this::toDto).collect(Collectors.toList());
    }


}
