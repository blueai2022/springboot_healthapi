package com.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
public class Application {

    @Id
    @GeneratedValue
    private Long id;

    private String agent;
    private boolean isFormal;
    private Boolean is1035Exchange;
    private Long primaryInsuredID;
    private Long jointInsuredID;
    private String carriers;
    private String productType;
    private long appliedAmount;
    private Date createdAt;
}
