package com.app.controller;

import com.app.service.HealthAPIService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/healthai")
@RequiredArgsConstructor
public class HealthAPIController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthAPIController.class);
    private final HealthAPIService healthAPIService;

    @PostMapping("/icd10")
    public ResponseEntity<?> proxyICD10Request(@RequestBody Object request) {
        LOGGER.debug("Call healthai/icd10 API");
        try {
            // Proxy the request to the actual Health API or service
            String response = healthAPIService.proxyICD10Request(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error processing request: " + e.getMessage());
        }
    }
}
