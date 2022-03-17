package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.OnlineCompet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface OnlineCompetRepository extends JpaRepository<OnlineCompet, UUID>, JpaSpecificationExecutor<OnlineCompet> {
}
