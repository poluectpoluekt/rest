package com.work.rest.dto;

import com.work.rest.util.LegalFormEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class FarmerDto {

    @NotEmpty(message = "Title should not be empty.")
    @Size(min = 6, max = 50, message = "Title should between 6 and 50 characters.")
    private String title;
    private LegalFormEnum legalForm;
    private long inn;
    private int kpp;
    private long ogrn;
    private long registrationDistrictId;
    private LocalDate dateRegistrations;
    private boolean statusArchived;

}
