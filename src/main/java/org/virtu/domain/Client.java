package org.virtu.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @NotBlank
    @Column(name = "sur_name")
    private String surName;

    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @Column(name = "birthday")
    private LocalDate birthday;

    @Pattern(regexp = "^[0-9]{4}$")
    @Column(name = "pass_series")
    private String passSeries;

    @Pattern(regexp = "^[0-9]{6}$")
    @Column(name = "pass_number")
    private String passNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private Set<Doc> docs;
}
