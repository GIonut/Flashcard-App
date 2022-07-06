package ro.ubbcluj.thesis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A AppUser.
 */
@Entity
@Table(name = "app_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class AppUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "last_login")
    private LocalDate lastLogin;

    @Min(value = 0)
    @Column(name = "invalid_logins")
    private Integer invalidLogins;

    @Column(name = "last_invalid_login")
    private LocalDate lastInvalidLogin;

    @OneToOne
    @JoinColumn(unique = true)
    private User appUser;

    @ManyToOne
    @JsonIgnoreProperties(value = { "followers" }, allowSetters = true)
    private Portal owner;

    @ManyToMany(mappedBy = "followers")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "followers" }, allowSetters = true)
    private Set<Portal> portals = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AppUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLastLogin() {
        return this.lastLogin;
    }

    public AppUser lastLogin(LocalDate lastLogin) {
        this.setLastLogin(lastLogin);
        return this;
    }

    public void setLastLogin(LocalDate lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Integer getInvalidLogins() {
        return this.invalidLogins;
    }

    public AppUser invalidLogins(Integer invalidLogins) {
        this.setInvalidLogins(invalidLogins);
        return this;
    }

    public void setInvalidLogins(Integer invalidLogins) {
        this.invalidLogins = invalidLogins;
    }

    public LocalDate getLastInvalidLogin() {
        return this.lastInvalidLogin;
    }

    public AppUser lastInvalidLogin(LocalDate lastInvalidLogin) {
        this.setLastInvalidLogin(lastInvalidLogin);
        return this;
    }

    public void setLastInvalidLogin(LocalDate lastInvalidLogin) {
        this.lastInvalidLogin = lastInvalidLogin;
    }

    public User getAppUser() {
        return this.appUser;
    }

    public void setAppUser(User user) {
        this.appUser = user;
    }

    public AppUser appUser(User user) {
        this.setAppUser(user);
        return this;
    }

    public Portal getOwner() {
        return this.owner;
    }

    public void setOwner(Portal portal) {
        this.owner = portal;
    }

    public AppUser owner(Portal portal) {
        this.setOwner(portal);
        return this;
    }

    public Set<Portal> getPortals() {
        return this.portals;
    }

    public void setPortals(Set<Portal> portals) {
        if (this.portals != null) {
            this.portals.forEach(i -> i.removeFollower(this));
        }
        if (portals != null) {
            portals.forEach(i -> i.addFollower(this));
        }
        this.portals = portals;
    }

    public AppUser portals(Set<Portal> portals) {
        this.setPortals(portals);
        return this;
    }

    public AppUser addPortal(Portal portal) {
        this.portals.add(portal);
        portal.getFollowers().add(this);
        return this;
    }

    public AppUser removePortal(Portal portal) {
        this.portals.remove(portal);
        portal.getFollowers().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUser)) {
            return false;
        }
        return id != null && id.equals(((AppUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUser{" +
            "id=" + getId() +
            ", lastLogin='" + getLastLogin() + "'" +
            ", invalidLogins=" + getInvalidLogins() +
            ", lastInvalidLogin='" + getLastInvalidLogin() + "'" +
            "}";
    }
}
