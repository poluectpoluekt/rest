package com.work.rest.controller;


import com.work.rest.dto.DistrictsTitleDTO;
import com.work.rest.dto.FarmerDto;
import com.work.rest.dto.FarmerFilterDTO;
import com.work.rest.service.FarmerServiceImpl;
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

    @PostMapping()
    public ResponseEntity<HttpStatus> createFarmer(@RequestBody FarmerDto farmerDto){
        farmerService.create(farmerDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestParam long id, @RequestBody FarmerDto farmerDto){
        farmerService.update(id,farmerDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/registryFarmers")
    public List<FarmerDto> getRegistryfarmers(@RequestBody FarmerFilterDTO farmerFilterDTO){
        return farmerService.getListInRegistry(farmerFilterDTO);
    }


    @PutMapping("/toArchived")
    public ResponseEntity<HttpStatus> farmerToArchived(@RequestParam long id){
        farmerService.toArchived(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
