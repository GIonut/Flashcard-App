package ro.ubbcluj.thesis.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ro.ubbcluj.thesis.domain.AppUser} entity.
 */
public class AppUserDTO implements Serializable {

    private Long id;

    private LocalDate lastLogin;

    @Min(value = 0)
    private Integer invalidLogins;

    private LocalDate lastInvalidLogin;

    private UserDTO appUser;

    private PortalDTO owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getInvalidLogins() {
        return invalidLogins;
    }

    public void setInvalidLogins(Integer invalidLogins) {
        this.invalidLogins = invalidLogins;
    }

    public LocalDate getLastInvalidLogin() {
        return lastInvalidLogin;
    }

    public void setLastInvalidLogin(LocalDate lastInvalidLogin) {
        this.lastInvalidLogin = lastInvalidLogin;
    }

    public UserDTO getAppUser() {
        return appUser;
    }

    public void setAppUser(UserDTO appUser) {
        this.appUser = appUser;
    }

    public PortalDTO getOwner() {
        return owner;
    }

    public void setOwner(PortalDTO owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUserDTO)) {
            return false;
        }

        AppUserDTO appUserDTO = (AppUserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, appUserDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserDTO{" +
            "id=" + getId() +
            ", lastLogin='" + getLastLogin() + "'" +
            ", invalidLogins=" + getInvalidLogins() +
            ", lastInvalidLogin='" + getLastInvalidLogin() + "'" +
            ", appUser=" + getAppUser() +
            ", owner=" + getOwner() +
            "}";
    }
}
