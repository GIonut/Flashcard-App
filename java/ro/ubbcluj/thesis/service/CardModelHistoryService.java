package ro.ubbcluj.thesis.service;

import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.thesis.domain.AppUser;
import ro.ubbcluj.thesis.domain.CardModelHistory;
import ro.ubbcluj.thesis.domain.UserCard;
import ro.ubbcluj.thesis.repository.AppUserRepository;
import ro.ubbcluj.thesis.repository.CardModelHistoryRepository;
import ro.ubbcluj.thesis.repository.UserRepository;
import ro.ubbcluj.thesis.security.SecurityUtils;

/**
 * Service Implementation for managing {@link CardModelHistory}.
 */
@Service
@Transactional
public class CardModelHistoryService {

    private final Logger log = LoggerFactory.getLogger(CardModelHistoryService.class);

    private final CardModelHistoryRepository cardModelHistoryRepository;
    private UserRepository userRepository;
    private AppUserRepository appUserRepository;
    private UserCardService userCardService;

    public CardModelHistoryService(
        CardModelHistoryRepository cardModelHistoryRepository,
        UserRepository userRepository,
        AppUserRepository appUserRepository,
        UserCardService userCardService
    ) {
        this.cardModelHistoryRepository = cardModelHistoryRepository;
        this.userRepository = userRepository;
        this.appUserRepository = appUserRepository;
        this.userCardService = userCardService;
    }

    /**
     * Save a cardModelHistory.
     *
     * @param cardModelHistory the entity to save.
     * @return the persisted entity.
     */
    public CardModelHistory save(CardModelHistory cardModelHistory) {
        log.debug("Request to save CardModelHistory : {}", cardModelHistory);
        return cardModelHistoryRepository.save(cardModelHistory);
    }

    /**
     * Partially update a cardModelHistory.
     *
     * @param cardModelHistory the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<CardModelHistory> partialUpdate(CardModelHistory cardModelHistory) {
        log.debug("Request to partially update CardModelHistory : {}", cardModelHistory);

        return cardModelHistoryRepository
            .findById(cardModelHistory.getId())
            .map(existingCardModelHistory -> {
                if (cardModelHistory.getTimeStamp() != null) {
                    existingCardModelHistory.setTimeStamp(cardModelHistory.getTimeStamp());
                }
                if (cardModelHistory.getRecallInHours() != null) {
                    existingCardModelHistory.setRecallInHours(cardModelHistory.getRecallInHours());
                }

                return existingCardModelHistory;
            })
            .map(cardModelHistoryRepository::save);
    }

    /**
     * Get all the cardModelHistories.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<CardModelHistory> findAll() {
        log.debug("Request to get all CardModelHistories");
        return cardModelHistoryRepository.findAll();
    }

    /**
     * Get one cardModelHistory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<CardModelHistory> findOne(Long id) {
        log.debug("Request to get CardModelHistory : {}", id);
        return cardModelHistoryRepository.findById(id);
    }

    /**
     * Delete the cardModelHistory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete CardModelHistory : {}", id);
        cardModelHistoryRepository.deleteById(id);
    }

    public List<CardModelHistory> findByCardId(Long cardId) {
        log.debug("Request to get CardModelHistory by cardId : {}", cardId);

        AppUser appUser = SecurityUtils
            .getCurrentUserLogin()
            .flatMap(login -> userRepository.findOneByEmailIgnoreCase(login).map(appUserRepository::findByAppUser))
            .orElse(null);

        if (appUser == null) return new ArrayList<>();

        UserCard userCard = this.userCardService.findOneWithFlashcardId(appUser, cardId);

        return cardModelHistoryRepository.findByUserCardEqualsOrderByTimeStampDesc(userCard);
    }
}
