package com.fikrat.hrms.repository;

import com.fikrat.hrms.model.City;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CityRepository extends JpaRepository<City,Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "city_entity_graph")
    List<City> findAll();
}
