package ro.ubbcluj.thesis.service.criteria;

import java.io.Serializable;
import java.util.Objects;
import org.springdoc.api.annotations.ParameterObject;
import tech.jhipster.service.Criteria;
import tech.jhipster.service.filter.BooleanFilter;
import tech.jhipster.service.filter.DoubleFilter;
import tech.jhipster.service.filter.Filter;
import tech.jhipster.service.filter.FloatFilter;
import tech.jhipster.service.filter.IntegerFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link ro.ubbcluj.thesis.domain.Flashcard} entity. This class is used
 * in {@link ro.ubbcluj.thesis.web.rest.FlashcardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /flashcards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class FlashcardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter thumbnailUrl;

    private StringFilter frontImageUrl;

    private StringFilter frontText;

    private StringFilter backImageUrl;

    private StringFilter backText;

    private LongFilter deckId;

    private Boolean distinct;

    public FlashcardCriteria() {}

    public FlashcardCriteria(FlashcardCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.thumbnailUrl = other.thumbnailUrl == null ? null : other.thumbnailUrl.copy();
        this.frontImageUrl = other.frontImageUrl == null ? null : other.frontImageUrl.copy();
        this.frontText = other.frontText == null ? null : other.frontText.copy();
        this.backImageUrl = other.backImageUrl == null ? null : other.backImageUrl.copy();
        this.backText = other.backText == null ? null : other.backText.copy();
        this.deckId = other.deckId == null ? null : other.deckId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public FlashcardCriteria copy() {
        return new FlashcardCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public LongFilter id() {
        if (id == null) {
            id = new LongFilter();
        }
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitle() {
        return title;
    }

    public StringFilter title() {
        if (title == null) {
            title = new StringFilter();
        }
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getThumbnailUrl() {
        return thumbnailUrl;
    }

    public StringFilter thumbnailUrl() {
        if (thumbnailUrl == null) {
            thumbnailUrl = new StringFilter();
        }
        return thumbnailUrl;
    }

    public void setThumbnailUrl(StringFilter thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public StringFilter getFrontImageUrl() {
        return frontImageUrl;
    }

    public StringFilter frontImageUrl() {
        if (frontImageUrl == null) {
            frontImageUrl = new StringFilter();
        }
        return frontImageUrl;
    }

    public void setFrontImageUrl(StringFilter frontImageUrl) {
        this.frontImageUrl = frontImageUrl;
    }

    public StringFilter getFrontText() {
        return frontText;
    }

    public StringFilter frontText() {
        if (frontText == null) {
            frontText = new StringFilter();
        }
        return frontText;
    }

    public void setFrontText(StringFilter frontText) {
        this.frontText = frontText;
    }

    public StringFilter getBackImageUrl() {
        return backImageUrl;
    }

    public StringFilter backImageUrl() {
        if (backImageUrl == null) {
            backImageUrl = new StringFilter();
        }
        return backImageUrl;
    }

    public void setBackImageUrl(StringFilter backImageUrl) {
        this.backImageUrl = backImageUrl;
    }

    public StringFilter getBackText() {
        return backText;
    }

    public StringFilter backText() {
        if (backText == null) {
            backText = new StringFilter();
        }
        return backText;
    }

    public void setBackText(StringFilter backText) {
        this.backText = backText;
    }

    public LongFilter getDeckId() {
        return deckId;
    }

    public LongFilter deckId() {
        if (deckId == null) {
            deckId = new LongFilter();
        }
        return deckId;
    }

    public void setDeckId(LongFilter deckId) {
        this.deckId = deckId;
    }

    public Boolean getDistinct() {
        return distinct;
    }

    public void setDistinct(Boolean distinct) {
        this.distinct = distinct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FlashcardCriteria that = (FlashcardCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(thumbnailUrl, that.thumbnailUrl) &&
            Objects.equals(frontImageUrl, that.frontImageUrl) &&
            Objects.equals(frontText, that.frontText) &&
            Objects.equals(backImageUrl, that.backImageUrl) &&
            Objects.equals(backText, that.backText) &&
            Objects.equals(deckId, that.deckId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, thumbnailUrl, frontImageUrl, frontText, backImageUrl, backText, deckId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FlashcardCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (thumbnailUrl != null ? "thumbnailUrl=" + thumbnailUrl + ", " : "") +
            (frontImageUrl != null ? "frontImageUrl=" + frontImageUrl + ", " : "") +
            (frontText != null ? "frontText=" + frontText + ", " : "") +
            (backImageUrl != null ? "backImageUrl=" + backImageUrl + ", " : "") +
            (backText != null ? "backText=" + backText + ", " : "") +
            (deckId != null ? "deckId=" + deckId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
