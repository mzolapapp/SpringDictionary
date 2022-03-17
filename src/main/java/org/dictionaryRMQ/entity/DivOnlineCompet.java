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
@Table(name = "division_online_сompet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@IdClass(DivOnlineCompetPK.class)
public class DivOnlineCompet {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "mz_division_ref")
    private UUID mz_division_ref;

    @Id
    @Column(name = "online_compet_ref")
    private UUID online_compet_ref;

    @Column(name = "subject_rf")
    private String subject_rf;

    @Column(name = "locality_for_pars")
    private String locality_for_pars;

    @Column(name = "locality")
    private String locality;

    @Column(name = "addr_div_compet")
    private String addr_div_compet;

    @Column(name = "type_locality_full")
    private String type_locality_full;

    @Column(name = "type_locality")
    private String type_locality;

    @Column(name = "period")
    private LocalDateTime period;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}

@AllArgsConstructor
@NoArgsConstructor
class DivOnlineCompetPK implements Serializable {
    protected UUID id;
    protected UUID online_compet_ref;
}