package com.work.rest.service;

import com.work.rest.dto.DistrictDto;
import com.work.rest.dto.DistrictsTitleDTO;
import com.work.rest.dto.FarmerDto;
import com.work.rest.dto.FarmerFilterDTO;

import java.util.List;

public interface FarmerService {

    public void create(FarmerDto farmerDto);

    public void update(long id, FarmerDto farmerDto);

    public List<FarmerDto> getListInRegistry(FarmerFilterDTO farmerFilterDTO);

    public void toArchived(long id);

}
