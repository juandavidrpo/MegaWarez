package com.sofka.domain;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Integer id;

    @Column(name = "user_username", nullable = false)
    private String username;

    @Column(name = "user_password", nullable = false, length = 32)
    private String password;

    @Column(name = "user_created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "user_updated_at")
    private Instant updatedAt;

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Download.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "user"
    )
    private Set<Download> downloads = new LinkedHashSet<>();

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Session.class,
            cascade = CascadeType.REMOVE,
            mappedBy = "user"
    )
    private Set<Session> sessions = new LinkedHashSet<>();
}
