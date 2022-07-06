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
import ro.ubbcluj.thesis.domain.Portal;
import ro.ubbcluj.thesis.repository.PortalRepository;
import ro.ubbcluj.thesis.service.criteria.PortalCriteria;
import ro.ubbcluj.thesis.service.dto.PortalDTO;
import ro.ubbcluj.thesis.service.mapper.PortalMapper;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link Portal} entities in the database.
 * The main input is a {@link PortalCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link PortalDTO} or a {@link Page} of {@link PortalDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class PortalQueryService extends QueryService<Portal> {

    private final Logger log = LoggerFactory.getLogger(PortalQueryService.class);

    private final PortalRepository portalRepository;

    private final PortalMapper portalMapper;

    public PortalQueryService(PortalRepository portalRepository, PortalMapper portalMapper) {
        this.portalRepository = portalRepository;
        this.portalMapper = portalMapper;
    }

    /**
     * Return a {@link List} of {@link PortalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<PortalDTO> findByCriteria(PortalCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<Portal> specification = createSpecification(criteria);
        return portalMapper.toDto(portalRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link PortalDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<PortalDTO> findByCriteria(PortalCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<Portal> specification = createSpecification(criteria);
        return portalRepository.findAll(specification, page).map(portalMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(PortalCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<Portal> specification = createSpecification(criteria);
        return portalRepository.count(specification);
    }

    /**
     * Function to convert {@link PortalCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<Portal> createSpecification(PortalCriteria criteria) {
        Specification<Portal> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), Portal_.id));
            }
            if (criteria.getPortalName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getPortalName(), Portal_.portalName));
            }
            if (criteria.getVisibility() != null) {
                specification = specification.and(buildSpecification(criteria.getVisibility(), Portal_.visibility));
            }
            if (criteria.getImageUrl() != null) {
                specification = specification.and(buildStringSpecification(criteria.getImageUrl(), Portal_.imageUrl));
            }
            if (criteria.getLastUpdated() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getLastUpdated(), Portal_.lastUpdated));
            }
            if (criteria.getTimesAccessed() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getTimesAccessed(), Portal_.timesAccessed));
            }
            if (criteria.getFollowerId() != null) {
                specification =
                    specification.and(
                        buildSpecification(criteria.getFollowerId(), root -> root.join(Portal_.followers, JoinType.LEFT).get(AppUser_.id))
                    );
            }
        }
        return specification;
    }
}
