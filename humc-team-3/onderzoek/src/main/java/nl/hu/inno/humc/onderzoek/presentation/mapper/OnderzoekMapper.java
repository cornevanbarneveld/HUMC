package nl.hu.inno.humc.onderzoek.presentation.mapper;

import nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek.Middel;
import nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek.Onderzoek;
import nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek.OnderzoekBudget;
import nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek.OnderzoekPeriode;
import nl.hu.inno.humc.onderzoek.presentation.dto.OnderzoekDTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class OnderzoekMapper {

    public Onderzoek toOnderzoek(OnderzoekDTO onderzoekDTO) {
        OnderzoekPeriode periode = new OnderzoekPeriode(onderzoekDTO.beginDatum(), onderzoekDTO.eindDatum());
        OnderzoekBudget budget = new OnderzoekBudget(onderzoekDTO.budget());

        return new Onderzoek(
                onderzoekDTO.id(),
                budget,
                periode,
                onderzoekDTO.beschrijving(),
                onderzoekDTO.onderzoekers(),
                onderzoekDTO.status(),
                onderzoekDTO.locatie(),
                onderzoekDTO.middelList()
        );
    }

    public OnderzoekDTO toOnderzoekDTO(Onderzoek onderzoek) {
        HashMap<String, Integer> map = new HashMap();
        for (Middel middel : onderzoek.getMiddelList()) {
            map.put(middel.getName(), middel.getHoeveelheid());
        }

        return new OnderzoekDTO(
                onderzoek.getId(),
                onderzoek.getBudget(),
                onderzoek.getBegindatum(),
                onderzoek.getEinddatum(),
                onderzoek.getBeschrijving(),
                onderzoek.getOnderzoekers(),
                onderzoek.getLocatie(),
                String.valueOf(onderzoek.getOnderzoekStatus()),
                onderzoek.getBeschrijving(),
                map
        );
    }
}
