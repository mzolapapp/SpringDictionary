package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.HeadSupp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface HeadSuppRepository extends JpaRepository<HeadSupp, UUID>, JpaSpecificationExecutor<HeadSupp> {
}
