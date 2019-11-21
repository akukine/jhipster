package com.example.jhipster.service;

import com.example.jhipster.domain.DomainModel;
import com.example.jhipster.repository.DomainModelRepository;
import com.example.jhipster.service.dto.DomainModelDTO;
import com.example.jhipster.service.mapper.DomainModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DomainModel}.
 */
@Service
@Transactional
public class DomainModelService {

    private final Logger log = LoggerFactory.getLogger(DomainModelService.class);

    private final DomainModelRepository domainModelRepository;

    private final DomainModelMapper domainModelMapper;

    public DomainModelService(DomainModelRepository domainModelRepository, DomainModelMapper domainModelMapper) {
        this.domainModelRepository = domainModelRepository;
        this.domainModelMapper = domainModelMapper;
    }

    /**
     * Save a domainModel.
     *
     * @param domainModelDTO the entity to save.
     * @return the persisted entity.
     */
    public DomainModelDTO save(DomainModelDTO domainModelDTO) {
        log.debug("Request to save DomainModel : {}", domainModelDTO);
        DomainModel domainModel = domainModelMapper.toEntity(domainModelDTO);
        domainModel = domainModelRepository.save(domainModel);
        return domainModelMapper.toDto(domainModel);
    }

    /**
     * Get all the domainModels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<DomainModelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all DomainModels");
        return domainModelRepository.findAll(pageable)
            .map(domainModelMapper::toDto);
    }


    /**
     * Get one domainModel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DomainModelDTO> findOne(Long id) {
        log.debug("Request to get DomainModel : {}", id);
        return domainModelRepository.findById(id)
            .map(domainModelMapper::toDto);
    }

    /**
     * Delete the domainModel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DomainModel : {}", id);
        domainModelRepository.deleteById(id);
    }
}
