package com.example.jhipster.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.example.jhipster.web.rest.TestUtil;

public class DomainModelTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DomainModel.class);
        DomainModel domainModel1 = new DomainModel();
        domainModel1.setId(1L);
        DomainModel domainModel2 = new DomainModel();
        domainModel2.setId(domainModel1.getId());
        assertThat(domainModel1).isEqualTo(domainModel2);
        domainModel2.setId(2L);
        assertThat(domainModel1).isNotEqualTo(domainModel2);
        domainModel1.setId(null);
        assertThat(domainModel1).isNotEqualTo(domainModel2);
    }
}
