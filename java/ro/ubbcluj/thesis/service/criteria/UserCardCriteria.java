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
 * Criteria class for the {@link ro.ubbcluj.thesis.domain.UserCard} entity. This class is used
 * in {@link ro.ubbcluj.thesis.web.rest.UserCardResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /user-cards?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class UserCardCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LongFilter appUserId;

    private LongFilter cardId;

    private Boolean distinct;

    public UserCardCriteria() {}

    public UserCardCriteria(UserCardCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.appUserId = other.appUserId == null ? null : other.appUserId.copy();
        this.cardId = other.cardId == null ? null : other.cardId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public UserCardCriteria copy() {
        return new UserCardCriteria(this);
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

    public LongFilter getAppUserId() {
        return appUserId;
    }

    public LongFilter appUserId() {
        if (appUserId == null) {
            appUserId = new LongFilter();
        }
        return appUserId;
    }

    public void setAppUserId(LongFilter appUserId) {
        this.appUserId = appUserId;
    }

    public LongFilter getCardId() {
        return cardId;
    }

    public LongFilter cardId() {
        if (cardId == null) {
            cardId = new LongFilter();
        }
        return cardId;
    }

    public void setCardId(LongFilter cardId) {
        this.cardId = cardId;
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
        final UserCardCriteria that = (UserCardCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(appUserId, that.appUserId) &&
            Objects.equals(cardId, that.cardId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, appUserId, cardId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserCardCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (appUserId != null ? "appUserId=" + appUserId + ", " : "") +
            (cardId != null ? "cardId=" + cardId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
