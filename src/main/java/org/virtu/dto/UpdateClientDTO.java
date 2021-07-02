package org.virtu.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateClientDTO {

    private String surName;

    private String firstName;

    private String middleName;

    private LocalDate birthday;

    private String passSeries;

    private String passNumber;

}
