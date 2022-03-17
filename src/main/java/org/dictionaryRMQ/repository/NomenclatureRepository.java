package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.Nomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface NomenclatureRepository extends JpaRepository<Nomenclature, UUID>, JpaSpecificationExecutor<Nomenclature> {
    List<Nomenclature> findAllById(UUID id);
}
