package com.spring.health.Dto;

import com.spring.health.model.BaseClass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PrescriptionSettingsInfoResponse extends BaseClass {

  private String hospitalName;
  private String prescriptionPageSize;
  private String banner;
  private Boolean footerShow;
  private Boolean medicationUnitShow;
  private Boolean isBannerImage;
  private int maximumDose;
}
