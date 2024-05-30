package com.work.rest.repository;

import com.work.rest.dto.DistrictsTitleDTO;
import com.work.rest.dto.FarmerDto;
import com.work.rest.exception.FarmerNotFoundException;
import com.work.rest.model.District;
import com.work.rest.model.Farmer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class FarmerDAO {

    @PersistenceContext
    private final EntityManager entityManager;


    public void update(long id, FarmerDto farmerDto) {
        Farmer farmer = entityManager.find(Farmer.class, id);
        if (farmer == null) {
            throw new FarmerNotFoundException(String.format("Farmer with \"%s\" not found.", farmerDto.getTitle()));
        }
        farmer.setTitle(farmer.getTitle());
        farmer.setLegalForm(farmer.getLegalForm());
        farmer.setInn(farmer.getInn());
        farmer.setKpp(farmer.getKpp());
        farmer.setOgrn(farmer.getOgrn());
        farmer.setDateRegistrations(farmerDto.getDateRegistrations());
        farmer.setStatusArchived(farmer.isStatusArchived());
        entityManager.merge(farmer);

    }

    public List<Farmer> getListFarmer() {
        TypedQuery<Farmer> farmers = entityManager.createQuery("SELECT f FROM Farmer f WHERE f.statusArchived = false", Farmer.class);
        return farmers.getResultList();
    }

    public void toArchived(long id) {
        Farmer farmer = entityManager.find(Farmer.class, id);
        if (farmer == null) {
            throw new FarmerNotFoundException(String.format("Farmer with \"%s\" not found.", id));
        }
        farmer.setStatusArchived(true);
    }

    public District findDistrict(long id) {
        return entityManager.find(District.class, id);
    }


}
