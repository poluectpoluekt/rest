package com.work.rest.repository;

import com.work.rest.model.Farmer;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public interface FarmerRepository extends JpaRepository<Farmer, Long>, JpaSpecificationExecutor<Farmer> {

    Optional<Farmer> findByTitle(String title);

}
