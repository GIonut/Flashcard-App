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
import ro.ubbcluj.thesis.domain.CardModelHistory;
import ro.ubbcluj.thesis.repository.CardModelHistoryRepository;
import ro.ubbcluj.thesis.service.CardModelHistoryQueryService;
import ro.ubbcluj.thesis.service.CardModelHistoryService;
import ro.ubbcluj.thesis.service.criteria.CardModelHistoryCriteria;
import ro.ubbcluj.thesis.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ro.ubbcluj.thesis.domain.CardModelHistory}.
 */
@RestController
@RequestMapping("/api")
public class CardModelHistoryResource {

    private final Logger log = LoggerFactory.getLogger(CardModelHistoryResource.class);

    private static final String ENTITY_NAME = "cardModelHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CardModelHistoryService cardModelHistoryService;

    private final CardModelHistoryRepository cardModelHistoryRepository;

    private final CardModelHistoryQueryService cardModelHistoryQueryService;

    public CardModelHistoryResource(
        CardModelHistoryService cardModelHistoryService,
        CardModelHistoryRepository cardModelHistoryRepository,
        CardModelHistoryQueryService cardModelHistoryQueryService
    ) {
        this.cardModelHistoryService = cardModelHistoryService;
        this.cardModelHistoryRepository = cardModelHistoryRepository;
        this.cardModelHistoryQueryService = cardModelHistoryQueryService;
    }

    /**
     * {@code POST  /card-model-histories} : Create a new cardModelHistory.
     *
     * @param cardModelHistory the cardModelHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cardModelHistory, or with status {@code 400 (Bad Request)} if the cardModelHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/card-model-histories")
    public ResponseEntity<CardModelHistory> createCardModelHistory(@Valid @RequestBody CardModelHistory cardModelHistory)
        throws URISyntaxException {
        log.debug("REST request to save CardModelHistory : {}", cardModelHistory);
        if (cardModelHistory.getId() != null) {
            throw new BadRequestAlertException("A new cardModelHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CardModelHistory result = cardModelHistoryService.save(cardModelHistory);
        return ResponseEntity
            .created(new URI("/api/card-model-histories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /card-model-histories/:id} : Updates an existing cardModelHistory.
     *
     * @param id the id of the cardModelHistory to save.
     * @param cardModelHistory the cardModelHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cardModelHistory,
     * or with status {@code 400 (Bad Request)} if the cardModelHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cardModelHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/card-model-histories/{id}")
    public ResponseEntity<CardModelHistory> updateCardModelHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CardModelHistory cardModelHistory
    ) throws URISyntaxException {
        log.debug("REST request to update CardModelHistory : {}, {}", id, cardModelHistory);
        if (cardModelHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cardModelHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cardModelHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        CardModelHistory result = cardModelHistoryService.save(cardModelHistory);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cardModelHistory.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /card-model-histories/:id} : Partial updates given fields of an existing cardModelHistory, field will ignore if it is null
     *
     * @param id the id of the cardModelHistory to save.
     * @param cardModelHistory the cardModelHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cardModelHistory,
     * or with status {@code 400 (Bad Request)} if the cardModelHistory is not valid,
     * or with status {@code 404 (Not Found)} if the cardModelHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the cardModelHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/card-model-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CardModelHistory> partialUpdateCardModelHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CardModelHistory cardModelHistory
    ) throws URISyntaxException {
        log.debug("REST request to partial update CardModelHistory partially : {}, {}", id, cardModelHistory);
        if (cardModelHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, cardModelHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!cardModelHistoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CardModelHistory> result = cardModelHistoryService.partialUpdate(cardModelHistory);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, cardModelHistory.getId().toString())
        );
    }

    /**
     * {@code GET  /card-model-histories} : get all the cardModelHistories.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cardModelHistories in body.
     */
    @GetMapping("/card-model-histories")
    public ResponseEntity<List<CardModelHistory>> getAllCardModelHistories(CardModelHistoryCriteria criteria) {
        log.debug("REST request to get CardModelHistories by criteria: {}", criteria);
        List<CardModelHistory> entityList = cardModelHistoryQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /card-model-histories} : get all the cardModelHistories.
     *
     * @param cardId the cardId which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cardModelHistories in body.
     */
    @GetMapping("/card-model-histories/card/{id}")
    public ResponseEntity<List<CardModelHistory>> getCardModelHistoriesOfCard(@PathVariable Long id) {
        log.debug("REST request to get CardModelHistories by cardId: {}", id);
        List<CardModelHistory> entityList = cardModelHistoryService.findByCardId(id);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /card-model-histories/count} : count all the cardModelHistories.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/card-model-histories/count")
    public ResponseEntity<Long> countCardModelHistories(CardModelHistoryCriteria criteria) {
        log.debug("REST request to count CardModelHistories by criteria: {}", criteria);
        return ResponseEntity.ok().body(cardModelHistoryQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /card-model-histories/:id} : get the "id" cardModelHistory.
     *
     * @param id the id of the cardModelHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cardModelHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/card-model-histories/{id}")
    public ResponseEntity<CardModelHistory> getCardModelHistory(@PathVariable Long id) {
        log.debug("REST request to get CardModelHistory : {}", id);
        Optional<CardModelHistory> cardModelHistory = cardModelHistoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cardModelHistory);
    }

    /**
     * {@code DELETE  /card-model-histories/:id} : delete the "id" cardModelHistory.
     *
     * @param id the id of the cardModelHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/card-model-histories/{id}")
    public ResponseEntity<Void> deleteCardModelHistory(@PathVariable Long id) {
        log.debug("REST request to delete CardModelHistory : {}", id);
        cardModelHistoryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
