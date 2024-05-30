package com.work.rest.service;

import com.work.rest.dto.DistrictDto;
import com.work.rest.dto.DistrictFilterDTO;
import com.work.rest.exception.DistrictAlreadyExistException;
import com.work.rest.exception.DistrictNotFoundException;
import com.work.rest.mapper.DistrictMapper;
import com.work.rest.model.District;
import com.work.rest.repository.DistrictDAO;
import com.work.rest.repository.DistrictRepository;
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
    private final DistrictDAO districtDAO;
    private final DistrictSpecification districtSpecification;


    @Transactional
    @Override
    public void createDistrict(DistrictDto districtDto) {

        if(repository.findByTitle(districtDto.getTitle()).isPresent()){
            throw new DistrictAlreadyExistException(String.format("This \"%s\" already exists.", districtDto.getTitle()));
        } else {
            District district = mapper.toDistrict(districtDto);
            district.setFarmers(districtDto.getFarmers().stream().map(districtDAO::findFarmer).collect(Collectors.toList()));
            repository.save(district); // проверить не сломалось ли
        }

    }

    public DistrictDto findDistrict(String title){
        return mapper.toDistrictDto(repository.findByTitle(title)
                .orElseThrow(()-> new DistrictNotFoundException(String.format("District with the \"%s\" was not found", title))));
    }

    public List<DistrictDto> findList(DistrictFilterDTO districtFilterDTO){
        Specification<District> spec = districtSpecification.listByTitleAndCode(districtFilterDTO);
        return repository.findAll(spec).stream().map(mapper::toDistrictDto).collect(Collectors.toList());
    }

    @Transactional
    public void updateDistrict(long id, DistrictDto districtDto){
        districtDAO.edit(id, districtDto);
    }

    @Transactional
    public void toArchived(long id){
        districtDAO.toArchived(id); // можно сделать без dao, на репозитории
    }

    @Transactional
    public void delete(long id){
        districtDAO.delete(id);
    }
}
