package com.example.jhipster.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.example.jhipster.domain.DomainModel;
import com.example.jhipster.domain.*; // for static metamodels
import com.example.jhipster.repository.DomainModelRepository;
import com.example.jhipster.service.dto.DomainModelCriteria;
import com.example.jhipster.service.dto.DomainModelDTO;
import com.example.jhipster.service.mapper.DomainModelMapper;

/**
 * Service for executing complex queries for {@link DomainModel} entities in the database.
 * The main input is a {@link DomainModelCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link DomainModelDTO} or a {@link Page} of {@link DomainModelDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class DomainModelQueryService extends QueryService<DomainModel> {

    private final Logger log = LoggerFactory.getLogger(DomainModelQueryService.class);

    private final DomainModelRepository domainModelRepository;

    private final DomainModelMapper domainModelMapper;

    public DomainModelQueryService(DomainModelRepository domainModelRepository, DomainModelMapper domainModelMapper) {
        this.domainModelRepository = domainModelRepository;
        this.domainModelMapper = domainModelMapper;
    }

    /**
     * Return a {@link List} of {@link DomainModelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<DomainModelDTO> findByCriteria(DomainModelCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<DomainModel> specification = createSpecification(criteria);
        return domainModelMapper.toDto(domainModelRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link DomainModelDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<DomainModelDTO> findByCriteria(DomainModelCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<DomainModel> specification = createSpecification(criteria);
        return domainModelRepository.findAll(specification, page)
            .map(domainModelMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(DomainModelCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<DomainModel> specification = createSpecification(criteria);
        return domainModelRepository.count(specification);
    }

    /**
     * Function to convert {@link DomainModelCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<DomainModel> createSpecification(DomainModelCriteria criteria) {
        Specification<DomainModel> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), DomainModel_.id));
            }
            if (criteria.getName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getName(), DomainModel_.name));
            }
            if (criteria.getType() != null) {
                specification = specification.and(buildStringSpecification(criteria.getType(), DomainModel_.type));
            }
            if (criteria.getDateCreate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreate(), DomainModel_.dateCreate));
            }
        }
        return specification;
    }
}
