package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.PredefinedValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PredefinedValuesRepository extends JpaRepository<PredefinedValues, UUID>,
        JpaSpecificationExecutor<PredefinedValues> {
}
