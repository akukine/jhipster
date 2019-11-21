package com.example.jhipster.service.dto;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.example.jhipster.domain.DomainModel} entity.
 */
public class DomainModelDTO implements Serializable {

    private Long id;

    private String name;

    private String type;

    private BigDecimal dateCreate;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(BigDecimal dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DomainModelDTO domainModelDTO = (DomainModelDTO) o;
        if (domainModelDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), domainModelDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DomainModelDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", dateCreate=" + getDateCreate() +
            "}";
    }
}
