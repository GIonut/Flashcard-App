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
import ro.ubbcluj.thesis.repository.PortalRepository;
import ro.ubbcluj.thesis.service.PortalQueryService;
import ro.ubbcluj.thesis.service.PortalService;
import ro.ubbcluj.thesis.service.criteria.PortalCriteria;
import ro.ubbcluj.thesis.service.dto.PortalDTO;
import ro.ubbcluj.thesis.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link ro.ubbcluj.thesis.domain.Portal}.
 */
@RestController
@RequestMapping("/api")
public class PortalResource {

    private final Logger log = LoggerFactory.getLogger(PortalResource.class);

    private static final String ENTITY_NAME = "portal";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PortalService portalService;

    private final PortalRepository portalRepository;

    private final PortalQueryService portalQueryService;

    public PortalResource(PortalService portalService, PortalRepository portalRepository, PortalQueryService portalQueryService) {
        this.portalService = portalService;
        this.portalRepository = portalRepository;
        this.portalQueryService = portalQueryService;
    }

    /**
     * {@code POST  /portals} : Create a new portal.
     *
     * @param portalDTO the portalDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new portalDTO, or with status {@code 400 (Bad Request)} if the portal has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/portals")
    public ResponseEntity<PortalDTO> createPortal(@Valid @RequestBody PortalDTO portalDTO) throws URISyntaxException {
        log.debug("REST request to save Portal : {}", portalDTO);
        if (portalDTO.getId() != null) {
            throw new BadRequestAlertException("A new portal cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PortalDTO result = portalService.save(portalDTO);
        return ResponseEntity
            .created(new URI("/api/portals/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /portals/:id} : Updates an existing portal.
     *
     * @param id the id of the portalDTO to save.
     * @param portalDTO the portalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated portalDTO,
     * or with status {@code 400 (Bad Request)} if the portalDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the portalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/portals/{id}")
    public ResponseEntity<PortalDTO> updatePortal(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PortalDTO portalDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Portal : {}, {}", id, portalDTO);
        if (portalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, portalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!portalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        PortalDTO result = portalService.save(portalDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, portalDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /portals/:id} : Partial updates given fields of an existing portal, field will ignore if it is null
     *
     * @param id the id of the portalDTO to save.
     * @param portalDTO the portalDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated portalDTO,
     * or with status {@code 400 (Bad Request)} if the portalDTO is not valid,
     * or with status {@code 404 (Not Found)} if the portalDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the portalDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/portals/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PortalDTO> partialUpdatePortal(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PortalDTO portalDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Portal partially : {}, {}", id, portalDTO);
        if (portalDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, portalDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!portalRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PortalDTO> result = portalService.partialUpdate(portalDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, portalDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /portals} : get all the portals.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of portals in body.
     */
    @GetMapping("/portals")
    public ResponseEntity<List<PortalDTO>> getAllPortals(PortalCriteria criteria) {
        log.debug("REST request to get Portals by criteria: {}", criteria);
        List<PortalDTO> entityList = portalQueryService.findByCriteria(criteria);
        return ResponseEntity.ok().body(entityList);
    }

    /**
     * {@code GET  /portals/count} : count all the portals.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/portals/count")
    public ResponseEntity<Long> countPortals(PortalCriteria criteria) {
        log.debug("REST request to count Portals by criteria: {}", criteria);
        return ResponseEntity.ok().body(portalQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /portals/:id} : get the "id" portal.
     *
     * @param id the id of the portalDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the portalDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/portals/{id}")
    public ResponseEntity<PortalDTO> getPortal(@PathVariable Long id) {
        log.debug("REST request to get Portal : {}", id);
        Optional<PortalDTO> portalDTO = portalService.findOne(id);
        return ResponseUtil.wrapOrNotFound(portalDTO);
    }

    /**
     * {@code DELETE  /portals/:id} : delete the "id" portal.
     *
     * @param id the id of the portalDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/portals/{id}")
    public ResponseEntity<Void> deletePortal(@PathVariable Long id) {
        log.debug("REST request to delete Portal : {}", id);
        portalService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
