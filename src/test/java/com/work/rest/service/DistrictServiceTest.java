package com.work.rest.service;


import com.work.rest.dto.DistrictDto;
import com.work.rest.exception.DistrictAlreadyExistException;
import com.work.rest.exception.DistrictNotFoundException;
import com.work.rest.mapper.DistrictMapper;
import com.work.rest.model.District;
import com.work.rest.repository.DistrictRepository;
import com.work.rest.repository.specification.DistrictSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class DistrictServiceTest {

    @Mock
    DistrictRepository repository;

    @Mock
    DistrictSpecification districtSpecification;

    @Mock
    DistrictMapper districtMapper;

    @InjectMocks
    DistrictServiceImpl districtService;


    @DisplayName("Возврат исключения, если district уже существует в БД")
    @Test
    void testCreateDistrict_WhenDistrictExists_ThrowsException(){
        DistrictDto districtDto = new DistrictDto();
        districtDto.setTitle("District Name");

        Mockito.when(repository.findByTitle(any())).thenReturn(Optional.of(new District()));

        assertThrows(DistrictAlreadyExistException.class, () -> districtService.createDistrict(districtDto));
    }

    @DisplayName("Добавляет район в БД")
    @Test
    void testCreateDistrict_WhenDistrictDoesNotExist_SavesDistrict() throws Exception {
        DistrictDto districtDto = new DistrictDto();
        districtDto.setTitle("New");
        districtDto.setFarmers(Collections.emptyList());

        District district = new District();
        district.setTitle(districtDto.getTitle());

        Mockito.when(repository.findByTitle(any())).thenReturn(Optional.empty());
        Mockito.when(districtMapper.toDistrict(any())).thenReturn(district);

        districtService.createDistrict(districtDto);

        Mockito.verify(repository, times(1)).save(any(District.class));
    }


    @Test
    void testFindDistrict_WhenDistrictNotFound_ThrowsException(){
        String title = "NoExistent District";

        assertThrows(DistrictNotFoundException.class, () -> districtService.findDistrict(title));
    }

}
