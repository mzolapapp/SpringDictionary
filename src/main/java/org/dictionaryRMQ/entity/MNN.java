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
@Table(name = "mnn")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MNN {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "data_version")
    private String data_version;

    @Column(name = "delete_mark")
    private boolean delete_mark;

    @Column(name = "name")
    private String name;

    @Column(name = "full_name")
    private String full_name;

    @Column(name = "short_name")
    private String short_name;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}
