package com.work.rest.repository.specification;

import com.work.rest.dto.DistrictFilterDTO;
import com.work.rest.dto.FarmerFilterDTO;
import com.work.rest.model.District;
import com.work.rest.model.Farmer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FarmerSpecification {

    public Specification<Farmer> buildListFarmerByFilter(FarmerFilterDTO farmerFilterDTO){
        return byTitle(farmerFilterDTO.getTitleFarmer())
                .and(byInn(farmerFilterDTO.getInnFarmer()))
                .and(byRegistrationDistrict(farmerFilterDTO.getRegistrationDistrictFarmer()))
                .and(byDateRegistrations(farmerFilterDTO.getDateRegistrationsFarmer()))
                .and(byStatusArchived(farmerFilterDTO.isStatusArchivedFarmer()));
    }

    public Specification<Farmer> byTitle(String title){
        return ((root, query, criteriaBuilder) -> title == null ? criteriaBuilder.conjunction() : criteriaBuilder
                .equal(root.get("title"), title));
    }

    public Specification<Farmer> byInn(long inn){
        return ((root, query, criteriaBuilder) -> inn == 0 ? criteriaBuilder.conjunction() : criteriaBuilder
                .equal(root.get("inn"), inn));
    }

    public Specification<Farmer> byRegistrationDistrict(long registrationDistrictId){
        return ((root, query, criteriaBuilder) -> registrationDistrictId == 0 ? criteriaBuilder.conjunction() : criteriaBuilder
                .equal(root.get("registrationDistrictId"), registrationDistrictId));
    }

    public Specification<Farmer> byDateRegistrations(LocalDate dateRegistrations){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("dateRegistrations"), dateRegistrations));
    }

    public Specification<Farmer> byStatusArchived(boolean status){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("statusArchived"), status));
    }

}
