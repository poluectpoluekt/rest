package com.work.rest.mapper;

import com.work.rest.dto.FarmerDto;
import com.work.rest.model.Farmer;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FarmerMapper {

    @Mapping(target = "legalForm", source = "farmerDto.legalForm")
    @Mapping(target = "registrationDistrictId", ignore = true)
    Farmer toFarmer(FarmerDto farmerDto);


    @Mapping(target = "registrationDistrictId", source = "farmer.registrationDistrictId.id")
    FarmerDto toFarmerDto(Farmer farmer);
}
