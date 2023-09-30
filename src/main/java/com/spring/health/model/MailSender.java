package com.spring.health.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mail-sender")
public class MailSender extends BaseClass {
    private String subject;
    private String message;
}
