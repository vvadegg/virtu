package org.virtu.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.virtu.exception.NotFoundKoeffException;

import java.util.List;
import java.util.Objects;

@ConfigurationProperties(prefix = "realty")
@Configuration
@NoArgsConstructor
@Data
public class RealtyYearConfiguration {

    // TODO: 27.06.2021 сделать проверку на непересечение годов постройки

    private List<YearBuilt> yearBuilt;

    /**
     * получить коэфициент по году постройки недвижимости
     * @param   year
     * @return  коэфициент
     */
    public double getKoeff(Integer year) {
        for (YearBuilt yearBuilt: this.yearBuilt) {
            // нижний порог, нет минимального года
            if (Objects.isNull(yearBuilt.min) && yearBuilt.max >= year) {
                return yearBuilt.koeff;
            }
            // верхний порог, нет максимального года
            if (Objects.isNull(yearBuilt.max) && yearBuilt.min <= year) {
                return yearBuilt.koeff;
            }
            // где-то в середине
            if (Objects.nonNull(yearBuilt.min) && Objects.nonNull(yearBuilt.max) &&
                yearBuilt.min <= year && yearBuilt.max >= year) {
                return yearBuilt.koeff;
            }
        }

        throw new NotFoundKoeffException();

    }

    @NoArgsConstructor
    public static class YearBuilt {
        private Integer min;
        private Integer max;
        private double koeff;

        public void setMin(Integer min) {
            this.min = min;
        }

        public void setMax(Integer max) {
            this.max = max;
        }

        public void setKoeff(double koeff) {
            this.koeff = koeff;
        }
    }


}
