package com.fikrat.hrms.model;


import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "managers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@NamedEntityGraph(name = "manager_entity_graph",
        attributeNodes = {@NamedAttributeNode("user"), @NamedAttributeNode("employees")})
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "manager", cascade = CascadeType.ALL)
    private Set<Employee> employees;
}
