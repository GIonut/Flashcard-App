package ro.ubbcluj.thesis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.thesis.domain.*; // for static metamodels
import ro.ubbcluj.thesis.domain.CardModelHistory;
import ro.ubbcluj.thesis.repository.AppUserRepository;
import ro.ubbcluj.thesis.repository.CardModelHistoryRepository;
import ro.ubbcluj.thesis.repository.UserRepository;
import ro.ubbcluj.thesis.security.SecurityUtils;
import ro.ubbcluj.thesis.service.criteria.CardModelHistoryCriteria;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link CardModelHistory} entities in the database.
 * The main input is a {@link CardModelHistoryCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link CardModelHistory} or a {@link Page} of {@link CardModelHistory} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class CardModelHistoryQueryService extends QueryService<CardModelHistory> {

    private final Logger log = LoggerFactory.getLogger(CardModelHistoryQueryService.class);

    private final CardModelHistoryRepository cardModelHistoryRepository;
    private final UserRepository userRepository;
    private final AppUserRepository appUserRepository;

    public CardModelHistoryQueryService(
        CardModelHistoryRepository cardModelHistoryRepository,
        UserRepository userRepository,
        AppUserRepository appUserRepository
    ) {
        this.cardModelHistoryRepository = cardModelHistoryRepository;
        this.appUserRepository = appUserRepository;
        this.userRepository = userRepository;
    }

    /**
     * Return a {@link List} of {@link CardModelHistory} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<CardModelHistory> findByCriteria(CardModelHistoryCriteria criteria) {
        log.debug("find by criteria : {}", criteria);

        AppUser appUser = SecurityUtils
            .getCurrentUserLogin()
            .flatMap(login -> userRepository.findOneByEmailIgnoreCase(login).map(appUserRepository::findByAppUser))
            .orElse(null);

        if (appUser == null) {
            return new ArrayList<>();
        }

        final Specification<CardModelHistory> specification = createSpecification(criteria);
        return cardModelHistoryRepository
            .findAll(specification)
            .stream()
            .filter(cardModelHistory -> Objects.equals(cardModelHistory.getUserCard().getAppUser().getId(), appUser.getId()))
            .collect(Collectors.toList());
    }

    /**
     * Return a {@link Page} of {@link CardModelHistory} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<CardModelHistory> findByCriteria(CardModelHistoryCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<CardModelHistory> specification = createSpecification(criteria);
        return cardModelHistoryRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(CardModelHistoryCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<CardModelHistory> specification = createSpecification(criteria);
        return cardModelHistoryRepository.count(specification);
    }

    /**
     * Function to convert {@link CardModelHistoryCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<CardModelHistory> createSpecification(CardModelHistoryCriteria criteria) {
        Specification<CardModelHistory> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), CardModelHistory_.id));
            }
            //            if (criteria.getTimeStamp() != null) {
            //                 specification = specification.and(buildRangeSpecification(criteria.getTimeStamp(), CardModelHistory_.timeStamp));
            //            }
            if (criteria.getRecallInHours() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getRecallInHours(), CardModelHistory_.recallInHours));
            }
            if (criteria.getCardModelHistoryId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getCardModelHistoryId(),
                            root -> root.join(CardModelHistory_.cardModelHistory, JoinType.LEFT).get(EbisuCardModel_.id)
                        )
                    );
            }
            if (criteria.getUserCardId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getUserCardId(),
                            root -> root.join(CardModelHistory_.userCard, JoinType.LEFT).get(UserCard_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
