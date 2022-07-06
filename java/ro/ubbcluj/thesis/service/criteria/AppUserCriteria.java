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
 * Criteria class for the {@link ro.ubbcluj.thesis.domain.AppUser} entity. This class is used
 * in {@link ro.ubbcluj.thesis.web.rest.AppUserResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /app-users?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
@ParameterObject
public class AppUserCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private LocalDateFilter lastLogin;

    private IntegerFilter invalidLogins;

    private LocalDateFilter lastInvalidLogin;

    private LongFilter appUserId;

    private LongFilter ownerId;

    private LongFilter portalId;

    private Boolean distinct;

    public AppUserCriteria() {}

    public AppUserCriteria(AppUserCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.lastLogin = other.lastLogin == null ? null : other.lastLogin.copy();
        this.invalidLogins = other.invalidLogins == null ? null : other.invalidLogins.copy();
        this.lastInvalidLogin = other.lastInvalidLogin == null ? null : other.lastInvalidLogin.copy();
        this.appUserId = other.appUserId == null ? null : other.appUserId.copy();
        this.ownerId = other.ownerId == null ? null : other.ownerId.copy();
        this.portalId = other.portalId == null ? null : other.portalId.copy();
        this.distinct = other.distinct;
    }

    @Override
    public AppUserCriteria copy() {
        return new AppUserCriteria(this);
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

    public LocalDateFilter getLastLogin() {
        return lastLogin;
    }

    public LocalDateFilter lastLogin() {
        if (lastLogin == null) {
            lastLogin = new LocalDateFilter();
        }
        return lastLogin;
    }

    public void setLastLogin(LocalDateFilter lastLogin) {
        this.lastLogin = lastLogin;
    }

    public IntegerFilter getInvalidLogins() {
        return invalidLogins;
    }

    public IntegerFilter invalidLogins() {
        if (invalidLogins == null) {
            invalidLogins = new IntegerFilter();
        }
        return invalidLogins;
    }

    public void setInvalidLogins(IntegerFilter invalidLogins) {
        this.invalidLogins = invalidLogins;
    }

    public LocalDateFilter getLastInvalidLogin() {
        return lastInvalidLogin;
    }

    public LocalDateFilter lastInvalidLogin() {
        if (lastInvalidLogin == null) {
            lastInvalidLogin = new LocalDateFilter();
        }
        return lastInvalidLogin;
    }

    public void setLastInvalidLogin(LocalDateFilter lastInvalidLogin) {
        this.lastInvalidLogin = lastInvalidLogin;
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

    public LongFilter getOwnerId() {
        return ownerId;
    }

    public LongFilter ownerId() {
        if (ownerId == null) {
            ownerId = new LongFilter();
        }
        return ownerId;
    }

    public void setOwnerId(LongFilter ownerId) {
        this.ownerId = ownerId;
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
        final AppUserCriteria that = (AppUserCriteria) o;
        return (
            Objects.equals(id, that.id) &&
            Objects.equals(lastLogin, that.lastLogin) &&
            Objects.equals(invalidLogins, that.invalidLogins) &&
            Objects.equals(lastInvalidLogin, that.lastInvalidLogin) &&
            Objects.equals(appUserId, that.appUserId) &&
            Objects.equals(ownerId, that.ownerId) &&
            Objects.equals(portalId, that.portalId) &&
            Objects.equals(distinct, that.distinct)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastLogin, invalidLogins, lastInvalidLogin, appUserId, ownerId, portalId, distinct);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppUserCriteria{" +
            (id != null ? "id=" + id + ", " : "") +
            (lastLogin != null ? "lastLogin=" + lastLogin + ", " : "") +
            (invalidLogins != null ? "invalidLogins=" + invalidLogins + ", " : "") +
            (lastInvalidLogin != null ? "lastInvalidLogin=" + lastInvalidLogin + ", " : "") +
            (appUserId != null ? "appUserId=" + appUserId + ", " : "") +
            (ownerId != null ? "ownerId=" + ownerId + ", " : "") +
            (portalId != null ? "portalId=" + portalId + ", " : "") +
            (distinct != null ? "distinct=" + distinct + ", " : "") +
            "}";
    }
}
