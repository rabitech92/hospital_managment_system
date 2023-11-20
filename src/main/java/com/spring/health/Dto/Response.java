package com.spring.health.Dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class Response extends Throwable {
  private long timeStamp;

  private int statusCode;

  private String status;

  private String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Object content;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private int numberOfElement;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private Long rowCount;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private List<ErrorResponseDto> errors;
}
