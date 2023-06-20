package com.qbs.app.repositories;

import com.qbs.app.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  Optional<AppUser> findByEmail(final String email);

  @Transactional
  @Modifying
  @Query("UPDATE AppUser a SET a.enabled = TRUE WHERE a.email = ?1")
  int enableAppUser(final String email);
}
