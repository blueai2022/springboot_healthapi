package com.app.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class ApiAccount {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private boolean isActive;
    private boolean isAutoRenewal;

    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Enumerated(EnumType.STRING)
    private PlanName planName;

    private long creditBalance;
    private LocalDateTime activeAt;
    private LocalDateTime lastUseAt;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;

    // Enum for ServiceType (to match ICD|ICD_PRO|APS|APS_TXT|ALL)
    public enum ServiceType {
        ICD, ICD_PRO, APS, APS_TXT, ALL
    }

    // Enum for PlanName (to match DEMO|BASIC|PRO)
    public enum PlanName {
        DEMO, BASIC, PRO
    }
}
