package com.spring.health.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@ToString
@Document(collection = "prescription_settings")
public class PrescriptionSettingsInfo extends BaseClass {

  @Transient
  public static final String SEQUENCE_NAME = "prescription_settings_id_sequence";


  private String hospitalName;
  private String prescriptionPageSize;
  private Boolean footerShow;
  private Boolean medicationUnitShow;
  private Boolean isBannerImage;
  private int maximumDose;
}
