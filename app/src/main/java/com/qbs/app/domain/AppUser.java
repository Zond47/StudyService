package com.qbs.app.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.qbs.app.domain.enums.AppUserRole;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class AppUser implements UserDetails {

  @Id
  @SequenceGenerator(
      name = "appUsers_sequence",
      sequenceName = "appUsers_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appUsers_sequence")
  private Long Id;

  private String firstName;
  private String lastName;
  private String email;
  private String password;

  @Enumerated(EnumType.STRING)
  private AppUserRole userRole;

  @OneToMany(
      mappedBy = "postId",
      cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
      fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<UserPost> posts;

  @OneToMany(
          mappedBy = "executor",
          cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE},
          fetch = FetchType.LAZY)
  @JsonManagedReference
  private List<Comment> comments;

  private Boolean locked = false;
  private Boolean enabled = false;

  public AppUser(
      String firstName, String lastName, String email, String password, AppUserRole userRole) {
    this.posts = new ArrayList<>();
    this.comments = new ArrayList<>();
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.userRole = userRole;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
    return Collections.singletonList(authority);
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return firstName;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return !locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return enabled;
  }
}
