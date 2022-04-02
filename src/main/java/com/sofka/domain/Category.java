package com.sofka.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;


@Data
@Entity
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cat", nullable = false)
    private Integer id;

    @Column(name = "cat_name", nullable = false)
    private Instant createdAt;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Subcategory.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "category"
    )
    private Set<Subcategory> subcategories = new LinkedHashSet<>();
}
