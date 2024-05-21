package com.work.rest.service;

import com.work.rest.dto.FarmerDto;
import com.work.rest.exception.FarmerAlreadyExistException;
import com.work.rest.mapper.FarmerMapper;
import com.work.rest.model.Farmer;
import com.work.rest.repository.FarmerDAO;
import com.work.rest.repository.FarmerRepository;
import com.work.rest.repository.specification.FarmerSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class FarmerServiceTest {


    @Mock
    FarmerRepository repository;

    @Mock
    FarmerDAO farmerDAO;

    @Mock
    FarmerSpecification farmerSpecification;

    @Mock
    FarmerMapper farmerMapper;

    @InjectMocks
    FarmerServiceImpl farmerService;


    @DisplayName("Возврат исключения, если farmer уже существует в БД")
    @Test
    void testCreateFarmer_WhenFarmerExists_ThrowsException(){
        FarmerDto farmerDto = new FarmerDto();
        farmerDto.setTitle("Farmer Name");

        Mockito.when(repository.findByTitle(any())).thenReturn(Optional.of(new Farmer()));

        assertThrows(FarmerAlreadyExistException.class, () -> farmerService.create(farmerDto));
    }

    @DisplayName("Добавляет farmer в БД")
    @Test
    void testCreateFarmer_WhenFarmerDoesNotExist_SavesFarmer(){
        FarmerDto farmerDto = new FarmerDto();
        Farmer farmer = new Farmer();

        Mockito.when(repository.findByTitle(any())).thenReturn(Optional.empty());
        Mockito.when(farmerMapper.toFarmer(any())).thenReturn(farmer);

        farmerService.create(farmerDto);
        Mockito.verify(repository, times(1)).save(any(Farmer.class));
    }
}
