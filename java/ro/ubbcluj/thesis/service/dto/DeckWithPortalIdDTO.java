package ro.ubbcluj.thesis.service.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DeckWithPortalIdDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 64)
    private String title;

    @NotNull
    private Boolean visibility;

    @Lob
    private String description;

    @Size(max = 256)
    private String imageUrl;

    private LocalDate lastUpdated;

    private Long portalId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getPortalId() {
        return portalId;
    }

    public void setPortalId(Long portalId) {
        this.portalId = portalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeckWithPortalIdDTO)) {
            return false;
        }

        DeckWithPortalIdDTO deckWithPortalIdDTO = (DeckWithPortalIdDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, deckWithPortalIdDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeckDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", visibility='" + getVisibility() + "'" +
            ", description='" + getDescription() + "'" +
            ", imageUrl='" + getImageUrl() + "'" +
            ", lastUpdated='" + getLastUpdated() + "'" +
            ", portal=" + getPortalId() +
            "}";
    }
}
