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
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Employee {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "data_version")
    private String data_version;

    @Column(name = "name")
    private String name;

    @Column(name = "delete_mark")
    private boolean delete_mark;

    @Column(name = "code")
    private int code;

    @Column(name = "hire_date")
    private LocalDateTime hire_date;

    @Column(name = "free_date")
    private LocalDateTime free_date;

    @Column(name = "position")
    private String position;

    @Column(name = "division_ref")
    private UUID division_ref;

    @Column(name = "pharmacy_net_ref")
    private UUID pharmacy_net_ref;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}
