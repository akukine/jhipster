package com.example.jhipster.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.example.jhipster.web.rest.TestUtil;

public class DomainModelDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DomainModelDTO.class);
        DomainModelDTO domainModelDTO1 = new DomainModelDTO();
        domainModelDTO1.setId(1L);
        DomainModelDTO domainModelDTO2 = new DomainModelDTO();
        assertThat(domainModelDTO1).isNotEqualTo(domainModelDTO2);
        domainModelDTO2.setId(domainModelDTO1.getId());
        assertThat(domainModelDTO1).isEqualTo(domainModelDTO2);
        domainModelDTO2.setId(2L);
        assertThat(domainModelDTO1).isNotEqualTo(domainModelDTO2);
        domainModelDTO1.setId(null);
        assertThat(domainModelDTO1).isNotEqualTo(domainModelDTO2);
    }
}
