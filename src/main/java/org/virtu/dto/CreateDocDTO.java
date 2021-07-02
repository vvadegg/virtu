package org.virtu.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateDocDTO {

    private int clientId;

    private LocalDate dateStart;

    private LocalDate dateEnd;
}
