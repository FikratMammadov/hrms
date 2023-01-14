package com.fikrat.hrms.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cities")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "city", cascade = CascadeType.ALL)
    private Set<User> users;
}
