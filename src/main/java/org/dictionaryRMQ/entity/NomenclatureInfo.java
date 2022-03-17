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
@Table(name = "nomenclature_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NomenclatureInfo {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "nomenclature_ref")
    private UUID nomenclature_ref;

    @Column(name = "supplier_ref")
    private UUID supplier_ref;

    @Column(name = "division_ref")
    private UUID division_ref;

    @Column(name = "data_version")
    private String data_version;

    @Column(name = "name")
    private String name;

    @Column(name = "mz_producer_series")
    private String mz_producer_series;

    @Column(name = "mz_producer_country")
    private String mz_producer_country;

    @Column(name = "mz_producer")
    private String mz_producer;

    @Column(name = "mz_description")
    private String mz_description;

    @Column(name = "mz_vat_percent")
    private String mz_vat_percent;

    @Column(name = "mz_scan_code")
    private String mz_scan_code;

    @Column(name = "mz_producer_scan_code")
    private String mz_producer_scan_code;

    @Column(name = "delete_mark")
    private boolean delete_mark;

    @Column(name = "mz_is_commission")
    private boolean mz_is_commission;

    @Column(name = "mz_is_internet_product")
    private boolean mz_is_internet_product;

    @Column(name = "mz_is_product_cp")
    private boolean mz_is_product_cp;

    @Column(name = "mz_is_copy")
    private boolean mz_is_copy;

    @Column(name = "marked_drug")
    private boolean marked_drug;

    @Column(name = "mz_was_internet_product")
    private boolean mz_was_internet_product;

    @Column(name = "mz_use_before_changed")
    private boolean mz_use_before_changed;

    @Column(name = "mz_income_price")
    private double mz_income_price;

    @Column(name = "mz_producer_price_no_vat")
    private double mz_producer_price_no_vat;

    @Column(name = "mz_producer_price_vat")
    private double mz_producer_price_vat;

    @Column(name = "mz_supplier_price_no_vat")
    private double mz_supplier_price_no_vat;

    @Column(name = "mz_jnvlp_price")
    private double mz_jnvlp_price;

    @Column(name = "mz_internet_sale_price")
    private double mz_internet_sale_price;

    @Column(name = "mz_tender_price")
    private double mz_tender_price;

    @Column(name = "mz_packing_divider")
    private double mz_packing_divider;

    @Column(name = "mz_mark")
    private int mz_mark;

    @Column(name = "mz_use_before")
    private LocalDateTime mz_use_before;

    @CreationTimestamp
    @Column(name = "inserted_at", updatable = false)
    private LocalDateTime inserted_at;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_at;

}
