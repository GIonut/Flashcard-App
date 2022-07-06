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
 * Criteria class for the {@link ro.ubbcluj.thesis.domain.CardModelHistory} entity. This class is used
 * in {@link ro.ubbcluj.thesis.web.rest.CardModelHistoryResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /card-model-histories?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class CardModelHistoryCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter timeStamp;

    private FloatFilter recallInHours;

    private LongFilter cardModelHistoryId;

    private LongFilter userCardId;

    private Boolean distinct;

    public CardModelHistoryCriteria() {}

    public CardModelHistoryCriteria(CardModelHistoryCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.timeStamp = other.timeStamp == null ? null : other.timeStamp.copy();
        this.recallInHours = other.recallInHours == null ? null : other.recallInHours.copy();
        this.cardModelHistoryId = other.cardModelHistoryId == null ? null : other.cardModelHistoryId.copy();
        this.userCardId = other.userCardId == null ? null : other.userCardId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public CardModelHistoryCriteria copy() {
        return new CardModelHistoryCriteria(this);
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

    public LocalDateFilter getTimeStamp() {
        return timeStamp;
    }

    public LocalDateFilter timeStamp() {
        if (timeStamp == null) {
            timeStamp = new LocalDateFilter();
        }
        return timeStamp;
    }

    public void setTimeStamp(LocalDateFilter timeStamp) {
        this.timeStamp = timeStamp;
    }

    public FloatFilter getRecallInHours() {
        return recallInHours;
    }

    public FloatFilter recallInHours() {
        if (recallInHours == null) {
            recallInHours = new FloatFilter();
        }
        return recallInHours;
    }

    public void setRecallInHours(FloatFilter recallInHours) {
        this.recallInHours = recallInHours;
    }

    public LongFilter getCardModelHistoryId() {
        return cardModelHistoryId;
    }

    public LongFilter cardModelHistoryId() {
        if (cardModelHistoryId == null) {
            cardModelHistoryId = new LongFilter();
        }
        return cardModelHistoryId;
    }

    public void setCardModelHistoryId(LongFilter cardModelHistoryId) {
        this.cardModelHistoryId = cardModelHistoryId;
    }

    public LongFilter getUserCardId() {
        return userCardId;
    }

    public LongFilter userCardId() {
        if (userCardId == null) {
            userCardId = new LongFilter();
        }
        return userCardId;
    }

    public void setUserCardId(LongFilter userCardId) {
        this.userCardId = userCardId;
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
        final CardModelHistoryCriteria that = (CardModelHistoryCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(timeStamp, that.timeStamp) &&
            Objects.equals(recallInHours, that.recallInHours) &&
            Objects.equals(cardModelHistoryId, that.cardModelHistoryId) &&
            Objects.equals(userCardId, that.userCardId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timeStamp, recallInHours, cardModelHistoryId, userCardId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CardModelHistoryCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (timeStamp != null ? "timeStamp=" + timeStamp + ", " : "") +
            (recallInHours != null ? "recallInHours=" + recallInHours + ", " : "") +
            (cardModelHistoryId != null ? "cardModelHistoryId=" + cardModelHistoryId + ", " : "") +
            (userCardId != null ? "userCardId=" + userCardId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
