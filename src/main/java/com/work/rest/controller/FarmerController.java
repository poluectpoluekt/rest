package com.work.rest.controller;


import com.work.rest.dto.DistrictsTitleDTO;
import com.work.rest.dto.FarmerDto;
import com.work.rest.dto.FarmerFilterDTO;
import com.work.rest.service.FarmerServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/farmer")
public class FarmerController {

    private final FarmerServiceImpl farmerService;

    @Tag(name = "endpoint добавления нового фермера", description = "Принимает dto фермера. Проверит по атрибуту title, существует ли фермер в БД")
    @PostMapping()
    public ResponseEntity<HttpStatus> createFarmer(@RequestBody FarmerDto farmerDto){
        farmerService.create(farmerDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Tag(name = "endpoint поиска изменения фермера", description = "Принимает id фермера и dto с новыми параметрами. Проверит наличие записи фермера в БД.")
    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestParam long id, @RequestBody FarmerDto farmerDto){
        farmerService.update(id,farmerDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Tag(name = "endpoint реестра фермера", description = "Получить реестра фермеров по фильтру.")
    @GetMapping("/registryFarmers")
    public List<FarmerDto> getRegistryfarmers(@RequestBody FarmerFilterDTO farmerFilterDTO){
        return farmerService.getListInRegistry(farmerFilterDTO);
    }


    @Tag(name = "endpoint архивации фермера", description = "Меняет статус архивности у записи фермера.")
    @PutMapping("/toArchived")
    public ResponseEntity<HttpStatus> farmerToArchived(@RequestParam long id){
        farmerService.toArchived(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
