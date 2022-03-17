package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.ElasticityDictionaries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ElasticityDictionariesRepository
        extends JpaRepository<ElasticityDictionaries, UUID>, JpaSpecificationExecutor<ElasticityDictionaries> {
}
