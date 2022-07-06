package ro.ubbcluj.thesis.service.dto;

import java.io.Serializable;
import java.util.Objects;
import javax.validation.constraints.*;

/**
 * A DTO for the {@link ro.ubbcluj.thesis.domain.Flashcard} entity.
 */
public class FlashcardDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 32)
    private String title;

    @Size(max = 256)
    private String thumbnailUrl;

    @Size(max = 256)
    private String frontImageUrl;

    @Size(max = 1024)
    private String frontText;

    @Size(max = 256)
    private String backImageUrl;

    @Size(max = 1024)
    private String backText;

    private DeckDTO deck;

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

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getFrontImageUrl() {
        return frontImageUrl;
    }

    public void setFrontImageUrl(String frontImageUrl) {
        this.frontImageUrl = frontImageUrl;
    }

    public String getFrontText() {
        return frontText;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackImageUrl() {
        return backImageUrl;
    }

    public void setBackImageUrl(String backImageUrl) {
        this.backImageUrl = backImageUrl;
    }

    public String getBackText() {
        return backText;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public DeckDTO getDeck() {
        return deck;
    }

    public void setDeck(DeckDTO deck) {
        this.deck = deck;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FlashcardDTO)) {
            return false;
        }

        FlashcardDTO flashcardDTO = (FlashcardDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, flashcardDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlashcardDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", thumbnailUrl='" + getThumbnailUrl() + "'" +
            ", frontImageUrl='" + getFrontImageUrl() + "'" +
            ", frontText='" + getFrontText() + "'" +
            ", backImageUrl='" + getBackImageUrl() + "'" +
            ", backText='" + getBackText() + "'" +
            "}";
    }
}
