package com.work.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DistrictDto {

    @NotEmpty(message = "Title should not be empty.")
    @Size(min = 6, max = 50, message = "Title should between 6 and 50 characters.")
    private String title;
    private int code;
    private boolean statusArchived;
    private List<Long> farmers;

}
