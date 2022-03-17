package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.SuppNomTT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface SuppNomTTRepository extends JpaRepository<SuppNomTT, UUID>, JpaSpecificationExecutor<SuppNomTT> {
}
