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
@Table(name = "supp_nom_tt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuppNomTT {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "nomenclature_ref")
    private UUID nomenclature_ref;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
