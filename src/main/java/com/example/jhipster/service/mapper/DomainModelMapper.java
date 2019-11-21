package com.example.jhipster.service.mapper;

import com.example.jhipster.domain.*;
import com.example.jhipster.service.dto.DomainModelDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link DomainModel} and its DTO {@link DomainModelDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DomainModelMapper extends EntityMapper<DomainModelDTO, DomainModel> {



    default DomainModel fromId(Long id) {
        if (id == null) {
            return null;
        }
        DomainModel domainModel = new DomainModel();
        domainModel.setId(id);
        return domainModel;
    }
}
