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
@Table(name = "onlineсompet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OnlineCompet {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "as_compet")
    private UUID as_compet;

    @Column(name = "site")
    private String site;

    @Column(name = "type_compet")
    private String type_compet;

    @Column(name = "type_sale")
    private String type_sale;

    @Column(name = "type_price")
    private String type_price;

    @Column(name = "can_parse")
    private boolean can_parse;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;
}
