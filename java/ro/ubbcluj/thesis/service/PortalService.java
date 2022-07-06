package ro.ubbcluj.thesis.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.thesis.domain.Portal;
import ro.ubbcluj.thesis.repository.PortalRepository;
import ro.ubbcluj.thesis.service.dto.PortalDTO;
import ro.ubbcluj.thesis.service.mapper.PortalMapper;

/**
 * Service Implementation for managing {@link Portal}.
 */
@Service
@Transactional
public class PortalService {

    private final Logger log = LoggerFactory.getLogger(PortalService.class);

    private final PortalRepository portalRepository;

    private final PortalMapper portalMapper;

    public PortalService(PortalRepository portalRepository, PortalMapper portalMapper) {
        this.portalRepository = portalRepository;
        this.portalMapper = portalMapper;
    }

    /**
     * Save a portal.
     *
     * @param portalDTO the entity to save.
     * @return the persisted entity.
     */
    public PortalDTO save(PortalDTO portalDTO) {
        log.debug("Request to save Portal : {}", portalDTO);
        Portal portal = portalMapper.toEntity(portalDTO);
        portal = portalRepository.save(portal);
        return portalMapper.toDto(portal);
    }

    /**
     * Partially update a portal.
     *
     * @param portalDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PortalDTO> partialUpdate(PortalDTO portalDTO) {
        log.debug("Request to partially update Portal : {}", portalDTO);

        return portalRepository
            .findById(portalDTO.getId())
            .map(existingPortal -> {
                portalMapper.partialUpdate(existingPortal, portalDTO);

                return existingPortal;
            })
            .map(portalRepository::save)
            .map(portalMapper::toDto);
    }

    /**
     * Get all the portals.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PortalDTO> findAll() {
        log.debug("Request to get all Portals");
        return portalRepository
            .findAllWithEagerRelationships()
            .stream()
            .map(portalMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the portals with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<PortalDTO> findAllWithEagerRelationships(Pageable pageable) {
        return portalRepository.findAllWithEagerRelationships(pageable).map(portalMapper::toDto);
    }

    /**
     * Get one portal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PortalDTO> findOne(Long id) {
        log.debug("Request to get Portal : {}", id);
        return portalRepository.findOneWithEagerRelationships(id).map(portalMapper::toDto);
    }

    /**
     * Delete the portal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Portal : {}", id);
        portalRepository.deleteById(id);
    }
}
