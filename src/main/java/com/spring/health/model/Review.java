package com.spring.health.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(collection = "review")
@NoArgsConstructor
@AllArgsConstructor
public class Review extends BaseClass {

    private String name;
    private String subject;
    private String from;
}
