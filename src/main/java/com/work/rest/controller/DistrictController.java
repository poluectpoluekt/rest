package com.work.rest.controller;

import com.work.rest.dto.DistrictDto;
import com.work.rest.dto.DistrictFilterDTO;
import com.work.rest.mapper.DistrictMapper;
import com.work.rest.model.District;
import com.work.rest.repository.specification.DistrictSpecification;
import com.work.rest.service.DistrictServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/district")
public class DistrictController {

    private final DistrictServiceImpl districtService;

    @Tag(name = "endpoint добавление района", description = "Принимает dto района. Проверит по атрибуту title, существует ли данный район в БД.")
    @PostMapping()
    public ResponseEntity<HttpStatus> create(@Valid @RequestBody DistrictDto districtDto){
        districtService.createDistrict(districtDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Tag(name = "endpoint поиска района", description = "Найти район по названию")
    @GetMapping()
    public DistrictDto find(@NotEmpty @RequestParam String title){
        return districtService.findDistrict(title);
    }

    @Tag(name = "endpoint реестра районов", description = "Получить реестра районов с фильтрами")
    @GetMapping("/registry")
    public List<DistrictDto> list(@RequestBody DistrictFilterDTO districtFilterDTO){

        return districtService.findList(districtFilterDTO);
    }

    @Tag(name = "endpoint изменения района", description = "Изменить район. Принимает id района и dto района с новыми параметрами.")
    @PutMapping()
    public ResponseEntity<HttpStatus> update(@RequestParam long id, @Valid @RequestBody DistrictDto districtDto){
        districtService.updateDistrict(id, districtDto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Tag(name = "endpoint архивации района", description = "Меняет статус у района, принимает id района. Только архивирует, возврат из архива не предусмотрен. ")
    @PutMapping("/archived")
    public ResponseEntity<HttpStatus> toArchived(@RequestParam long id){
        districtService.toArchived(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @Tag(name = "endpoint удаления района", description = "Удалит район по id.")
    @DeleteMapping()
    public ResponseEntity<HttpStatus> delete(@RequestParam long id){
        districtService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
