package com.fikrat.hrms.repository;

import com.fikrat.hrms.model.Role;
import com.fikrat.hrms.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "user_entity_graph")
    List<User> findAll();
}
