package com.fikrat.hrms.repository;

import com.fikrat.hrms.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    List<Employee> findAllByManagerId(Long id);
    Optional<Employee> findByUserId(Long userId);

    @Query(value = "select e from Employee e join fetch e.manager")
    List<Employee> findAll();
}
