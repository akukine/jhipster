package com.example.jhipster.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.BigDecimalFilter;

/**
 * Criteria class for the {@link com.example.jhipster.domain.DomainModel} entity. This class is used
 * in {@link com.example.jhipster.web.rest.DomainModelResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /domain-models?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class DomainModelCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter type;

    private BigDecimalFilter dateCreate;

    public DomainModelCriteria(){
    }

    public DomainModelCriteria(DomainModelCriteria other){
        this.id = other.id == null ? null : other.id.copy();
        this.name = other.name == null ? null : other.name.copy();
        this.type = other.type == null ? null : other.type.copy();
        this.dateCreate = other.dateCreate == null ? null : other.dateCreate.copy();
    }

    @Override
    public DomainModelCriteria copy() {
        return new DomainModelCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getType() {
        return type;
    }

    public void setType(StringFilter type) {
        this.type = type;
    }

    public BigDecimalFilter getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(BigDecimalFilter dateCreate) {
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
        final DomainModelCriteria that = (DomainModelCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(type, that.type) &&
            Objects.equals(dateCreate, that.dateCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        type,
        dateCreate
        );
    }

    @Override
    public String toString() {
        return "DomainModelCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (type != null ? "type=" + type + ", " : "") +
                (dateCreate != null ? "dateCreate=" + dateCreate + ", " : "") +
            "}";
    }

}
