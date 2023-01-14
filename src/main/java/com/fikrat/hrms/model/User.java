package com.fikrat.hrms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedEntityGraph(name = "user_entity_graph",
        attributeNodes = {@NamedAttributeNode("city"), @NamedAttributeNode("employee"), @NamedAttributeNode("manager")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private Manager manager;
}
