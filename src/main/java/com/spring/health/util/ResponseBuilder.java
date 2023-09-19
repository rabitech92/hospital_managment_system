package com.spring.health.util;

import com.spring.health.Dto.ErrorResponseDto;
import com.spring.health.Dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class ResponseBuilder {
    private ResponseBuilder() {
    }

    private static List<ErrorResponseDto> getCustomError(BindingResult result) {
        List<ErrorResponseDto> dtoList = new ArrayList<>();
        result.getFieldErrors().forEach(fieldError -> {
            ErrorResponseDto errorResponseDto = ErrorResponseDto.builder()
                    .field(fieldError.getField())
                    .message(fieldError.getDefaultMessage())
                    .build();
            dtoList.add(errorResponseDto);
        });
        return dtoList;
    }

    public static Response getFailureResponse(BindingResult result, String message) {
        return Response.builder()
                .message(message)
                .errors(getCustomError(result))
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(new Date().getTime())
                .build();
    }

    public static Response getFailureResponse(HttpStatus status, String message) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .timeStamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .content(content)
                .timeStamp(new Date().getTime())
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .content(content)
                .timeStamp(new Date().getTime())
                .numberOfElement(numberOfElement)
                .build();
    }

    public static Response getSuccessResponse(HttpStatus status, String message, Object content, int numberOfElement, Long rowCount) {
        return Response.builder()
                .message(message)
                .status(status.getReasonPhrase())
                .statusCode(status.value())
                .content(content)
                .timeStamp(new Date().getTime())
                .numberOfElement(numberOfElement)
                .rowCount(rowCount)
                .build();
    }
}
