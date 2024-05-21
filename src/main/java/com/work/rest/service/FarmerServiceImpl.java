package com.work.rest.service;

import com.work.rest.dto.DistrictDto;
import com.work.rest.dto.DistrictsTitleDTO;
import com.work.rest.dto.FarmerDto;
import com.work.rest.dto.FarmerFilterDTO;
import com.work.rest.exception.FarmerAlreadyExistException;
import com.work.rest.mapper.FarmerMapper;
import com.work.rest.model.Farmer;
import com.work.rest.repository.FarmerDAO;
import com.work.rest.repository.FarmerRepository;
import com.work.rest.repository.specification.FarmerSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FarmerServiceImpl implements FarmerService {

    private final FarmerDAO farmerDAO;
    private final FarmerMapper mapper;
    private final FarmerRepository repository;
    private final FarmerSpecification farmerSpecification;

    @Transactional
    @Override
    public void create(FarmerDto farmerDto) {
        if(repository.findByTitle(farmerDto.getTitle()).isPresent()){
            throw new FarmerAlreadyExistException(String.format("This \"%s\" already exists.", farmerDto.getTitle()));
        } else {
            Farmer farmer = mapper.toFarmer(farmerDto);
            farmer.setDateRegistrations(LocalDate.now());
            farmer.setRegistrationDistrictId(farmerDAO.findDistrict(farmerDto.getRegistrationDistrictId()));
            repository.save(farmer);
        }
    }

    @Override
    public void update(long id, FarmerDto farmerDto) {
        farmerDAO.update(id, farmerDto);
    }

    @Override
    public List<FarmerDto> getListInRegistry(FarmerFilterDTO farmerFilterDTO) {
        Specification<Farmer> spec = farmerSpecification.buildListFarmerByFilter(farmerFilterDTO);
        return repository.findAll(spec).stream().map(mapper::toFarmerDto).collect(Collectors.toList());
    }


    @Override
    public void toArchived(long id) {
        farmerDAO.toArchived(id);
    }
}
