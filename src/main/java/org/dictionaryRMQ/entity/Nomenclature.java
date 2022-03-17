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
@Table(name = "nomenclature")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Nomenclature {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "data_version")
    private String data_version;

    @Column(name = "delete_mark")
    private boolean delete_mark;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "ma_id")
    private int ma_id;

    @Column(name = "ma_code")
    private int ma_code;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "producer_ref")
    private UUID producer_ref;

    @Column(name = "not_in_use")
    private boolean not_in_use;

    @Column(name = "spec_group_ref")
    private UUID spec_group_ref;

    @Column(name = "mnn_ref")
    private UUID mnn_ref;

    @Column(name = "drug_form_ref")
    private UUID drug_form_ref;

    @Column(name = "vat")
    private String vat;

    @Column(name = "new_name")
    private String new_name;

    @Column(name = "jnvlp")
    private boolean jnvlp;

    @Column(name = "is_ps")
    private boolean is_ps;

    @Column(name = "is_archive")
    private boolean is_archive;

    @Column(name = "is_new")
    private boolean is_new;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

    @Column(name = "cluster")
    private String cluster;
}
