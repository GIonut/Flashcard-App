package ro.ubbcluj.thesis.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.thesis.domain.Evaluation;
import ro.ubbcluj.thesis.repository.FlashcardRepository;
import ro.ubbcluj.thesis.service.FlashcardQueryService;
import ro.ubbcluj.thesis.service.FlashcardService;
import ro.ubbcluj.thesis.service.ReviewService;
import ro.ubbcluj.thesis.service.criteria.FlashcardCriteria;
import ro.ubbcluj.thesis.service.dto.FlashcardDTO;
import ro.ubbcluj.thesis.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ro.ubbcluj.thesis.domain.Flashcard}.
 */
@RestController
@RequestMapping("/api")
public class FlashcardResource {

    private final Logger log = LoggerFactory.getLogger(FlashcardResource.class);

    private static final String ENTITY_NAME = "flashcard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FlashcardService flashcardService;

    private final FlashcardRepository flashcardRepository;

    private final FlashcardQueryService flashcardQueryService;

    private final ReviewService reviewService;

    public FlashcardResource(
        FlashcardService flashcardService,
        FlashcardRepository flashcardRepository,
        FlashcardQueryService flashcardQueryService,
        ReviewService reviewService
    ) {
        this.flashcardService = flashcardService;
        this.flashcardRepository = flashcardRepository;
        this.flashcardQueryService = flashcardQueryService;
        this.reviewService = reviewService;
    }

    /**
     * {@code POST  /flashcards} : Create a new flashcard.
     *
     * @param flashcardDTO the flashcardDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new flashcardDTO, or with status {@code 400 (Bad Request)} if the flashcard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flashcards")
    public ResponseEntity<FlashcardDTO> createFlashcard(@Valid @RequestBody FlashcardDTO flashcardDTO) throws URISyntaxException {
        log.debug("REST request to save Flashcard : {}", flashcardDTO);
        if (flashcardDTO.getId() != null) {
            throw new BadRequestAlertException("A new flashcard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FlashcardDTO result = flashcardService.save(flashcardDTO);
        return ResponseEntity
            .created(new URI("/api/flashcards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code POST  /flashcards/evaluations} : Compute new IS model for flashcards.
     *
     * @param  the array of  to compute IS model for .
     * @return the {@link ResponseEntity} with status {@code 200 (OK)}
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/flashcards/evaluations")
    public ResponseEntity<String> evaluations(@Valid @RequestBody List<Evaluation> evaluations) throws URISyntaxException {
        reviewService.computeNewISModels(evaluations);
        return ResponseEntity.ok().build();
    }

    /**
     * {@code PUT  /flashcards/:id} : Updates an existing flashcard.
     *
     * @param id the id of the flashcardDTO to save.
     * @param flashcardDTO the flashcardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flashcardDTO,
     * or with status {@code 400 (Bad Request)} if the flashcardDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the flashcardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/flashcards/{id}")
    public ResponseEntity<FlashcardDTO> updateFlashcard(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody FlashcardDTO flashcardDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Flashcard : {}, {}", id, flashcardDTO);
        if (flashcardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, flashcardDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!flashcardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        FlashcardDTO result = flashcardService.save(flashcardDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, flashcardDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /flashcards/:id} : Partial updates given fields of an existing flashcard, field will ignore if it is null
     *
     * @param id the id of the flashcardDTO to save.
     * @param flashcardDTO the flashcardDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated flashcardDTO,
     * or with status {@code 400 (Bad Request)} if the flashcardDTO is not valid,
     * or with status {@code 404 (Not Found)} if the flashcardDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the flashcardDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/flashcards/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<FlashcardDTO> partialUpdateFlashcard(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody FlashcardDTO flashcardDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Flashcard partially : {}, {}", id, flashcardDTO);
        if (flashcardDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, flashcardDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!flashcardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<FlashcardDTO> result = flashcardService.partialUpdate(flashcardDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, flashcardDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /flashcards} : get all the flashcards.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of flashcards in body.
     */
    @GetMapping("/flashcards")
    public ResponseEntity<List<FlashcardDTO>> getAllFlashcards(FlashcardCriteria criteria) {
        log.debug("REST request to get Flashcards by criteria: {}", criteria);
        List<FlashcardDTO> entityList = flashcardQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /flashcards/count} : count all the flashcards.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/flashcards/count")
    public ResponseEntity<Long> countFlashcards(FlashcardCriteria criteria) {
        log.debug("REST request to count Flashcards by criteria: {}", criteria);
        return ResponseEntity.ok().body(flashcardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /flashcards/:id} : get the "id" flashcard.
     *
     * @param id the id of the flashcardDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the flashcardDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/flashcards/{id}")
    public ResponseEntity<FlashcardDTO> getFlashcard(@PathVariable Long id) {
        log.debug("REST request to get Flashcard : {}", id);
        Optional<FlashcardDTO> flashcardDTO = flashcardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(flashcardDTO);
    }

    /**
     * {@code DELETE  /flashcards/:id} : delete the "id" flashcard.
     *
     * @param id the id of the flashcardDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/flashcards/{id}")
    public ResponseEntity<Void> deleteFlashcard(@PathVariable Long id) {
        log.debug("REST request to delete Flashcard : {}", id);
        flashcardService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
