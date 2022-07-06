package ro.ubbcluj.thesis.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CardModelHistory.
 */
@Entity
@Table(name = "card_model_history")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CardModelHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "time_stamp", nullable = false)
    private LocalDateTime timeStamp;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "recall_in_hours", nullable = false)
    private Float recallInHours;

    @OneToOne
    @JoinColumn(unique = true)
    private EbisuCardModel cardModelHistory;

    @ManyToOne
    @JsonIgnoreProperties(value = { "appUser", "card" }, allowSetters = true)
    private UserCard userCard;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CardModelHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeStamp() {
        return this.timeStamp;
    }

    public CardModelHistory timeStamp(LocalDateTime timeStamp) {
        this.setTimeStamp(timeStamp);
        return this;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Float getRecallInHours() {
        return this.recallInHours;
    }

    public CardModelHistory recallInHours(Float recallInHours) {
        this.setRecallInHours(recallInHours);
        return this;
    }

    public void setRecallInHours(Float recallInHours) {
        this.recallInHours = recallInHours;
    }

    public EbisuCardModel getCardModelHistory() {
        return this.cardModelHistory;
    }

    public void setCardModelHistory(EbisuCardModel ebisuCardModel) {
        this.cardModelHistory = ebisuCardModel;
    }

    public CardModelHistory cardModelHistory(EbisuCardModel ebisuCardModel) {
        this.setCardModelHistory(ebisuCardModel);
        return this;
    }

    public UserCard getUserCard() {
        return this.userCard;
    }

    public void setUserCard(UserCard userCard) {
        this.userCard = userCard;
    }

    public CardModelHistory userCard(UserCard userCard) {
        this.setUserCard(userCard);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CardModelHistory)) {
            return false;
        }
        return id != null && id.equals(((CardModelHistory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CardModelHistory{" +
            "id=" + getId() +
            ", timeStamp='" + getTimeStamp() + "'" +
            ", recallInHours=" + getRecallInHours() +
            "}";
    }
}
