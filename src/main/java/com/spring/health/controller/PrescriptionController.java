package com.spring.health.controller;

import com.spring.health.Dto.PrescriptionSettingsInfoRequest;
import com.spring.health.Dto.Response;
import com.spring.health.annotation.ApiController;
import com.spring.health.service.PrescriptionSettingsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@ApiController
@RequiredArgsConstructor
@RequestMapping("/prescription")
public class PrescriptionController {

    private final PrescriptionSettingsService service;

    @PostMapping("page")
    public Response settingPrescription(@ModelAttribute PrescriptionSettingsInfoRequest request, @RequestParam(value = "file",required = false) MultipartFile file){
        return service.saveSettings(request,file,"file");
    }

}
