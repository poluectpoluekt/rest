package com.work.rest.service;

import com.work.rest.dto.DistrictDto;
import com.work.rest.dto.DistrictFilterDTO;
import com.work.rest.exception.DistrictAlreadyExistException;
import com.work.rest.exception.DistrictNotFoundException;
import com.work.rest.mapper.DistrictMapper;
import com.work.rest.model.District;
import com.work.rest.repository.DistrictRepository;
import com.work.rest.repository.FarmerRepository;
import com.work.rest.repository.specification.DistrictSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictMapper mapper;
    private final DistrictRepository repository;
    private final DistrictSpecification districtSpecification;
    private FarmerRepository farmerRepository;


    @Transactional
    @Override
    public void createDistrict(DistrictDto districtDto) {

        if(repository.findByTitle(districtDto.getTitle()).isPresent()){
            throw new DistrictAlreadyExistException(districtDto.getTitle());
        }
            District district = mapper.toDistrict(districtDto);
            district.setFarmers(districtDto.getFarmers().stream()
                    .map(f -> farmerRepository.findById(f).orElse(null)).collect(Collectors.toList())); // нужно бы уточнить по ТЗ, что делать если в списке, нет фермера с id
            repository.save(district); // проверить не сломалось ли
    }

    public DistrictDto findDistrict(String title){
        return mapper.toDistrictDto(repository.findByTitle(title)
                .orElseThrow(()-> new DistrictNotFoundException(title)));
    }

    public List<DistrictDto> findList(DistrictFilterDTO districtFilterDTO){
        Specification<District> spec = districtSpecification.listByTitleAndCode(districtFilterDTO);
        return repository.findAll(spec).stream().map(mapper::toDistrictDto).collect(Collectors.toList());
    }

    @Transactional
    public void updateDistrict(long id, DistrictDto districtDto){
        District districtUpdate = repository.findById(id).orElseThrow(()-> new DistrictNotFoundException(Long.toString(id)));
        districtUpdate.setTitle(districtUpdate.getTitle());
        districtUpdate.setCode(districtDto.getCode());
        districtUpdate.setStatusArchived(districtDto.isStatusArchived());
        districtUpdate.setFarmers(districtDto.getFarmers().stream()
                .map(f -> farmerRepository.findById(f).orElse(null)).collect(Collectors.toList()));
        repository.save(districtUpdate);
    }

    @Transactional
    public void toArchived(long id){
        District districtUpdate = repository.findById(id).orElseThrow(()-> new DistrictNotFoundException(Long.toString(id)));
        districtUpdate.setStatusArchived(true);
        repository.save(districtUpdate);
    }

    @Transactional
    public void delete(long id){
        repository.deleteById(id);
    }

}
