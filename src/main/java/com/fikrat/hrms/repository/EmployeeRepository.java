package com.fikrat.hrms.repository;

import com.fikrat.hrms.model.Employee;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Optional<Employee> findByUserId(Long userId);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "employee_entity_graph")
    List<Employee> findAll();

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "employee_entity_graph")
    List<Employee> findAllByManagerId(Long managerId);
}
