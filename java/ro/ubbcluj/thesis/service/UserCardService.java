package ro.ubbcluj.thesis.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.thesis.domain.AppUser;
import ro.ubbcluj.thesis.domain.UserCard;
import ro.ubbcluj.thesis.repository.UserCardRepository;

/**
 * Service Implementation for managing {@link UserCard}.
 */
@Service
@Transactional
public class UserCardService {

    private final Logger log = LoggerFactory.getLogger(UserCardService.class);

    private final UserCardRepository userCardRepository;

    public UserCardService(UserCardRepository userCardRepository) {
        this.userCardRepository = userCardRepository;
    }

    /**
     * Save a userCard.
     *
     * @param userCard the entity to save.
     * @return the persisted entity.
     */
    public UserCard save(UserCard userCard) {
        log.debug("Request to save UserCard : {}", userCard);
        return userCardRepository.save(userCard);
    }

    /**
     * Partially update a userCard.
     *
     * @param userCard the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<UserCard> partialUpdate(UserCard userCard) {
        log.debug("Request to partially update UserCard : {}", userCard);

        return userCardRepository
            .findById(userCard.getId())
            .map(existingUserCard -> {
                return existingUserCard;
            })
            .map(userCardRepository::save);
    }

    /**
     * Get all the userCards.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<UserCard> findAll() {
        log.debug("Request to get all UserCards");
        return userCardRepository.findAll();
    }

    /**
     * Get one userCard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<UserCard> findOne(Long id) {
        log.debug("Request to get UserCard : {}", id);
        return userCardRepository.findById(id);
    }

    /**
     * Delete the userCard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete UserCard : {}", id);
        userCardRepository.deleteById(id);
    }

    public UserCard findOneWithFlashcardId(AppUser appUser, Long flashcardId) {
        return this.userCardRepository.findOneWithFlashcardId(appUser.getId(), flashcardId);
    }
}
