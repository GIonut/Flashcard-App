package ro.ubbcluj.thesis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Flashcard.
 */
@Entity
@Table(name = "flashcard")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Flashcard implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 32)
    @Column(name = "title", length = 32, nullable = false)
    private String title;

    @Size(max = 256)
    @Column(name = "thumbnail_url", length = 256)
    private String thumbnailUrl;

    @Size(max = 256)
    @Column(name = "front_image_url", length = 256)
    private String frontImageUrl;

    @Size(max = 1024)
    @Column(name = "front_text", length = 1024)
    private String frontText;

    @Size(max = 256)
    @Column(name = "back_image_url", length = 256)
    private String backImageUrl;

    @Size(max = 1024)
    @Column(name = "back_text", length = 1024)
    private String backText;

    @ManyToOne
    @JsonIgnoreProperties(value = { "portal" }, allowSetters = true)
    private Deck deck;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Flashcard id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public Flashcard title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public Flashcard thumbnailUrl(String thumbnailUrl) {
        this.setThumbnailUrl(thumbnailUrl);
        return this;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getFrontImageUrl() {
        return this.frontImageUrl;
    }

    public Flashcard frontImageUrl(String frontImageUrl) {
        this.setFrontImageUrl(frontImageUrl);
        return this;
    }

    public void setFrontImageUrl(String frontImageUrl) {
        this.frontImageUrl = frontImageUrl;
    }

    public String getFrontText() {
        return this.frontText;
    }

    public Flashcard frontText(String frontText) {
        this.setFrontText(frontText);
        return this;
    }

    public void setFrontText(String frontText) {
        this.frontText = frontText;
    }

    public String getBackImageUrl() {
        return this.backImageUrl;
    }

    public Flashcard backImageUrl(String backImageUrl) {
        this.setBackImageUrl(backImageUrl);
        return this;
    }

    public void setBackImageUrl(String backImageUrl) {
        this.backImageUrl = backImageUrl;
    }

    public String getBackText() {
        return this.backText;
    }

    public Flashcard backText(String backText) {
        this.setBackText(backText);
        return this;
    }

    public void setBackText(String backText) {
        this.backText = backText;
    }

    public Deck getDeck() {
        return this.deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public Flashcard deck(Deck deck) {
        this.setDeck(deck);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Flashcard)) {
            return false;
        }
        return id != null && id.equals(((Flashcard) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Flashcard{" +
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
