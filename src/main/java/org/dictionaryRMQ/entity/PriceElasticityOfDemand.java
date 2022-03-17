package org.dictionaryRMQ.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "price_elasticity_of_demand")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(PriceElasticityOfDemandPK.class)
public class PriceElasticityOfDemand {
    @Id
    @Column(name = "id")
    private UUID id;

    @Id
    @Column(name = "product")
    private UUID product;

    @Column(name = "name")
    private String name;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}

@AllArgsConstructor
@NoArgsConstructor
class PriceElasticityOfDemandPK implements Serializable {
    protected UUID id;
    protected UUID product;
}
