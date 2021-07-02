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
public class RealtySquareConfiguration {

    // TODO: 27.06.2021 сделать проверку на непересечение площади

    private List<Square> square;

    /**
     * получить коэфициент по площади недвижимости
     * @param   squareParam
     * @return  коэфициент
     */
    public double getKoeff(double squareParam) {
        for (Square square: this.square) {
            // нижний порог, нет минимальной площади
            if (square.min == 0 && square.max >= squareParam) {
                return square.koeff;
            }
            // верхний порог, нет максимальной площади
            if (square.max == 0 && square.min <= squareParam) {
                return square.koeff;
            }
            // где-то в середине
            if (square.min > 0 && square.max > 0 &&
                square.min <= squareParam && square.max >= squareParam) {
                return square.koeff;
            }
        }

        throw new NotFoundKoeffException();

    }

    @NoArgsConstructor
    public static class Square {
        private double min;
        private double max;
        private double koeff;

        public void setMin(double min) {
            this.min = min;
        }

        public void setMax(double max) {
            this.max = max;
        }

        public void setKoeff(double koeff) {
            this.koeff = koeff;
        }
    }


}
