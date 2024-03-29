package com.qbs.app.repositories;

import com.qbs.app.security.token.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
  Optional<ConfirmationToken> findByToken(final String token);

  @Transactional
  @Modifying
  @Query("UPDATE ConfirmationToken c SET c.confirmedAt = ?2 WHERE c.token = ?1")
  int updateConfirmedAt(final String token, final LocalDateTime confirmedTime);
}
