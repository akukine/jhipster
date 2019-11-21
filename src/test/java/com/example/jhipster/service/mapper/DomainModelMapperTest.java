package com.example.jhipster.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class DomainModelMapperTest {

    private DomainModelMapper domainModelMapper;

    @BeforeEach
    public void setUp() {
        domainModelMapper = new DomainModelMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 2L;
        assertThat(domainModelMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(domainModelMapper.fromId(null)).isNull();
    }
}
