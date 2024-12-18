package com.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Document {

    @Id
    @GeneratedValue
    private Long id;

    private Long applicationID;
    private Long clientID;
    private String docType;
    private String fileName;
    private String fileSize;
    private String fileType;
    private String s3Url;
    private LocalDateTime createdAt;
}
