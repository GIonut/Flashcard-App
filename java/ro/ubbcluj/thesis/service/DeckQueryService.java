package ro.ubbcluj.thesis.service;

import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.thesis.domain.*; // for static metamodels
import ro.ubbcluj.thesis.domain.Deck;
import ro.ubbcluj.thesis.repository.DeckRepository;
import ro.ubbcluj.thesis.service.criteria.DeckCriteria;
import ro.ubbcluj.thesis.service.dto.DeckDTO;
import ro.ubbcluj.thesis.service.mapper.DeckMapper;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Deck} entities in the database.
 * The main input is a {@link DeckCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DeckDTO} or a {@link Page} of {@link DeckDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DeckQueryService extends QueryService<Deck> {

    private final Logger log = LoggerFactory.getLogger(DeckQueryService.class);

    private final DeckRepository deckRepository;

    private final DeckMapper deckMapper;

    public DeckQueryService(DeckRepository deckRepository, DeckMapper deckMapper) {
        this.deckRepository = deckRepository;
        this.deckMapper = deckMapper;
    }

    /**
     * Return a {@link List} of {@link DeckDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DeckDTO> findByCriteria(DeckCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Deck> specification = createSpecification(criteria);
        return deckMapper.toDto(deckRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DeckDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DeckDTO> findByCriteria(DeckCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Deck> specification = createSpecification(criteria);
        return deckRepository.findAll(specification, page).map(deckMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DeckCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Deck> specification = createSpecification(criteria);
        return deckRepository.count(specification);
    }

    /**
     * Function to convert {@link DeckCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Deck> createSpecification(DeckCriteria criteria) {
        Specification<Deck> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Deck_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Deck_.title));
            }
            if (criteria.getVisibility() != null) {
                specification = specification.and(buildSpecification(criteria.getVisibility(), Deck_.visibility));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), Deck_.imageUrl));
            }
            if (criteria.getLastUpdated() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastUpdated(), Deck_.lastUpdated));
            }
            if (criteria.getPortalId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getPortalId(), root -> root.join(Deck_.portal, JoinType.LEFT).get(Portal_.id))
                    );
            }
        }
        return specification;
    }
}
