package ro.ubbcluj.thesis.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.thesis.domain.Deck;
import ro.ubbcluj.thesis.repository.DeckRepository;
import ro.ubbcluj.thesis.service.dto.DeckDTO;
import ro.ubbcluj.thesis.service.mapper.DeckMapper;

/**
 * Service Implementation for managing {@link Deck}.
 */
@Service
@Transactional
public class DeckService {

    private final Logger log = LoggerFactory.getLogger(DeckService.class);

    private final DeckRepository deckRepository;

    private final DeckMapper deckMapper;

    public DeckService(DeckRepository deckRepository, DeckMapper deckMapper) {
        this.deckRepository = deckRepository;
        this.deckMapper = deckMapper;
    }

    /**
     * Save a deck.
     *
     * @param deckDTO the entity to save.
     * @return the persisted entity.
     */
    public DeckDTO save(DeckDTO deckDTO) {
        log.debug("Request to save Deck : {}", deckDTO);
        Deck deck = deckMapper.toEntity(deckDTO);
        deck = deckRepository.save(deck);
        return deckMapper.toDto(deck);
    }

    /**
     * Partially update a deck.
     *
     * @param deckDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DeckDTO> partialUpdate(DeckDTO deckDTO) {
        log.debug("Request to partially update Deck : {}", deckDTO);

        return deckRepository
            .findById(deckDTO.getId())
            .map(existingDeck -> {
                deckMapper.partialUpdate(existingDeck, deckDTO);

                return existingDeck;
            })
            .map(deckRepository::save)
            .map(deckMapper::toDto);
    }

    /**
     * Get all the decks.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DeckDTO> findAll() {
        log.debug("Request to get all Decks");
        return deckRepository.findAll().stream().map(deckMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one deck by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DeckDTO> findOne(Long id) {
        log.debug("Request to get Deck : {}", id);
        return deckRepository.findById(id).map(deckMapper::toDto);
    }

    /**
     * Delete the deck by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Deck : {}", id);
        deckRepository.deleteById(id);
    }
}
