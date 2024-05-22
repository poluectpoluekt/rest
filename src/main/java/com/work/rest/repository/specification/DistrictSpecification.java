package com.work.rest.repository.specification;


import com.work.rest.dto.DistrictFilterDTO;
import com.work.rest.model.District;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DistrictSpecification {

    public Specification<District> listByTitleAndCode(DistrictFilterDTO paramsFilter){
        return byTitle(paramsFilter.getTitleDistrict()).and(byCode(paramsFilter.getCodeDistrict())
                .and(byStatusArchived()));
    }

    public Specification<District> byTitle(String title){
        return ((root, query, criteriaBuilder) -> title == null ? criteriaBuilder.conjunction() : criteriaBuilder
                .equal(root.get("title"), title));
    }

    public Specification<District> byCode(long code){
        return ((root, query, criteriaBuilder) -> code == 0 ? criteriaBuilder.conjunction() : criteriaBuilder
                .equal(root.get("code"), code));
    }

    public Specification<District> byStatusArchived(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("statusArchived"), false));

    }

}
