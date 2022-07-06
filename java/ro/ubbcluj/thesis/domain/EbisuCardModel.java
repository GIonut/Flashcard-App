package ro.ubbcluj.thesis.domain;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import me.aldebrn.ebisu.Ebisu;
import me.aldebrn.ebisu.EbisuInterface;
import me.aldebrn.ebisu.EbisuModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A EbisuCardModel.
 */
@Entity
@Table(name = "ebisu_card_model")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class EbisuCardModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "alpha", nullable = false)
    private Float alpha;

    @NotNull
    @Column(name = "beta", nullable = false)
    private Float beta;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "halflife", nullable = false)
    private Float halflife;

    @Transient
    static final Float defaultAlphaBeta = 4f;

    @Transient
    static final Float defaultHalflife = 1f;

    @Transient
    private static final int totalTrials = 2;

    public static EbisuCardModel defaultModel() {
        EbisuCardModel ebisuModel = new EbisuCardModel();
        ebisuModel.setAlpha(defaultAlphaBeta);
        ebisuModel.setBeta(defaultAlphaBeta);
        ebisuModel.setHalflife(defaultHalflife);
        return ebisuModel;
    }

    public static EbisuCardModel updateModel(CardModelHistory cardModelHistory, Integer evaluation) {
        EbisuCardModel ebisuCardModel = cardModelHistory.getCardModelHistory();

        double hours = Duration.between(cardModelHistory.getTimeStamp(), LocalDateTime.now()).toSeconds() / 3600f;

        EbisuModel previousModel = new EbisuModel(ebisuCardModel.getHalflife(), ebisuCardModel.getAlpha(), ebisuCardModel.getBeta());

        EbisuInterface updatedModel = Ebisu.updateRecall(previousModel, evaluation % (totalTrials + 1), totalTrials, hours);

        EbisuCardModel newEbisuCardModel = new EbisuCardModel();
        newEbisuCardModel.setAlpha((float) updatedModel.getAlpha());
        newEbisuCardModel.setBeta((float) updatedModel.getBeta());
        newEbisuCardModel.setHalflife((float) updatedModel.getTime());
        return newEbisuCardModel;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public EbisuCardModel id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getAlpha() {
        return this.alpha;
    }

    public EbisuCardModel alpha(Float alpha) {
        this.setAlpha(alpha);
        return this;
    }

    public void setAlpha(Float alpha) {
        this.alpha = alpha;
    }

    public Float getBeta() {
        return this.beta;
    }

    public EbisuCardModel beta(Float beta) {
        this.setBeta(beta);
        return this;
    }

    public void setBeta(Float beta) {
        this.beta = beta;
    }

    public Float getHalflife() {
        return this.halflife;
    }

    public EbisuCardModel halflife(Float halflife) {
        this.setHalflife(halflife);
        return this;
    }

    public void setHalflife(Float halflife) {
        this.halflife = halflife;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EbisuCardModel)) {
            return false;
        }
        return id != null && id.equals(((EbisuCardModel) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EbisuCardModel{" +
            "id=" + getId() +
            ", alpha=" + getAlpha() +
            ", beta=" + getBeta() +
            ", halflife=" + getHalflife() +
            "}";
    }
}
