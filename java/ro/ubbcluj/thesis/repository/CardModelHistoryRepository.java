package ro.ubbcluj.thesis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.thesis.domain.CardModelHistory;
import ro.ubbcluj.thesis.domain.EbisuCardModel;
import ro.ubbcluj.thesis.domain.UserCard;

/**
 * Spring Data SQL repository for the CardModelHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CardModelHistoryRepository extends JpaRepository<CardModelHistory, Long>, JpaSpecificationExecutor<CardModelHistory> {
    CardModelHistory findFirstByUserCardEqualsOrderByTimeStampDesc(UserCard userCard);

    List<CardModelHistory> findByUserCardEqualsOrderByTimeStampDesc(UserCard userCard);
}
