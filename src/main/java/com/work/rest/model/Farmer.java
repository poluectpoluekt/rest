package com.work.rest.model;


import com.work.rest.util.LegalFormEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Farmer")
@Entity
public class Farmer {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequence_farmer")
    @SequenceGenerator(name = "sequence_farmer", sequenceName = "farmer_main_sequence", allocationSize = 1)
    private long id;
    @Column(name = "title")
    private String title;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "legalform")
    private LegalFormEnum legalForm;
    @Column(name = "inn")
    private long inn;
    @Column(name = "kpp")
    private int kpp;
    @Column(name = "ogrn")
    private long ogrn;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "registration_district")
    private District registrationDistrictId;

    @ManyToMany
    @JoinTable(name = "District_Farmer",
            joinColumns = @JoinColumn(name = "district_id"),
            inverseJoinColumns = @JoinColumn(name = "farmer_id"))
    private Set<District> DistrictCropFields;

    @Column(name = "registration_date")
    @Temporal(TemporalType.DATE)
    private LocalDate dateRegistrations;
    @Column(name = "status_archived")
    private boolean statusArchived;

}
