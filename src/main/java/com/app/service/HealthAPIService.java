package com.app.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HealthAPIService {

    private final RestTemplate restTemplate;
    private static final String HEALTHAPI_URL = "http://example.com/healthai/icd10";

    public HealthAPIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String proxyICD10Request(Object requestBody) {
        return restTemplate.postForObject(HEALTHAPI_URL, requestBody, String.class);
    }
}
