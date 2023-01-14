package com.fikrat.hrms.mapper;

import com.fikrat.hrms.dto.city.CityDto;
import com.fikrat.hrms.model.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class CityMapper {
    public static final CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    @Mappings(
            @Mapping(source = "name",target = "name")
    )
    public abstract CityDto cityToCityDto(City city);

    @Mappings(
            @Mapping(source = "name",target = "name")
    )
    public abstract City cityDtoToCity(CityDto cityDto);


    public abstract List<CityDto> citiesToCityDtoList(List<City> cities);
}
