package com.example.jhipster.web.rest;

import com.example.jhipster.JhispterApp;
import com.example.jhipster.domain.DomainModel;
import com.example.jhipster.repository.DomainModelRepository;
import com.example.jhipster.service.DomainModelService;
import com.example.jhipster.service.dto.DomainModelDTO;
import com.example.jhipster.service.mapper.DomainModelMapper;
import com.example.jhipster.web.rest.errors.ExceptionTranslator;
import com.example.jhipster.service.dto.DomainModelCriteria;
import com.example.jhipster.service.DomainModelQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static com.example.jhipster.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DomainModelResource} REST controller.
 */
@SpringBootTest(classes = JhispterApp.class)
public class DomainModelResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_TYPE = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DATE_CREATE = new BigDecimal(1);
    private static final BigDecimal UPDATED_DATE_CREATE = new BigDecimal(2);
    private static final BigDecimal SMALLER_DATE_CREATE = new BigDecimal(1 - 1);

    @Autowired
    private DomainModelRepository domainModelRepository;

    @Autowired
    private DomainModelMapper domainModelMapper;

    @Autowired
    private DomainModelService domainModelService;

    @Autowired
    private DomainModelQueryService domainModelQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restDomainModelMockMvc;

    private DomainModel domainModel;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DomainModelResource domainModelResource = new DomainModelResource(domainModelService, domainModelQueryService);
        this.restDomainModelMockMvc = MockMvcBuilders.standaloneSetup(domainModelResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DomainModel createEntity(EntityManager em) {
        DomainModel domainModel = new DomainModel()
            .name(DEFAULT_NAME)
            .type(DEFAULT_TYPE)
            .dateCreate(DEFAULT_DATE_CREATE);
        return domainModel;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static DomainModel createUpdatedEntity(EntityManager em) {
        DomainModel domainModel = new DomainModel()
            .name(UPDATED_NAME)
            .type(UPDATED_TYPE)
            .dateCreate(UPDATED_DATE_CREATE);
        return domainModel;
    }

    @BeforeEach
    public void initTest() {
        domainModel = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllDomainModels() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList
        restDomainModelMockMvc.perform(get("/api/domain-models?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(domainModel.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.intValue())));
    }
    
    @Test
    @Transactional
    public void getDomainModel() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get the domainModel
        restDomainModelMockMvc.perform(get("/api/domain-models/{id}", domainModel.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(domainModel.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE))
            .andExpect(jsonPath("$.dateCreate").value(DEFAULT_DATE_CREATE.intValue()));
    }


    @Test
    @Transactional
    public void getDomainModelsByIdFiltering() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        Long id = domainModel.getId();

        defaultDomainModelShouldBeFound("id.equals=" + id);
        defaultDomainModelShouldNotBeFound("id.notEquals=" + id);

        defaultDomainModelShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultDomainModelShouldNotBeFound("id.greaterThan=" + id);

        defaultDomainModelShouldBeFound("id.lessThanOrEqual=" + id);
        defaultDomainModelShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllDomainModelsByNameIsEqualToSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where name equals to DEFAULT_NAME
        defaultDomainModelShouldBeFound("name.equals=" + DEFAULT_NAME);

        // Get all the domainModelList where name equals to UPDATED_NAME
        defaultDomainModelShouldNotBeFound("name.equals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByNameIsNotEqualToSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where name not equals to DEFAULT_NAME
        defaultDomainModelShouldNotBeFound("name.notEquals=" + DEFAULT_NAME);

        // Get all the domainModelList where name not equals to UPDATED_NAME
        defaultDomainModelShouldBeFound("name.notEquals=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByNameIsInShouldWork() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where name in DEFAULT_NAME or UPDATED_NAME
        defaultDomainModelShouldBeFound("name.in=" + DEFAULT_NAME + "," + UPDATED_NAME);

        // Get all the domainModelList where name equals to UPDATED_NAME
        defaultDomainModelShouldNotBeFound("name.in=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByNameIsNullOrNotNull() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where name is not null
        defaultDomainModelShouldBeFound("name.specified=true");

        // Get all the domainModelList where name is null
        defaultDomainModelShouldNotBeFound("name.specified=false");
    }
                @Test
    @Transactional
    public void getAllDomainModelsByNameContainsSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where name contains DEFAULT_NAME
        defaultDomainModelShouldBeFound("name.contains=" + DEFAULT_NAME);

        // Get all the domainModelList where name contains UPDATED_NAME
        defaultDomainModelShouldNotBeFound("name.contains=" + UPDATED_NAME);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByNameNotContainsSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where name does not contain DEFAULT_NAME
        defaultDomainModelShouldNotBeFound("name.doesNotContain=" + DEFAULT_NAME);

        // Get all the domainModelList where name does not contain UPDATED_NAME
        defaultDomainModelShouldBeFound("name.doesNotContain=" + UPDATED_NAME);
    }


    @Test
    @Transactional
    public void getAllDomainModelsByTypeIsEqualToSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where type equals to DEFAULT_TYPE
        defaultDomainModelShouldBeFound("type.equals=" + DEFAULT_TYPE);

        // Get all the domainModelList where type equals to UPDATED_TYPE
        defaultDomainModelShouldNotBeFound("type.equals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByTypeIsNotEqualToSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where type not equals to DEFAULT_TYPE
        defaultDomainModelShouldNotBeFound("type.notEquals=" + DEFAULT_TYPE);

        // Get all the domainModelList where type not equals to UPDATED_TYPE
        defaultDomainModelShouldBeFound("type.notEquals=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByTypeIsInShouldWork() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where type in DEFAULT_TYPE or UPDATED_TYPE
        defaultDomainModelShouldBeFound("type.in=" + DEFAULT_TYPE + "," + UPDATED_TYPE);

        // Get all the domainModelList where type equals to UPDATED_TYPE
        defaultDomainModelShouldNotBeFound("type.in=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByTypeIsNullOrNotNull() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where type is not null
        defaultDomainModelShouldBeFound("type.specified=true");

        // Get all the domainModelList where type is null
        defaultDomainModelShouldNotBeFound("type.specified=false");
    }
                @Test
    @Transactional
    public void getAllDomainModelsByTypeContainsSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where type contains DEFAULT_TYPE
        defaultDomainModelShouldBeFound("type.contains=" + DEFAULT_TYPE);

        // Get all the domainModelList where type contains UPDATED_TYPE
        defaultDomainModelShouldNotBeFound("type.contains=" + UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByTypeNotContainsSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where type does not contain DEFAULT_TYPE
        defaultDomainModelShouldNotBeFound("type.doesNotContain=" + DEFAULT_TYPE);

        // Get all the domainModelList where type does not contain UPDATED_TYPE
        defaultDomainModelShouldBeFound("type.doesNotContain=" + UPDATED_TYPE);
    }


    @Test
    @Transactional
    public void getAllDomainModelsByDateCreateIsEqualToSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where dateCreate equals to DEFAULT_DATE_CREATE
        defaultDomainModelShouldBeFound("dateCreate.equals=" + DEFAULT_DATE_CREATE);

        // Get all the domainModelList where dateCreate equals to UPDATED_DATE_CREATE
        defaultDomainModelShouldNotBeFound("dateCreate.equals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByDateCreateIsNotEqualToSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where dateCreate not equals to DEFAULT_DATE_CREATE
        defaultDomainModelShouldNotBeFound("dateCreate.notEquals=" + DEFAULT_DATE_CREATE);

        // Get all the domainModelList where dateCreate not equals to UPDATED_DATE_CREATE
        defaultDomainModelShouldBeFound("dateCreate.notEquals=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByDateCreateIsInShouldWork() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where dateCreate in DEFAULT_DATE_CREATE or UPDATED_DATE_CREATE
        defaultDomainModelShouldBeFound("dateCreate.in=" + DEFAULT_DATE_CREATE + "," + UPDATED_DATE_CREATE);

        // Get all the domainModelList where dateCreate equals to UPDATED_DATE_CREATE
        defaultDomainModelShouldNotBeFound("dateCreate.in=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByDateCreateIsNullOrNotNull() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where dateCreate is not null
        defaultDomainModelShouldBeFound("dateCreate.specified=true");

        // Get all the domainModelList where dateCreate is null
        defaultDomainModelShouldNotBeFound("dateCreate.specified=false");
    }

    @Test
    @Transactional
    public void getAllDomainModelsByDateCreateIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where dateCreate is greater than or equal to DEFAULT_DATE_CREATE
        defaultDomainModelShouldBeFound("dateCreate.greaterThanOrEqual=" + DEFAULT_DATE_CREATE);

        // Get all the domainModelList where dateCreate is greater than or equal to UPDATED_DATE_CREATE
        defaultDomainModelShouldNotBeFound("dateCreate.greaterThanOrEqual=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByDateCreateIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where dateCreate is less than or equal to DEFAULT_DATE_CREATE
        defaultDomainModelShouldBeFound("dateCreate.lessThanOrEqual=" + DEFAULT_DATE_CREATE);

        // Get all the domainModelList where dateCreate is less than or equal to SMALLER_DATE_CREATE
        defaultDomainModelShouldNotBeFound("dateCreate.lessThanOrEqual=" + SMALLER_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByDateCreateIsLessThanSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where dateCreate is less than DEFAULT_DATE_CREATE
        defaultDomainModelShouldNotBeFound("dateCreate.lessThan=" + DEFAULT_DATE_CREATE);

        // Get all the domainModelList where dateCreate is less than UPDATED_DATE_CREATE
        defaultDomainModelShouldBeFound("dateCreate.lessThan=" + UPDATED_DATE_CREATE);
    }

    @Test
    @Transactional
    public void getAllDomainModelsByDateCreateIsGreaterThanSomething() throws Exception {
        // Initialize the database
        domainModelRepository.saveAndFlush(domainModel);

        // Get all the domainModelList where dateCreate is greater than DEFAULT_DATE_CREATE
        defaultDomainModelShouldNotBeFound("dateCreate.greaterThan=" + DEFAULT_DATE_CREATE);

        // Get all the domainModelList where dateCreate is greater than SMALLER_DATE_CREATE
        defaultDomainModelShouldBeFound("dateCreate.greaterThan=" + SMALLER_DATE_CREATE);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultDomainModelShouldBeFound(String filter) throws Exception {
        restDomainModelMockMvc.perform(get("/api/domain-models?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(domainModel.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE)))
            .andExpect(jsonPath("$.[*].dateCreate").value(hasItem(DEFAULT_DATE_CREATE.intValue())));

        // Check, that the count call also returns 1
        restDomainModelMockMvc.perform(get("/api/domain-models/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultDomainModelShouldNotBeFound(String filter) throws Exception {
        restDomainModelMockMvc.perform(get("/api/domain-models?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restDomainModelMockMvc.perform(get("/api/domain-models/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingDomainModel() throws Exception {
        // Get the domainModel
        restDomainModelMockMvc.perform(get("/api/domain-models/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
