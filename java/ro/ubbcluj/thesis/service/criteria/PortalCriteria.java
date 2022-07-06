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
 * Criteria class for the {@link ro.ubbcluj.thesis.domain.Portal} entity. This class is used
 * in {@link ro.ubbcluj.thesis.web.rest.PortalResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /portals?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class PortalCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter portalName;

    private BooleanFilter visibility;

    private StringFilter imageUrl;

    private LocalDateFilter lastUpdated;

    private IntegerFilter timesAccessed;

    private LongFilter followerId;

    private Boolean distinct;

    public PortalCriteria() {}

    public PortalCriteria(PortalCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.portalName = other.portalName == null ? null : other.portalName.copy();
        this.visibility = other.visibility == null ? null : other.visibility.copy();
        this.imageUrl = other.imageUrl == null ? null : other.imageUrl.copy();
        this.lastUpdated = other.lastUpdated == null ? null : other.lastUpdated.copy();
        this.timesAccessed = other.timesAccessed == null ? null : other.timesAccessed.copy();
        this.followerId = other.followerId == null ? null : other.followerId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public PortalCriteria copy() {
        return new PortalCriteria(this);
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

    public StringFilter getPortalName() {
        return portalName;
    }

    public StringFilter portalName() {
        if (portalName == null) {
            portalName = new StringFilter();
        }
        return portalName;
    }

    public void setPortalName(StringFilter portalName) {
        this.portalName = portalName;
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

    public IntegerFilter getTimesAccessed() {
        return timesAccessed;
    }

    public IntegerFilter timesAccessed() {
        if (timesAccessed == null) {
            timesAccessed = new IntegerFilter();
        }
        return timesAccessed;
    }

    public void setTimesAccessed(IntegerFilter timesAccessed) {
        this.timesAccessed = timesAccessed;
    }

    public LongFilter getFollowerId() {
        return followerId;
    }

    public LongFilter followerId() {
        if (followerId == null) {
            followerId = new LongFilter();
        }
        return followerId;
    }

    public void setFollowerId(LongFilter followerId) {
        this.followerId = followerId;
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
        final PortalCriteria that = (PortalCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(portalName, that.portalName) &&
            Objects.equals(visibility, that.visibility) &&
            Objects.equals(imageUrl, that.imageUrl) &&
            Objects.equals(lastUpdated, that.lastUpdated) &&
            Objects.equals(timesAccessed, that.timesAccessed) &&
            Objects.equals(followerId, that.followerId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, portalName, visibility, imageUrl, lastUpdated, timesAccessed, followerId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PortalCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (portalName != null ? "portalName=" + portalName + ", " : "") +
            (visibility != null ? "visibility=" + visibility + ", " : "") +
            (imageUrl != null ? "imageUrl=" + imageUrl + ", " : "") +
            (lastUpdated != null ? "lastUpdated=" + lastUpdated + ", " : "") +
            (timesAccessed != null ? "timesAccessed=" + timesAccessed + ", " : "") +
            (followerId != null ? "followerId=" + followerId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
