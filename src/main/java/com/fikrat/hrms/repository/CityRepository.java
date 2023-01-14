package com.fikrat.hrms.repository;

import com.fikrat.hrms.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CityRepository extends JpaRepository<City,Long> {
}
