package nl.hu.inno.humc.onderzoek.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.onderzoek.api.MedewerkerApi;
import nl.hu.inno.humc.onderzoek.api.dto.MedewerkerDto;
import nl.hu.inno.humc.onderzoek.data.OnderzoekRepository;
import nl.hu.inno.humc.onderzoek.domain.exception.OnderzoekNotFoundException;
import nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek.MedewerkerId;
import nl.hu.inno.humc.onderzoek.domain.neo4j.onderzoek.Onderzoek;
import nl.hu.inno.humc.onderzoek.presentation.dto.MedewerkerEnOnderzoekDTO;
import nl.hu.inno.humc.onderzoek.presentation.dto.OnderzoekDTO;
import nl.hu.inno.humc.onderzoek.presentation.mapper.OnderzoekMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Transactional
@Service
public class OnderzoekService{

    private final OnderzoekRepository onderzoekRepository;
    private final MedewerkerApi medewerkerApi;
    private final OnderzoekMapper onderzoekMapper;

    public OnderzoekService(OnderzoekRepository onderzoekRepository, MedewerkerApi medewerkerApi, OnderzoekMapper onderzoekMapper) {
        this.onderzoekRepository = onderzoekRepository;
        this.medewerkerApi = medewerkerApi;
        this.onderzoekMapper = onderzoekMapper;
    }

    public OnderzoekDTO GetOnderzoekById(Long id) {
        Onderzoek onderzoek = onderzoekRepository.findById(id).orElseThrow(OnderzoekNotFoundException::new);
        return onderzoekMapper.toOnderzoekDTO(onderzoek);
    }

    public OnderzoekDTO createOnderzoek(OnderzoekDTO onderzoekDTO) {
        Onderzoek onderzoek = onderzoekMapper.toOnderzoek(onderzoekDTO);
        onderzoekRepository.save(onderzoek);

        return onderzoekMapper.toOnderzoekDTO(onderzoek);
    }

    public String addResearcher(MedewerkerEnOnderzoekDTO medewerkerEnOnderzoekDTO) {
        Onderzoek onderzoek = onderzoekRepository.findById(medewerkerEnOnderzoekDTO.OId()).orElseThrow(OnderzoekNotFoundException::new);
        ResponseEntity<MedewerkerDto> medewerker = medewerkerApi.getMedewerkerById(medewerkerEnOnderzoekDTO.MId());

        if (medewerker == null) {
            return "Medewerker not found";
        }

        boolean succes = onderzoek.AddResearcher(medewerker.getBody().id());
        onderzoekRepository.save(onderzoek);

        if (succes) {
            return "Medewerker '" + medewerker.getBody().voornaam() + " " + medewerker.getBody().achternaam() + "' is toegevoeg aan onderzoek '" + onderzoek.getId() + "'";
        } else { return "Medewerker already assigned to this research"; }
    }

    public OnderzoekDTO changeOnderzoekState(Long id, String state) {
        Onderzoek onderzoek = onderzoekRepository.findById(id).orElseThrow(OnderzoekNotFoundException::new);
        onderzoek.changeOnderzoekStatus(state);

        return onderzoekMapper.toOnderzoekDTO(onderzoek);
    }

    public ArrayList<MedewerkerDto> getResearchers(Long id) {
        ArrayList<MedewerkerDto> medewerkers = new ArrayList<MedewerkerDto>();
        Onderzoek onderzoek = onderzoekRepository.findById(id).orElseThrow(OnderzoekNotFoundException::new);
        for (MedewerkerId medId : onderzoek.getOnderzoekers()) {
            medewerkers.add(medewerkerApi.getMedewerkerById(medId.getId()).getBody());
        }

        return medewerkers;
    }

}
