package com.work.rest.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FarmerFilterDTO {
    private String titleFarmer;
    private long innFarmer;
    private long registrationDistrictFarmer;
    private LocalDate dateRegistrationsFarmer;
    private boolean statusArchivedFarmer;
}
