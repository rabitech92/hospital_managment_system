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
    private final PrescriptionSettingsRepository prescriptionSettingsRepository;


    @Override
    public Response saveSettings(PrescriptionSettingsInfoRequest request, MultipartFile file, String docName) {
        List<PrescriptionSettingsInfo> infoList = prescriptionSettingsRepository.findAllByActiveStatus(Status.ACTIVE.getValue());
        PrescriptionSettingsInfo prescriptionSettingsInfo;
        if (!infoList.isEmpty()) {
            prescriptionSettingsInfo = infoList.get(infoList.size()-1);
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            modelMapper.map(request, prescriptionSettingsInfo);// 37 line is same but what is work to do same mapping
        } else {
            modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
            prescriptionSettingsInfo = modelMapper.map(request, PrescriptionSettingsInfo.class);
        }
        if (request.getIsBannerImage() && file == null) {
            request.setIsBannerImage(false);
        }
        prescriptionSettingsInfo.setActiveStatus(Status.ACTIVE.getValue());
        prescriptionSettingsRepository.save(prescriptionSettingsInfo);
        if (request.getIsBannerImage() != null && request.getIsBannerImage() && file != null) {
            filesService.saveFile(docName, file, PrescriptionSettingsInfo.class, prescriptionSettingsInfo.getId());
        } else if (request.getIsBannerImage() != null && !request.getIsBannerImage()) {
            filesService.deleteImageIfExists(PrescriptionSettingsInfo.class.getName(), prescriptionSettingsInfo.getId());
        }
        return ResponseBuilder.getSuccessResponse(HttpStatus.OK, "your request has been save", null);
    }
}
