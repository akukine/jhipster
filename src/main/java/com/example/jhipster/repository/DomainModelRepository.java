package com.example.jhipster.repository;
import com.example.jhipster.domain.DomainModel;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the DomainModel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DomainModelRepository extends JpaRepository<DomainModel, Long>, JpaSpecificationExecutor<DomainModel> {

}
