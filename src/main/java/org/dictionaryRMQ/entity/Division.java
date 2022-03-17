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
@Table(name = "division")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Division {
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

    @Column(name = "ma_address")
    private String ma_address;

    @Column(name = "ma_code")
    private String ma_code;

    @Column(name = "ma_name")
    private String ma_name;

    @Column(name = "ma_short_name")
    private String ma_short_name;

    @Column(name = "open_date")
    private LocalDateTime open_date;

    @Column(name = "close_date")
    private LocalDateTime close_date;

    @Column(name = "piu_code")
    private String piu_code;

    @Column(name = "pharmacy_net_ref")
    private UUID pharmacy_net_ref;

    @Column(name = "prices_geography_ref")
    private UUID price_geography_ref;

    @Column(name = "division_number")
    private double division_number;

    @Column(name = "entity")
    private String entity;

    @Column(name = "symphony_enabled")
    private boolean symphony_enabled;

    @Column(name = "auto_order")
    private boolean auto_order;

    @Column(name = "subject_rf")
    private UUID subjectRF;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}
