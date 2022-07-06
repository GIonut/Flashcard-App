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
import tech.jhipster.service.filter.LocalDateFilter;
import tech.jhipster.service.filter.LongFilter;
import tech.jhipster.service.filter.StringFilter;

/**
 * Criteria class for the {@link ro.ubbcluj.thesis.domain.Deck} entity. This class is used
 * in {@link ro.ubbcluj.thesis.web.rest.DeckResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /decks?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class DeckCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private BooleanFilter visibility;

    private StringFilter imageUrl;

    private LocalDateFilter lastUpdated;

    private LongFilter portalId;

    private Boolean distinct;

    public DeckCriteria() {}

    public DeckCriteria(DeckCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.title = other.title == null ? null : other.title.copy();
        this.visibility = other.visibility == null ? null : other.visibility.copy();
        this.imageUrl = other.imageUrl == null ? null : other.imageUrl.copy();
        this.lastUpdated = other.lastUpdated == null ? null : other.lastUpdated.copy();
        this.portalId = other.portalId == null ? null : other.portalId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public DeckCriteria copy() {
        return new DeckCriteria(this);
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

    public BooleanFilter getVisibility() {
        return visibility;
    }

    public BooleanFilter visibility() {
        if (visibility == null) {
            visibility = new BooleanFilter();
        }
        return visibility;
    }

    public void setVisibility(BooleanFilter visibility) {
        this.visibility = visibility;
    }

    public StringFilter getImageUrl() {
        return imageUrl;
    }

    public StringFilter imageUrl() {
        if (imageUrl == null) {
            imageUrl = new StringFilter();
        }
        return imageUrl;
    }

    public void setImageUrl(StringFilter imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LocalDateFilter getLastUpdated() {
        return lastUpdated;
    }

    public LocalDateFilter lastUpdated() {
        if (lastUpdated == null) {
            lastUpdated = new LocalDateFilter();
        }
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateFilter lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public LongFilter getPortalId() {
        return portalId;
    }

    public LongFilter portalId() {
        if (portalId == null) {
            portalId = new LongFilter();
        }
        return portalId;
    }

    public void setPortalId(LongFilter portalId) {
        this.portalId = portalId;
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
        final DeckCriteria that = (DeckCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(visibility, that.visibility) &&
            Objects.equals(imageUrl, that.imageUrl) &&
            Objects.equals(lastUpdated, that.lastUpdated) &&
            Objects.equals(portalId, that.portalId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, visibility, imageUrl, lastUpdated, portalId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DeckCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (title != null ? "title=" + title + ", " : "") +
            (visibility != null ? "visibility=" + visibility + ", " : "") +
            (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") +
            (lastUpdated != null ? "lastUpdated=" + lastUpdated + ", " : "") +
            (portalId != null ? "portalId=" + portalId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
