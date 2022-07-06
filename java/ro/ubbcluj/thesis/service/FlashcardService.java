package ro.ubbcluj.thesis.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.thesis.domain.Flashcard;
import ro.ubbcluj.thesis.repository.FlashcardRepository;
import ro.ubbcluj.thesis.service.dto.FlashcardDTO;
import ro.ubbcluj.thesis.service.mapper.FlashcardMapper;

/**
 * Service Implementation for managing {@link Flashcard}.
 */
@Service
@Transactional
public class FlashcardService {

    private final Logger log = LoggerFactory.getLogger(FlashcardService.class);

    private final FlashcardRepository flashcardRepository;

    private final FlashcardMapper flashcardMapper;

    public FlashcardService(FlashcardRepository flashcardRepository, FlashcardMapper flashcardMapper) {
        this.flashcardRepository = flashcardRepository;
        this.flashcardMapper = flashcardMapper;
    }

    /**
     * Save a flashcard.
     *
     * @param flashcardDTO the entity to save.
     * @return the persisted entity.
     */
    public FlashcardDTO save(FlashcardDTO flashcardDTO) {
        log.debug("Request to save Flashcard : {}", flashcardDTO);
        Flashcard flashcard = flashcardMapper.toEntity(flashcardDTO);
        flashcard = flashcardRepository.save(flashcard);
        return flashcardMapper.toDto(flashcard);
    }

    /**
     * Partially update a flashcard.
     *
     * @param flashcardDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<FlashcardDTO> partialUpdate(FlashcardDTO flashcardDTO) {
        log.debug("Request to partially update Flashcard : {}", flashcardDTO);

        return flashcardRepository
            .findById(flashcardDTO.getId())
            .map(existingFlashcard -> {
                flashcardMapper.partialUpdate(existingFlashcard, flashcardDTO);

                return existingFlashcard;
            })
            .map(flashcardRepository::save)
            .map(flashcardMapper::toDto);
    }

    /**
     * Get all the flashcards.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<FlashcardDTO> findAll() {
        log.debug("Request to get all Flashcards");
        return flashcardRepository.findAll().stream().map(flashcardMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one flashcard by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<FlashcardDTO> findOne(Long id) {
        log.debug("Request to get Flashcard : {}", id);
        return flashcardRepository.findById(id).map(flashcardMapper::toDto);
    }

    /**
     * Delete the flashcard by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Flashcard : {}", id);
        flashcardRepository.deleteById(id);
    }
}
