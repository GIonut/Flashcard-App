package ro.ubbcluj.thesis.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.thesis.domain.EbisuCardModel;

/**
 * Spring Data SQL repository for the EbisuCardModel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface EbisuCardModelRepository extends JpaRepository<EbisuCardModel, Long>, JpaSpecificationExecutor<EbisuCardModel> {}
