package com.sofka.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "subcategory")
public class Subcategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_scat", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Category.class, optional = false)
    @JoinColumn(name = "scat_category_id", nullable = false)
    private Category category;

    @Column(name = "scat_created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Item.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "subcategory"
    )
    private Set<Item> items = new LinkedHashSet<>();
}
