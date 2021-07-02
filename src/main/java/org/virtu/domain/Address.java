package org.virtu.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @Column(name = "country")
    private String country;

    @Column(name = "zip")
    private String zip;

    @NotNull
    @NotBlank
    @Column(name = "region")
    private String region;

    @Column(name = "district")
    private String district;

    @NotNull
    @NotBlank
    @Column(name = "city")
    private String city;

    @NotNull
    @NotBlank
    @Column(name = "street")
    private String street;

    @Column(name = "house")
    private String house;

    @Column(name = "housing")
    private String housing;

    @Column(name = "building")
    private String building;

    @NotNull
    @NotBlank
    @Column(name = "appartment")
    private String appartment;

    @NotNull
    @NotBlank
    @Column(name = "comment")
    private String comment;

}
