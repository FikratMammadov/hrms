package com.fikrat.hrms.service.impl;

import com.fikrat.hrms.dto.city.CityDto;
import com.fikrat.hrms.exception.CityNotFoundException;
import com.fikrat.hrms.mapper.CityMapper;
import com.fikrat.hrms.model.City;
import com.fikrat.hrms.repository.CityRepository;
import com.fikrat.hrms.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    @Override
    public City add(CityDto cityDto) {
        log.info("ActionLog.addCity.start");
        City city = CityMapper.INSTANCE.cityDtoToCity(cityDto);
        log.info("ActionLog.addCity.end");
        return cityRepository.save(city);
    }

    @Override
    public void update(Long id, CityDto dto) {
        log.info("ActionLog.updateCity.start");
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found"));
        city.setName(dto.getName());
        cityRepository.save(city);
        log.info("ActionLog.updateCity.end");
    }

    @Override
    public CityDto get(Long id) {
        log.info("ActionLog.getCity.start");
        City city = cityRepository.findById(id).orElseThrow(() -> new CityNotFoundException("City not found"));
        log.info("ActionLog.getCity.end");
        return CityMapper.INSTANCE.cityToCityDto(city);
    }

    @Override
    public List<CityDto> getList() {
        log.info("ActionLog.getCities.start");
        List<CityDto> cities = CityMapper.INSTANCE.citiesToCityDtoList(cityRepository.findAll());
        log.info("ActionLog.getCities.end");
        return cities;
    }
}
