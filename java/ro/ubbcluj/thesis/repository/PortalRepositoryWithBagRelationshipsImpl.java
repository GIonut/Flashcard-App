package ro.ubbcluj.thesis.repository;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.hibernate.annotations.QueryHints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import ro.ubbcluj.thesis.domain.Portal;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class PortalRepositoryWithBagRelationshipsImpl implements PortalRepositoryWithBagRelationships {

    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<Portal> fetchBagRelationships(Optional<Portal> portal) {
        return portal.map(this::fetchFollowers);
    }

    @Override
    public Page<Portal> fetchBagRelationships(Page<Portal> portals) {
        return new PageImpl<>(fetchBagRelationships(portals.getContent()), portals.getPageable(), portals.getTotalElements());
    }

    @Override
    public List<Portal> fetchBagRelationships(List<Portal> portals) {
        return Optional.of(portals).map(this::fetchFollowers).get();
    }

    Portal fetchFollowers(Portal result) {
        return entityManager
            .createQuery("select portal from Portal portal left join fetch portal.followers where portal is :portal", Portal.class)
            .setParameter("portal", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Portal> fetchFollowers(List<Portal> portals) {
        return entityManager
            .createQuery(
                "select distinct portal from Portal portal left join fetch portal.followers where portal in :portals",
                Portal.class
            )
            .setParameter("portals", portals)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
    }
}
