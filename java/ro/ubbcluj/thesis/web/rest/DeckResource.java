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
import ro.ubbcluj.thesis.repository.DeckRepository;
import ro.ubbcluj.thesis.service.DeckQueryService;
import ro.ubbcluj.thesis.service.DeckService;
import ro.ubbcluj.thesis.service.criteria.DeckCriteria;
import ro.ubbcluj.thesis.service.dto.DeckDTO;
import ro.ubbcluj.thesis.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ro.ubbcluj.thesis.domain.Deck}.
 */
@RestController
@RequestMapping("/api")
public class DeckResource {

    private final Logger log = LoggerFactory.getLogger(DeckResource.class);

    private static final String ENTITY_NAME = "deck";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DeckService deckService;

    private final DeckRepository deckRepository;

    private final DeckQueryService deckQueryService;

    public DeckResource(DeckService deckService, DeckRepository deckRepository, DeckQueryService deckQueryService) {
        this.deckService = deckService;
        this.deckRepository = deckRepository;
        this.deckQueryService = deckQueryService;
    }

    /**
     * {@code POST  /decks} : Create a new deck.
     *
     * @param deckDTO the deckDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new deckDTO, or with status {@code 400 (Bad Request)} if the deck has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/decks")
    public ResponseEntity<DeckDTO> createDeck(@Valid @RequestBody DeckDTO deckDTO) throws URISyntaxException {
        log.debug("REST request to save Deck : {}", deckDTO);
        if (deckDTO.getId() != null) {
            throw new BadRequestAlertException("A new deck cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DeckDTO result = deckService.save(deckDTO);
        return ResponseEntity
            .created(new URI("/api/decks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /decks/:id} : Updates an existing deck.
     *
     * @param id the id of the deckDTO to save.
     * @param deckDTO the deckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deckDTO,
     * or with status {@code 400 (Bad Request)} if the deckDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the deckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/decks/{id}")
    public ResponseEntity<DeckDTO> updateDeck(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DeckDTO deckDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Deck : {}, {}", id, deckDTO);
        if (deckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deckDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deckRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        DeckDTO result = deckService.save(deckDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deckDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /decks/:id} : Partial updates given fields of an existing deck, field will ignore if it is null
     *
     * @param id the id of the deckDTO to save.
     * @param deckDTO the deckDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated deckDTO,
     * or with status {@code 400 (Bad Request)} if the deckDTO is not valid,
     * or with status {@code 404 (Not Found)} if the deckDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the deckDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/decks/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DeckDTO> partialUpdateDeck(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DeckDTO deckDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Deck partially : {}, {}", id, deckDTO);
        if (deckDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, deckDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!deckRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DeckDTO> result = deckService.partialUpdate(deckDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, deckDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /decks} : get all the decks.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of decks in body.
     */
    @GetMapping("/decks")
    public ResponseEntity<List<DeckDTO>> getAllDecks(DeckCriteria criteria) {
        log.debug("REST request to get Decks by criteria: {}", criteria);
        List<DeckDTO> entityList = deckQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /decks/count} : count all the decks.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/decks/count")
    public ResponseEntity<Long> countDecks(DeckCriteria criteria) {
        log.debug("REST request to count Decks by criteria: {}", criteria);
        return ResponseEntity.ok().body(deckQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /decks/:id} : get the "id" deck.
     *
     * @param id the id of the deckDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the deckDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/decks/{id}")
    public ResponseEntity<DeckDTO> getDeck(@PathVariable Long id) {
        log.debug("REST request to get Deck : {}", id);
        Optional<DeckDTO> deckDTO = deckService.findOne(id);
        return ResponseUtil.wrapOrNotFound(deckDTO);
    }

    /**
     * {@code DELETE  /decks/:id} : delete the "id" deck.
     *
     * @param id the id of the deckDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/decks/{id}")
    public ResponseEntity<Void> deleteDeck(@PathVariable Long id) {
        log.debug("REST request to delete Deck : {}", id);
        deckService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
