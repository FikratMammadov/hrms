package com.fikrat.hrms.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class EmployeeMapper {
    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
}
