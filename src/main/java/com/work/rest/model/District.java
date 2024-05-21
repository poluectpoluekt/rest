package com.work.rest.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "District")
@Entity
public class District {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_district")
    @SequenceGenerator(name = "sequence_district", sequenceName = "district_main_sequence", allocationSize = 1)
    @Column(name = "id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "code")
    private int code;
    @Column(name = "status")
    private boolean statusArchived;

    @OneToMany(mappedBy = "registrationDistrictId", fetch = FetchType.LAZY)
    private List<Farmer> farmers;

}
