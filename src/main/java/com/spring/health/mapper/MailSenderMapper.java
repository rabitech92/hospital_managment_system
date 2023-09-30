package com.spring.health.mapper;

import com.spring.health.Dto.MailSenderDto;
import com.spring.health.model.MailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderMapper {

    public MailSenderDto toDto(MailSender mailSender){
        MailSenderDto mailSenderDto = CommonMapper.mapEntityToDto(mailSender,MailSenderDto.class);
        return mailSenderDto;
    }

    public MailSender toEntity(MailSenderDto mailSenderDto){
        MailSender mailSender=CommonMapper.mapDtoToEntity(mailSenderDto,MailSender.class);
        return mailSender;
    }
}
