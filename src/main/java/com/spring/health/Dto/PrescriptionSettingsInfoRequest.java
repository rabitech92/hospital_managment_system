package com.spring.health.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PrescriptionSettingsInfoRequest {

  private String hospitalName;
  private String prescriptionPageSize;
  private Boolean footerShow;
  private Boolean medicationUnitShow;
  private Boolean isBannerImage;
  private int maximumDose;
}
