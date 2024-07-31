package nl.hu.inno.humc.monoliet.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.monoliet.data.MedewerkerRepository;
import nl.hu.inno.humc.monoliet.data.OnderzoekRepository;
import nl.hu.inno.humc.monoliet.domain.exception.OnderzoekNotFoundException;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import nl.hu.inno.humc.monoliet.domain.onderzoek.Onderzoek;
import nl.hu.inno.humc.monoliet.presentation.dto.MedewerkerEnOnderzoekDTO;
import nl.hu.inno.humc.monoliet.presentation.dto.OnderzoekDTO;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class OnderzoekService{

    private final OnderzoekRepository onderzoekRepository;
    private final MedewerkerRepository medewerkerRepository;

    public OnderzoekService(OnderzoekRepository onderzoekRepository, MedewerkerRepository medewerkerRepository) {
        this.onderzoekRepository = onderzoekRepository;
        this.medewerkerRepository = medewerkerRepository;
    }

    public OnderzoekDTO GetOnderzoekById(Long id) {
        Onderzoek onderzoek = onderzoekRepository.findById(id).orElseThrow(OnderzoekNotFoundException::new);
        return new OnderzoekDTO(
                onderzoek.getId(),
                onderzoek.getBudget(),
                onderzoek.getBegindatum(),
                onderzoek.getEinddatum(),
                onderzoek.getBeschrijving(),
                onderzoek.getOnderzoekers(),
                onderzoek.getOnderzoekStatus().toString()
        );
    }

    public OnderzoekDTO createOnderzoek(OnderzoekDTO onderzoekDTO) {
        Onderzoek onderzoek = new Onderzoek();
        onderzoek.setBudget(onderzoekDTO.budget());
        onderzoek.setPeriode(onderzoekDTO.beginDatum(), onderzoekDTO.eindDatum());
        onderzoekRepository.save(onderzoek);

        return new OnderzoekDTO(
                onderzoek.getId(),
                onderzoek.getBudget(),
                onderzoek.getBegindatum(),
                onderzoek.getEinddatum(),
                onderzoek.getBeschrijving(),
                onderzoek.getOnderzoekers(),
                onderzoek.getOnderzoekStatus().toString()
        );
    }

    public String addResearcher(MedewerkerEnOnderzoekDTO medewerkerEnOnderzoekDTO) {
        Onderzoek onderzoek = onderzoekRepository.findById(medewerkerEnOnderzoekDTO.OId()).orElseThrow(OnderzoekNotFoundException::new);
        Medewerker medewerker = medewerkerRepository.findById(medewerkerEnOnderzoekDTO.MId()).orElseThrow(OnderzoekNotFoundException::new);
        boolean succes = onderzoek.AddResearcher(medewerker);
        onderzoekRepository.save(onderzoek);

        if (succes) {
            return "Medewerker '" + medewerker.getNaam().getVoornaam() + " " + medewerker.getNaam().getAchternaam() + "' is toegevoeg aan onderzoek '" + onderzoek.getBeschrijving() + "'";
        } else { return "Medewerker already assigned to this research"; }
    }

    public OnderzoekDTO changeOnderzoekState(Long id, String state) {
        Onderzoek onderzoek = onderzoekRepository.findById(id).orElseThrow(OnderzoekNotFoundException::new);
        onderzoek.changeOnderzoekStatus(state);

        return new OnderzoekDTO(
                onderzoek.getId(),
                onderzoek.getBudget(),
                onderzoek.getBegindatum(),
                onderzoek.getEinddatum(),
                onderzoek.getBeschrijving(),
                onderzoek.getOnderzoekers(),
                onderzoek.getOnderzoekStatus().toString()
        );
    }
}
