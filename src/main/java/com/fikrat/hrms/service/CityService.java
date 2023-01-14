package com.fikrat.hrms.service;

import com.fikrat.hrms.dto.city.CityDto;
import com.fikrat.hrms.model.City;

import java.util.List;

public interface CityService {
    City add(CityDto cityDto);

    void delete(Long id);

    City update(Long id, CityDto dto);

    CityDto get(Long id);

    List<CityDto> getList();
}
