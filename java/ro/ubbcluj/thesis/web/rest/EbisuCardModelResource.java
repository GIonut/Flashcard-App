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
import ro.ubbcluj.thesis.domain.EbisuCardModel;
import ro.ubbcluj.thesis.repository.EbisuCardModelRepository;
import ro.ubbcluj.thesis.service.EbisuCardModelQueryService;
import ro.ubbcluj.thesis.service.EbisuCardModelService;
import ro.ubbcluj.thesis.service.criteria.EbisuCardModelCriteria;
import ro.ubbcluj.thesis.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ro.ubbcluj.thesis.domain.EbisuCardModel}.
 */
@RestController
@RequestMapping("/api")
public class EbisuCardModelResource {

    private final Logger log = LoggerFactory.getLogger(EbisuCardModelResource.class);

    private static final String ENTITY_NAME = "ebisuCardModel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EbisuCardModelService ebisuCardModelService;

    private final EbisuCardModelRepository ebisuCardModelRepository;

    private final EbisuCardModelQueryService ebisuCardModelQueryService;

    public EbisuCardModelResource(
        EbisuCardModelService ebisuCardModelService,
        EbisuCardModelRepository ebisuCardModelRepository,
        EbisuCardModelQueryService ebisuCardModelQueryService
    ) {
        this.ebisuCardModelService = ebisuCardModelService;
        this.ebisuCardModelRepository = ebisuCardModelRepository;
        this.ebisuCardModelQueryService = ebisuCardModelQueryService;
    }

    /**
     * {@code POST  /ebisu-card-models} : Create a new ebisuCardModel.
     *
     * @param ebisuCardModel the ebisuCardModel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ebisuCardModel, or with status {@code 400 (Bad Request)} if the ebisuCardModel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ebisu-card-models")
    public ResponseEntity<EbisuCardModel> createEbisuCardModel(@Valid @RequestBody EbisuCardModel ebisuCardModel)
        throws URISyntaxException {
        log.debug("REST request to save EbisuCardModel : {}", ebisuCardModel);
        if (ebisuCardModel.getId() != null) {
            throw new BadRequestAlertException("A new ebisuCardModel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EbisuCardModel result = ebisuCardModelService.save(ebisuCardModel);
        return ResponseEntity
            .created(new URI("/api/ebisu-card-models/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ebisu-card-models/:id} : Updates an existing ebisuCardModel.
     *
     * @param id the id of the ebisuCardModel to save.
     * @param ebisuCardModel the ebisuCardModel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ebisuCardModel,
     * or with status {@code 400 (Bad Request)} if the ebisuCardModel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ebisuCardModel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ebisu-card-models/{id}")
    public ResponseEntity<EbisuCardModel> updateEbisuCardModel(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody EbisuCardModel ebisuCardModel
    ) throws URISyntaxException {
        log.debug("REST request to update EbisuCardModel : {}, {}", id, ebisuCardModel);
        if (ebisuCardModel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ebisuCardModel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ebisuCardModelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EbisuCardModel result = ebisuCardModelService.save(ebisuCardModel);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ebisuCardModel.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ebisu-card-models/:id} : Partial updates given fields of an existing ebisuCardModel, field will ignore if it is null
     *
     * @param id the id of the ebisuCardModel to save.
     * @param ebisuCardModel the ebisuCardModel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ebisuCardModel,
     * or with status {@code 400 (Bad Request)} if the ebisuCardModel is not valid,
     * or with status {@code 404 (Not Found)} if the ebisuCardModel is not found,
     * or with status {@code 500 (Internal Server Error)} if the ebisuCardModel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ebisu-card-models/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EbisuCardModel> partialUpdateEbisuCardModel(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody EbisuCardModel ebisuCardModel
    ) throws URISyntaxException {
        log.debug("REST request to partial update EbisuCardModel partially : {}, {}", id, ebisuCardModel);
        if (ebisuCardModel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, ebisuCardModel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!ebisuCardModelRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EbisuCardModel> result = ebisuCardModelService.partialUpdate(ebisuCardModel);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ebisuCardModel.getId().toString())
        );
    }

    /**
     * {@code GET  /ebisu-card-models} : get all the ebisuCardModels.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ebisuCardModels in body.
     */
    @GetMapping("/ebisu-card-models")
    public ResponseEntity<List<EbisuCardModel>> getAllEbisuCardModels(EbisuCardModelCriteria criteria) {
        log.debug("REST request to get EbisuCardModels by criteria: {}", criteria);
        List<EbisuCardModel> entityList = ebisuCardModelQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /ebisu-card-models/count} : count all the ebisuCardModels.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/ebisu-card-models/count")
    public ResponseEntity<Long> countEbisuCardModels(EbisuCardModelCriteria criteria) {
        log.debug("REST request to count EbisuCardModels by criteria: {}", criteria);
        return ResponseEntity.ok().body(ebisuCardModelQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /ebisu-card-models/:id} : get the "id" ebisuCardModel.
     *
     * @param id the id of the ebisuCardModel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ebisuCardModel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ebisu-card-models/{id}")
    public ResponseEntity<EbisuCardModel> getEbisuCardModel(@PathVariable Long id) {
        log.debug("REST request to get EbisuCardModel : {}", id);
        Optional<EbisuCardModel> ebisuCardModel = ebisuCardModelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ebisuCardModel);
    }

    /**
     * {@code DELETE  /ebisu-card-models/:id} : delete the "id" ebisuCardModel.
     *
     * @param id the id of the ebisuCardModel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ebisu-card-models/{id}")
    public ResponseEntity<Void> deleteEbisuCardModel(@PathVariable Long id) {
        log.debug("REST request to delete EbisuCardModel : {}", id);
        ebisuCardModelService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
