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
import ro.ubbcluj.thesis.domain.Flashcard;
import ro.ubbcluj.thesis.repository.FlashcardRepository;
import ro.ubbcluj.thesis.service.criteria.FlashcardCriteria;
import ro.ubbcluj.thesis.service.dto.FlashcardDTO;
import ro.ubbcluj.thesis.service.mapper.FlashcardMapper;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Flashcard} entities in the database.
 * The main input is a {@link FlashcardCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link FlashcardDTO} or a {@link Page} of {@link FlashcardDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class FlashcardQueryService extends QueryService<Flashcard> {

    private final Logger log = LoggerFactory.getLogger(FlashcardQueryService.class);

    private final FlashcardRepository flashcardRepository;

    private final FlashcardMapper flashcardMapper;

    public FlashcardQueryService(FlashcardRepository flashcardRepository, FlashcardMapper flashcardMapper) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardMapper = flashcardMapper;
    }

    /**
     * Return a {@link List} of {@link FlashcardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<FlashcardDTO> findByCriteria(FlashcardCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Flashcard> specification = createSpecification(criteria);
        return flashcardMapper.toDto(flashcardRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link FlashcardDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<FlashcardDTO> findByCriteria(FlashcardCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Flashcard> specification = createSpecification(criteria);
        return flashcardRepository.findAll(specification, page).map(flashcardMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(FlashcardCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Flashcard> specification = createSpecification(criteria);
        return flashcardRepository.count(specification);
    }

    /**
     * Function to convert {@link FlashcardCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Flashcard> createSpecification(FlashcardCriteria criteria) {
        Specification<Flashcard> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Flashcard_.id));
            }
            if (criteria.getTitle() != null) {
                specification = specification.and(buildStringSpecification(criteria.getTitle(), Flashcard_.title));
            }
            if (criteria.getThumbnailUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getThumbnailUrl(), Flashcard_.thumbnailUrl));
            }
            if (criteria.getFrontImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFrontImageUrl(), Flashcard_.frontImageUrl));
            }
            if (criteria.getFrontText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getFrontText(), Flashcard_.frontText));
            }
            if (criteria.getBackImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBackImageUrl(), Flashcard_.backImageUrl));
            }
            if (criteria.getBackText() != null) {
                specification = specification.and(buildStringSpecification(criteria.getBackText(), Flashcard_.backText));
            }
            if (criteria.getDeckId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getDeckId(), root -> root.join(Flashcard_.deck, JoinType.LEFT).get(Deck_.id))
                    );
            }
        }
        return specification;
    }
}
