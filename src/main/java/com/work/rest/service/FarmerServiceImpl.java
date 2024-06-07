package com.work.rest.service;

import com.work.rest.dto.FarmerDto;
import com.work.rest.dto.FarmerFilterDTO;
import com.work.rest.exception.FarmerAlreadyExistException;
import com.work.rest.exception.FarmerNotFoundException;
import com.work.rest.mapper.FarmerMapper;
import com.work.rest.model.Farmer;
import com.work.rest.repository.DistrictRepository;
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
    private final FarmerMapper mapper;
    private final FarmerRepository repository;
    private final FarmerSpecification farmerSpecification;
    private final DistrictRepository districtRepository;

    @Transactional
    @Override
    public void create(FarmerDto farmerDto) {
        if (repository.findByTitle(farmerDto.getTitle()).isPresent()) {
            throw new FarmerAlreadyExistException(farmerDto.getTitle());
        }
        Farmer farmer = mapper.toFarmer(farmerDto);
        farmer.setDateRegistrations(LocalDate.now());
        farmer.setRegistrationDistrictId(districtRepository.findById(farmerDto.getRegistrationDistrictId()).orElse(null));
        repository.save(farmer);
    }

    @Override
    public void update(long id, FarmerDto farmerDto) {
        Farmer farmer = repository.findById(id).orElseThrow(()-> new FarmerNotFoundException(Long.toString(id)));
        farmer.setTitle(farmerDto.getTitle());
        farmer.setLegalForm(farmerDto.getLegalForm());
        farmer.setInn(farmerDto.getInn());
        farmer.setKpp(farmerDto.getKpp());
        farmer.setOgrn(farmerDto.getOgrn());
        farmer.setDateRegistrations(farmerDto.getDateRegistrations());
    }

    @Override
    public List<FarmerDto> getListInRegistry(FarmerFilterDTO farmerFilterDTO) {
        Specification<Farmer> spec = farmerSpecification.buildListFarmerByFilter(farmerFilterDTO);
        return repository.findAll(spec).stream().map(mapper::toFarmerDto).collect(Collectors.toList());
    }


    @Override
    public void toArchived(long id) {
        Farmer farmer = repository.findById(id).orElseThrow(()-> new FarmerNotFoundException(Long.toString(id)));
        farmer.setStatusArchived(true);
    }
}
