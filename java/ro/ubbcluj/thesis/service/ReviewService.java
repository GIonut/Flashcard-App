package ro.ubbcluj.thesis.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import ro.ubbcluj.thesis.domain.*;
import ro.ubbcluj.thesis.repository.*;
import ro.ubbcluj.thesis.security.SecurityUtils;

@Service
public class ReviewService {

    private final UserCardService userCardService;
    private final UserRepository userRepository;
    private final AppUserRepository appUserRepository;
    private final FlashcardRepository flashcardRepository;
    private final CardModelHistoryRepository cardModelHistoryRepository;
    private final EbisuCardModelRepository ebisuCardModelRepository;

    public ReviewService(
        UserCardService userCardService,
        UserRepository userRepository,
        AppUserRepository appUserRepository,
        FlashcardRepository flashcardRepository,
        CardModelHistoryRepository cardModelHistoryRepository,
        EbisuCardModelRepository ebisuCardModelRepository
    ) {
        this.userCardService = userCardService;
        this.userRepository = userRepository;
        this.appUserRepository = appUserRepository;
        this.flashcardRepository = flashcardRepository;
        this.cardModelHistoryRepository = cardModelHistoryRepository;
        this.ebisuCardModelRepository = ebisuCardModelRepository;
    }

    public void computeNewISModels(List<Evaluation> evaluations) {
        Map<Long, Integer> evaluationsMap = evaluations
            .stream()
            .collect(Collectors.toMap(Evaluation::getFlashcardId, Evaluation::getEvaluation));

        AppUser appUser = SecurityUtils
            .getCurrentUserLogin()
            .flatMap(login -> userRepository.findOneByEmailIgnoreCase(login).map(appUserRepository::findByAppUser))
            .orElse(null);

        if (appUser == null) return;

        List<UserCard> userCards = getUserCardsOfFlashcards(appUser, evaluationsMap.keySet());

        updateCardModelHistoryOfUserCards(userCards, evaluationsMap);
    }

    private void updateCardModelHistoryOfUserCards(List<UserCard> userCards, Map<Long, Integer> evaluations) {
        userCards.forEach(userCard -> {
            CardModelHistory cardModel = cardModelHistoryRepository.findFirstByUserCardEqualsOrderByTimeStampDesc(userCard);

            EbisuCardModel ebisuModel;
            if (cardModel == null) {
                ebisuModel = EbisuCardModel.defaultModel();
            } else {
                ebisuModel = EbisuCardModel.updateModel(cardModel, evaluations.get(userCard.getCard().getId()));
            }
            ebisuModel = ebisuCardModelRepository.save(ebisuModel);

            CardModelHistory newCardModel = new CardModelHistory();
            newCardModel.setUserCard(userCard);
            newCardModel.setTimeStamp(LocalDateTime.now());
            newCardModel.setCardModelHistory(ebisuModel);
            newCardModel.setRecallInHours(ebisuModel.getHalflife() / 2);

            cardModelHistoryRepository.save(newCardModel);
        });
    }

    private List<UserCard> getUserCardsOfFlashcards(AppUser appUser, Set<Long> flashcardIds) {
        return flashcardIds
            .stream()
            .map(flashcardId -> {
                UserCard userCard = this.userCardService.findOneWithFlashcardId(appUser, flashcardId);
                if (userCard == null) {
                    userCard = this.userCardService.save(createUserCard(appUser, flashcardId));
                }
                return userCard;
            })
            .collect(Collectors.toList());
    }

    private UserCard createUserCard(AppUser appUser, Long flashcardId) {
        UserCard userCard = new UserCard();
        userCard.setAppUser(appUser);
        userCard.setCard(flashcardRepository.getById(flashcardId));
        return userCard;
    }
}
