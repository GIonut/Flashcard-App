package ro.ubbcluj.thesis.service;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubbcluj.thesis.domain.EbisuCardModel;
import ro.ubbcluj.thesis.repository.EbisuCardModelRepository;

/**
 * Service Implementation for managing {@link EbisuCardModel}.
 */
@Service
@Transactional
public class EbisuCardModelService {

    private final Logger log = LoggerFactory.getLogger(EbisuCardModelService.class);

    private final EbisuCardModelRepository ebisuCardModelRepository;

    public EbisuCardModelService(EbisuCardModelRepository ebisuCardModelRepository) {
        this.ebisuCardModelRepository = ebisuCardModelRepository;
    }

    /**
     * Save a ebisuCardModel.
     *
     * @param ebisuCardModel the entity to save.
     * @return the persisted entity.
     */
    public EbisuCardModel save(EbisuCardModel ebisuCardModel) {
        log.debug("Request to save EbisuCardModel : {}", ebisuCardModel);
        return ebisuCardModelRepository.save(ebisuCardModel);
    }

    /**
     * Partially update a ebisuCardModel.
     *
     * @param ebisuCardModel the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EbisuCardModel> partialUpdate(EbisuCardModel ebisuCardModel) {
        log.debug("Request to partially update EbisuCardModel : {}", ebisuCardModel);

        return ebisuCardModelRepository
            .findById(ebisuCardModel.getId())
            .map(existingEbisuCardModel -> {
                if (ebisuCardModel.getAlpha() != null) {
                    existingEbisuCardModel.setAlpha(ebisuCardModel.getAlpha());
                }
                if (ebisuCardModel.getBeta() != null) {
                    existingEbisuCardModel.setBeta(ebisuCardModel.getBeta());
                }
                if (ebisuCardModel.getHalflife() != null) {
                    existingEbisuCardModel.setHalflife(ebisuCardModel.getHalflife());
                }

                return existingEbisuCardModel;
            })
            .map(ebisuCardModelRepository::save);
    }

    /**
     * Get all the ebisuCardModels.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EbisuCardModel> findAll() {
        log.debug("Request to get all EbisuCardModels");
        return ebisuCardModelRepository.findAll();
    }

    /**
     * Get one ebisuCardModel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EbisuCardModel> findOne(Long id) {
        log.debug("Request to get EbisuCardModel : {}", id);
        return ebisuCardModelRepository.findById(id);
    }

    /**
     * Delete the ebisuCardModel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EbisuCardModel : {}", id);
        ebisuCardModelRepository.deleteById(id);
    }
}
