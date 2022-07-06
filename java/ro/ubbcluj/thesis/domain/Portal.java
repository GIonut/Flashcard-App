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
import org.hibernate.annotations.Type;

/**
 * A Portal.
 */
@Entity
@Table(name = "portal")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Portal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 5, max = 32)
    @Column(name = "portal_name", length = 32, nullable = false, unique = true)
    private String portalName;

    @NotNull
    @Column(name = "visibility", nullable = false)
    private Boolean visibility;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column(name = "description")
    private String description;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Column(name = "last_updated")
    private LocalDate lastUpdated;

    @Min(value = 0)
    @Column(name = "times_accessed")
    private Integer timesAccessed;

    @ManyToMany
    @JoinTable(
        name = "rel_portal__follower",
        joinColumns = @JoinColumn(name = "portal_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "appUser", "owner", "portals" }, allowSetters = true)
    private Set<AppUser> followers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Portal id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPortalName() {
        return this.portalName;
    }

    public Portal portalName(String portalName) {
        this.setPortalName(portalName);
        return this;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
    }

    public Boolean getVisibility() {
        return this.visibility;
    }

    public Portal visibility(Boolean visibility) {
        this.setVisibility(visibility);
        return this;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return this.description;
    }

    public Portal description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public Portal imageUrl(String imageUrl) {
        this.setImageUrl(imageUrl);
        return this;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getLastUpdated() {
        return this.lastUpdated;
    }

    public Portal lastUpdated(LocalDate lastUpdated) {
        this.setLastUpdated(lastUpdated);
        return this;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getTimesAccessed() {
        return this.timesAccessed;
    }

    public Portal timesAccessed(Integer timesAccessed) {
        this.setTimesAccessed(timesAccessed);
        return this;
    }

    public void setTimesAccessed(Integer timesAccessed) {
        this.timesAccessed = timesAccessed;
    }

    public Set<AppUser> getFollowers() {
        return this.followers;
    }

    public void setFollowers(Set<AppUser> appUsers) {
        this.followers = appUsers;
    }

    public Portal followers(Set<AppUser> appUsers) {
        this.setFollowers(appUsers);
        return this;
    }

    public Portal addFollower(AppUser appUser) {
        this.followers.add(appUser);
        appUser.getPortals().add(this);
        return this;
    }

    public Portal removeFollower(AppUser appUser) {
        this.followers.remove(appUser);
        appUser.getPortals().remove(this);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Portal)) {
            return false;
        }
        return id != null && id.equals(((Portal) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Portal{" +
            "id=" + getId() +
            ", portalName='" + getPortalName() + "'" +
            ", visibility='" + getVisibility() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", lastUpdated='" + getLastUpdated() + "'" +
            ", timesAccessed=" + getTimesAccessed() +
            "}";
    }
}
