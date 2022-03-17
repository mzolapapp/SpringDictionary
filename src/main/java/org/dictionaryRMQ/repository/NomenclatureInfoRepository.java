package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.NomenclatureInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface NomenclatureInfoRepository extends JpaRepository<NomenclatureInfo, UUID>, JpaSpecificationExecutor<NomenclatureInfo> {
}
