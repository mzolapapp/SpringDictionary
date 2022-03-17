package org.dictionaryRMQ.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "elasticity_of_demand_dictionaries")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ElasticityDictionaries {
    @Id
    @Column(name = "division")
    private UUID division;

    @Column(name = "product_list_elastic")
    private UUID productListElastic;

    @Column(name = "product_list_non_elastic")
    private UUID productListNonElastic;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
