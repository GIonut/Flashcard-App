package ro.ubbcluj.thesis.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import ro.ubbcluj.thesis.domain.UserCard;

/**
 * Spring Data SQL repository for the UserCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserCardRepository extends JpaRepository<UserCard, Long>, JpaSpecificationExecutor<UserCard> {
    @Query("SELECT uc from UserCard uc where uc.appUser.id = ?1 and uc.card.id = ?2")
    UserCard findOneWithFlashcardId(Long appUserId, Long flashcardId);
}
