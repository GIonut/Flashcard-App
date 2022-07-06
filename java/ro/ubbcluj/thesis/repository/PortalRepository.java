package ro.ubbcluj.thesis.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.thesis.domain.Portal;

/**
 * Spring Data SQL repository for the Portal entity.
 */
@Repository
public interface PortalRepository
    extends PortalRepositoryWithBagRelationships, JpaRepository<Portal, Long>, JpaSpecificationExecutor<Portal> {
    default Optional<Portal> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<Portal> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<Portal> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }
}
