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
 * Criteria class for the {@link ro.ubbcluj.thesis.domain.EbisuCardModel} entity. This class is used
 * in {@link ro.ubbcluj.thesis.web.rest.EbisuCardModelResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /ebisu-card-models?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class EbisuCardModelCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private FloatFilter alpha;

    private FloatFilter beta;

    private FloatFilter halflife;

    private Boolean distinct;

    public EbisuCardModelCriteria() {}

    public EbisuCardModelCriteria(EbisuCardModelCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.alpha = other.alpha == null ? null : other.alpha.copy();
        this.beta = other.beta == null ? null : other.beta.copy();
        this.halflife = other.halflife == null ? null : other.halflife.copy();
        this.distinct = other.distinct;
    }

    @Override
    public EbisuCardModelCriteria copy() {
        return new EbisuCardModelCriteria(this);
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

    public FloatFilter getAlpha() {
        return alpha;
    }

    public FloatFilter alpha() {
        if (alpha == null) {
            alpha = new FloatFilter();
        }
        return alpha;
    }

    public void setAlpha(FloatFilter alpha) {
        this.alpha = alpha;
    }

    public FloatFilter getBeta() {
        return beta;
    }

    public FloatFilter beta() {
        if (beta == null) {
            beta = new FloatFilter();
        }
        return beta;
    }

    public void setBeta(FloatFilter beta) {
        this.beta = beta;
    }

    public FloatFilter getHalflife() {
        return halflife;
    }

    public FloatFilter halflife() {
        if (halflife == null) {
            halflife = new FloatFilter();
        }
        return halflife;
    }

    public void setHalflife(FloatFilter halflife) {
        this.halflife = halflife;
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
        final EbisuCardModelCriteria that = (EbisuCardModelCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(alpha, that.alpha) &&
            Objects.equals(beta, that.beta) &&
            Objects.equals(halflife, that.halflife) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, alpha, beta, halflife, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EbisuCardModelCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (alpha != null ? "alpha=" + alpha + ", " : "") +
            (beta != null ? "beta=" + beta + ", " : "") +
            (halflife != null ? "halflife=" + halflife + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
