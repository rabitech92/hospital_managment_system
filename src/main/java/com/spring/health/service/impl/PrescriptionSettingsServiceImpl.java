package com.spring.health.service.impl;

import com.spring.health.Dto.PrescriptionSettingsInfoRequest;
import com.spring.health.Dto.Response;
import com.spring.health.enums.Status;
import com.spring.health.model.PrescriptionSettingsInfo;
import com.spring.health.repository.PrescriptionSettingsRepository;
import com.spring.health.service.FilesService;
import com.spring.health.service.PrescriptionSettingsService;
import com.spring.health.util.ResponseBuilder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PrescriptionSettingsServiceImpl implements PrescriptionSettingsService {

    private final FilesService filesService;
    private final ModelMapper modelMapper;
    private final PrescriptionSettingsRepository repository;


//    @Override
//    public Response saveSettings(PrescriptionSettingsInfoRequest settingDto, MultipartFile file, String docName) {
//        List<PrescriptionSettingsInfo> infoList = repository.findAllByActiveStatus(Status.ACTIVE.getValue());
//        PrescriptionSettingsInfo settingsInfo;
//        if (!infoList.isEmpty()) {
//            settingsInfo = infoList.get(infoList.size()-1);
//            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
//            modelMapper.map(settingDto, settingsInfo);// 37 line is same but what is work to do same mapping
//        } else {
//            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
//            settingsInfo = modelMapper.map(settingDto, PrescriptionSettingsInfo.class);
//        }
//        if (settingsInfo.getIsBannerImage() && file == null) {
//            settingsInfo.setIsBannerImage(false);
//        }
//        settingsInfo.setActiveStatus(Status.ACTIVE.getValue());
//        repository.save(settingsInfo);
//        if (settingDto.getIsBannerImage() != null && settingDto.getIsBannerImage() && file != null) {
//            filesService.saveFile(docName, file, PrescriptionSettingsInfo.class, settingsInfo.getId());
//        } else if (settingDto.getIsBannerImage() != null && !settingDto.getIsBannerImage()) {
//            filesService.deleteImageIfExists(PrescriptionSettingsInfo.class.getName(), settingsInfo.getId());
//        }
//        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "your request has been save", null);
//    }
}
