package org.virtu.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import java.time.LocalDate;

@Data
public class CreateClientDTO {

    private String surName;

    private String firstName;

    private String middleName;

    private LocalDate birthday;

    private String passSeries;

    private String passNumber;

}
