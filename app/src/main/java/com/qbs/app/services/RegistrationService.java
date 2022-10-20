package com.qbs.app.services;

import com.qbs.app.model.AppUser;
import com.qbs.app.model.enums.AppUserRole;
import com.qbs.app.model.requests.RegistrationRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService userService;

    public String register(RegistrationRequest request) {
        if (request.getFirstName().isEmpty() || request.getLastName().isEmpty() ||request.getEmail().isEmpty() ||request.getPassword().isEmpty()) {
            throw new IllegalStateException("Input fields cannot be empty!");
        }
        return userService.signUpUser(
                new AppUser(request.getFirstName(),
                            request.getLastName(),
                            request.getEmail(),
                            request.getPassword(),
                            AppUserRole.STUDENT
                ));
    }

}
