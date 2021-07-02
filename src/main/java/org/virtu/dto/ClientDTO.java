package org.virtu.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.virtu.domain.Client;
import org.virtu.domain.Doc;

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

@Data
public class ClientDTO {

    private int id;

    private String surName;

    private String firstName;

    private String middleName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDate birthday;

    private String passSeries;

    private String passNumber;

    private String fio;

    public ClientDTO(Client client) {
        id = client.getId();
        surName = client.getSurName();
        firstName = client.getFirstName();
        middleName = client.getMiddleName();
        birthday = client.getBirthday();
        passSeries = client.getPassSeries();
        passNumber = client.getPassNumber();
        fio = String.format("%s %s %s", surName, firstName, middleName);
    }


}
