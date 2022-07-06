package ro.ubbcluj.thesis.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Lob;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ro.ubbcluj.thesis.domain.Portal} entity.
 */
public class PortalDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 32)
    private String portalName;

    @NotNull
    private Boolean visibility;

    @Lob
    private String description;

    @Size(max = 256)
    private String imageUrl;

    private LocalDate lastUpdated;

    @Min(value = 0)
    private Integer timesAccessed;

    private Set<AppUserDTO> followers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPortalName() {
        return portalName;
    }

    public void setPortalName(String portalName) {
        this.portalName = portalName;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDate getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDate lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public Integer getTimesAccessed() {
        return timesAccessed;
    }

    public void setTimesAccessed(Integer timesAccessed) {
        this.timesAccessed = timesAccessed;
    }

    public Set<AppUserDTO> getFollowers() {
        return followers;
    }

    public void setFollowers(Set<AppUserDTO> followers) {
        this.followers = followers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PortalDTO)) {
            return false;
        }

        PortalDTO portalDTO = (PortalDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, portalDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PortalDTO{" +
            "id=" + getId() +
            ", portalName='" + getPortalName() + "'" +
            ", visibility='" + getVisibility() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", lastUpdated='" + getLastUpdated() + "'" +
            ", timesAccessed=" + getTimesAccessed() +
            ", followers=" + getFollowers() +
            "}";
    }
}
