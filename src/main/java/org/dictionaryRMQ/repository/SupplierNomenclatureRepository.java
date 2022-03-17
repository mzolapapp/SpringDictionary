package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.SupplierNomenclature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SupplierNomenclatureRepository extends JpaRepository<SupplierNomenclature, UUID>,
        JpaSpecificationExecutor<SupplierNomenclature> {
}
