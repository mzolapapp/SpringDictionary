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
@Table(name = "suppliernomencl")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SupplierNomenclature {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "owner_ref")
    private UUID owner_ref;

    @Column(name = "name")
    private String name;

    @Column(name = "supplier_name")
    private String supplies_name;

    @Column(name = "has_no_tt")
    private boolean has_no_tt;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
