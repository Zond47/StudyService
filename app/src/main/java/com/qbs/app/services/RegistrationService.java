package com.qbs.app.services;

import com.qbs.app.model.AppUser;
import com.qbs.app.model.enums.AppUserRole;
import com.qbs.app.model.requests.RegistrationRequest;
import com.qbs.app.security.token.ConfirmationToken;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService userService;
    private final ConfirmationTokenService confirmationTokenService;

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
    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.findConfirmationToken(token).orElseThrow(() -> new IllegalStateException("Token not found!"));
        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if (expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired!");
        }

        confirmationTokenService.setConfirmedAt(token);
        userService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "Token confirmed! Account is enabled!";
    }

}
