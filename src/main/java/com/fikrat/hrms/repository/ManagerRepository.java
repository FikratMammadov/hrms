package com.fikrat.hrms.repository;

import com.fikrat.hrms.model.Manager;
import com.fikrat.hrms.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
    boolean existsManagerByUser(User user);
    Manager findByUserId(Long userId);
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "manager_entity_graph")
    List<Manager> findAll();
}
