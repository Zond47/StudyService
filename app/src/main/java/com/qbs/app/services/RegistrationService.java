package com.qbs.app.services;

import com.qbs.app.model.requests.RegistrationRequest;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    public String register(RegistrationRequest request) {
        return "Works!";
    }
}
