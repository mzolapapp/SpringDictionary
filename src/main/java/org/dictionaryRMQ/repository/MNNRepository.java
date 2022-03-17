package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.MNN;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface MNNRepository extends JpaRepository<MNN, UUID>, JpaSpecificationExecutor<MNN> {
}
