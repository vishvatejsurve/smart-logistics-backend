package com.logistics_system.mapper;

import com.logistics_system.dto.driver.DriverRequestDTO;
import com.logistics_system.dto.driver.DriverResponseDTO;
import com.logistics_system.entity.Driver;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    Driver toEntity(DriverRequestDTO dto);
    DriverResponseDTO toDTO(Driver driver);

    List<DriverResponseDTO> toDTOList(List<Driver> drivers);

}
