package ro.ubbcluj.thesis.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubbcluj.thesis.domain.UserCard;
import ro.ubbcluj.thesis.repository.UserCardRepository;
import ro.ubbcluj.thesis.service.UserCardQueryService;
import ro.ubbcluj.thesis.service.UserCardService;
import ro.ubbcluj.thesis.service.criteria.UserCardCriteria;
import ro.ubbcluj.thesis.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ro.ubbcluj.thesis.domain.UserCard}.
 */
@RestController
@RequestMapping("/api")
public class UserCardResource {

    private final Logger log = LoggerFactory.getLogger(UserCardResource.class);

    private static final String ENTITY_NAME = "userCard";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final UserCardService userCardService;

    private final UserCardRepository userCardRepository;

    private final UserCardQueryService userCardQueryService;

    public UserCardResource(
        UserCardService userCardService,
        UserCardRepository userCardRepository,
        UserCardQueryService userCardQueryService
    ) {
        this.userCardService = userCardService;
        this.userCardRepository = userCardRepository;
        this.userCardQueryService = userCardQueryService;
    }

    /**
     * {@code POST  /user-cards} : Create a new userCard.
     *
     * @param userCard the userCard to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userCard, or with status {@code 400 (Bad Request)} if the userCard has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/user-cards")
    public ResponseEntity<UserCard> createUserCard(@RequestBody UserCard userCard) throws URISyntaxException {
        log.debug("REST request to save UserCard : {}", userCard);
        if (userCard.getId() != null) {
            throw new BadRequestAlertException("A new userCard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UserCard result = userCardService.save(userCard);
        return ResponseEntity
            .created(new URI("/api/user-cards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /user-cards/:id} : Updates an existing userCard.
     *
     * @param id the id of the userCard to save.
     * @param userCard the userCard to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userCard,
     * or with status {@code 400 (Bad Request)} if the userCard is not valid,
     * or with status {@code 500 (Internal Server Error)} if the userCard couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/user-cards/{id}")
    public ResponseEntity<UserCard> updateUserCard(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserCard userCard
    ) throws URISyntaxException {
        log.debug("REST request to update UserCard : {}, {}", id, userCard);
        if (userCard.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userCard.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userCardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        UserCard result = userCardService.save(userCard);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userCard.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /user-cards/:id} : Partial updates given fields of an existing userCard, field will ignore if it is null
     *
     * @param id the id of the userCard to save.
     * @param userCard the userCard to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated userCard,
     * or with status {@code 400 (Bad Request)} if the userCard is not valid,
     * or with status {@code 404 (Not Found)} if the userCard is not found,
     * or with status {@code 500 (Internal Server Error)} if the userCard couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/user-cards/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<UserCard> partialUpdateUserCard(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody UserCard userCard
    ) throws URISyntaxException {
        log.debug("REST request to partial update UserCard partially : {}, {}", id, userCard);
        if (userCard.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, userCard.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!userCardRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<UserCard> result = userCardService.partialUpdate(userCard);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, userCard.getId().toString())
        );
    }

    /**
     * {@code GET  /user-cards} : get all the userCards.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of userCards in body.
     */
    @GetMapping("/user-cards")
    public ResponseEntity<List<UserCard>> getAllUserCards(UserCardCriteria criteria) {
        log.debug("REST request to get UserCards by criteria: {}", criteria);
        List<UserCard> entityList = userCardQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /user-cards/count} : count all the userCards.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/user-cards/count")
    public ResponseEntity<Long> countUserCards(UserCardCriteria criteria) {
        log.debug("REST request to count UserCards by criteria: {}", criteria);
        return ResponseEntity.ok().body(userCardQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /user-cards/:id} : get the "id" userCard.
     *
     * @param id the id of the userCard to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userCard, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/user-cards/{id}")
    public ResponseEntity<UserCard> getUserCard(@PathVariable Long id) {
        log.debug("REST request to get UserCard : {}", id);
        Optional<UserCard> userCard = userCardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userCard);
    }

    /**
     * {@code DELETE  /user-cards/:id} : delete the "id" userCard.
     *
     * @param id the id of the userCard to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/user-cards/{id}")
    public ResponseEntity<Void> deleteUserCard(@PathVariable Long id) {
        log.debug("REST request to delete UserCard : {}", id);
        userCardService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
