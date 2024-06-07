package com.work.rest.repository;

import com.work.rest.dto.DistrictDto;
import com.work.rest.exception.DistrictNotFoundException;
import com.work.rest.model.District;
import com.work.rest.model.Farmer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс DistrictDAO cоздан, в качестве демонстративного работы Hibernate, в код не используется.
 */
@AllArgsConstructor
//@Component
public class DistrictDAO {

    @PersistenceContext
    private final EntityManager entityManager;

    public List<District> getListDistrict(){

        TypedQuery<District> query = entityManager.createQuery("SELECT d FROM District d WHERE d.status = false", District.class);
        return query.getResultList();
    }

    @Transactional
    public void edit(long id, DistrictDto districtDto){

        District district = entityManager.find(District.class, id);
        if(district!=null){
            district.setTitle(district.getTitle());
            district.setCode(districtDto.getCode());
            district.setStatusArchived(districtDto.isStatusArchived());
            district.setFarmers(districtDto.getFarmers().stream().map(this::findFarmer).collect(Collectors.toList()));
            entityManager.merge(district);
        } throw new DistrictNotFoundException(String.format("District with \"%s\" id not found.", id));
    }

    public Farmer findFarmer(long id){
        return entityManager.find(Farmer.class, id);
    }

    @Transactional
    public void toArchived(long id){
        District district = entityManager.find(District.class, id);
        district.setStatusArchived(true);
    }

    @Transactional
    public void delete(long id){
        District district = entityManager.find(District.class, id);
        if(district!=null) {
            entityManager.remove(district);
        } else throw new DistrictNotFoundException(String.format("District with \"%s\" id not found.", id));
    }

}
