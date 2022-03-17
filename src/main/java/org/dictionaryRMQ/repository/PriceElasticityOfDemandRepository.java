package org.dictionaryRMQ.repository;

import org.dictionaryRMQ.entity.PriceElasticityOfDemand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface PriceElasticityOfDemandRepository
        extends JpaRepository<PriceElasticityOfDemand, UUID>, JpaSpecificationExecutor<PriceElasticityOfDemand> {
    PriceElasticityOfDemand findByIdAndAndProduct(UUID id, UUID product);
}
