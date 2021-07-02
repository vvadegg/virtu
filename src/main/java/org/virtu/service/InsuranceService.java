package org.virtu.service;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.virtu.config.RealtySquareConfiguration;
import org.virtu.config.RealtyTypeConfiguration;
import org.virtu.config.RealtyYearConfiguration;
import org.virtu.dto.InsuranceCalcDTO;
import org.virtu.dto.InsuranceCalcResultDTO;
import org.virtu.dto.RealtyDTO;
import org.virtu.exception.InsuranceCalculationException;
import org.virtu.exception.NotFoundKoeffException;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@ConfigurationProperties(prefix = "realty")
public class InsuranceService {

    private List<RealtyDTO> realtys = new ArrayList<>();

    @Autowired
    private RealtyTypeConfiguration realtyTypeConfiguration;

    @Autowired
    private RealtyYearConfiguration realtyYearConfiguration;

    @Autowired
    private RealtySquareConfiguration realtySquareConfiguration;

    @PostConstruct
    public void init() {
        for(RealtyDTO current : this.realtys) {
            String s = current.getName();
        }
    }


    public InsuranceCalcResultDTO calculation(InsuranceCalcDTO dto) {

        InsuranceCalcResultDTO resDto = new InsuranceCalcResultDTO();

        double koeffType = realtyTypeConfiguration.getKoeff(dto.getRealtyType());

        if (koeffType == 0) {
            throw new InsuranceCalculationException("Koeff type not found");
        }

        double koeffYear;
        try {
             koeffYear = realtyYearConfiguration.getKoeff(dto.getYearBuilding());
        } catch (NotFoundKoeffException e) {
            throw new InsuranceCalculationException("Koeff year not found");
        }

        double koeffSquare;
        try {
            koeffSquare = realtySquareConfiguration.getKoeff(dto.getSquare());
        } catch (NotFoundKoeffException e) {
            throw new InsuranceCalculationException("Koeff square not found");
        }

        // Страховая премия = (Страховая сумма / кол-во дней) * Коэф.ТН * Коэф.ГП * Коэф.Пл

        if (dto.getSum() <= 0) {
            throw new InsuranceCalculationException("Sum less than zero");
        }

        long days = Duration.between(dto.getDateStart().atStartOfDay(), dto.getDateEnd().atStartOfDay()).toDays();

        double bonus = (dto.getSum() / days) * koeffType * koeffYear * koeffSquare;

        resDto.setCalcDate(LocalDate.now());
        resDto.setBonus(bonus);

        return resDto;

    }


}
