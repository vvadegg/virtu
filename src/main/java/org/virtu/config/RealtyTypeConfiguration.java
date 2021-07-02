package org.virtu.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ConfigurationProperties(prefix = "realty")
@Configuration
@Data
@NoArgsConstructor
public class RealtyTypeConfiguration {

    private List<TypeKoeff> typeKoeff;

    private Map<String, Double> map = new HashMap<>();

    @PostConstruct
    public void init(){
        map = typeKoeff.stream().collect(Collectors.toMap(x -> x.name, x -> x.koeff));
    }

    /**
     * получить коэфициент по наименованию недвижимости
     * @param   name
     * @return  коэфициент
     */
    public double getKoeff(String name) {
        return map.get(name);
    }

    @Data
    @NoArgsConstructor
    public static class TypeKoeff {
        private String name;
        private double koeff;
    }


}
