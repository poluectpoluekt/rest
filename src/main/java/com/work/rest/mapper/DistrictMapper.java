package com.work.rest.mapper;

import com.work.rest.dto.DistrictDto;
import com.work.rest.model.District;
import com.work.rest.model.Farmer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DistrictMapper {



     @Mapping(target = "farmers", ignore = true)
     District toDistrict(DistrictDto dto);

     @Mapping(source = "farmers", target = "farmers", qualifiedByName = "farmersToFarmersDTOIds")
     DistrictDto toDistrictDto(District district);

     @Named("farmersToFarmersDTOIds")
     default List<Long> farmersToFarmersIds(List<Farmer> farmers) {
          return farmers.stream().map(Farmer::getId).collect(Collectors.toList());
     }

}
