package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.PharmacyNet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PharmacyNetRepository extends JpaRepository<PharmacyNet, UUID>,
        JpaSpecificationExecutor<PharmacyNet> {
}
