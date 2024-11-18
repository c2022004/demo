package com.example.projectsale.supplier.entity;

import com.example.projectsale.inventory.entity.Inventory;
import com.example.projectsale.utils.base.BaseEntityUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "supplier")
public class Supplier extends BaseEntityUtil {

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "country")
    private String country;

    @Column(name = "website")
    private String website;

    @Column(name = "tax_id")
    private String taxId;

    @Column(name = "payment_terms")
    private String paymentTerms;

    @Column(name = "note")
    private String note;

    @OneToMany(mappedBy = "supplier", fetch = FetchType.LAZY)
    private List<Inventory> inventories;
}
