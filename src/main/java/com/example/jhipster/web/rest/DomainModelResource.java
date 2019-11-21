package com.example.jhipster.web.rest;

import com.example.jhipster.service.DomainModelService;
import com.example.jhipster.web.rest.errors.BadRequestAlertException;
import com.example.jhipster.service.dto.DomainModelDTO;
import com.example.jhipster.service.dto.DomainModelCriteria;
import com.example.jhipster.service.DomainModelQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.example.jhipster.domain.DomainModel}.
 */
@RestController
@RequestMapping("/api")
public class DomainModelResource {

    private final Logger log = LoggerFactory.getLogger(DomainModelResource.class);

    private final DomainModelService domainModelService;

    private final DomainModelQueryService domainModelQueryService;

    public DomainModelResource(DomainModelService domainModelService, DomainModelQueryService domainModelQueryService) {
        this.domainModelService = domainModelService;
        this.domainModelQueryService = domainModelQueryService;
    }

    /**
     * {@code GET  /domain-models} : get all the domainModels.
     *

     * @param pageable the pagination information.

     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of domainModels in body.
     */
    @GetMapping("/domain-models")
    public ResponseEntity<List<DomainModelDTO>> getAllDomainModels(DomainModelCriteria criteria, Pageable pageable) {
        log.debug("REST request to get DomainModels by criteria: {}", criteria);
        Page<DomainModelDTO> page = domainModelQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /domain-models/count} : count all the domainModels.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/domain-models/count")
    public ResponseEntity<Long> countDomainModels(DomainModelCriteria criteria) {
        log.debug("REST request to count DomainModels by criteria: {}", criteria);
        return ResponseEntity.ok().body(domainModelQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /domain-models/:id} : get the "id" domainModel.
     *
     * @param id the id of the domainModelDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the domainModelDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/domain-models/{id}")
    public ResponseEntity<DomainModelDTO> getDomainModel(@PathVariable Long id) {
        log.debug("REST request to get DomainModel : {}", id);
        Optional<DomainModelDTO> domainModelDTO = domainModelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(domainModelDTO);
    }
}
