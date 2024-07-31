package nl.hu.inno.humc.onderzoek.presentation;

import nl.hu.inno.humc.onderzoek.api.dto.MedewerkerDto;
import nl.hu.inno.humc.onderzoek.application.OnderzoekService;
import nl.hu.inno.humc.onderzoek.presentation.dto.*;
import nl.hu.inno.humc.onderzoek.producer.AccesRequestProducer;
import nl.hu.inno.humc.onderzoek.producer.StartOnderzoekProducer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/onderzoek")
public class OnderzoekController {

    private final OnderzoekService onderzoekService;
    private final AccesRequestProducer accesRequestProducer;
    private final StartOnderzoekProducer startOnderzoekProducerOnderzoek;

    public OnderzoekController(OnderzoekService onderzoekService, AccesRequestProducer accesRequestProducer, StartOnderzoekProducer startOnderzoekProducerOnderzoek) {
        this.onderzoekService = onderzoekService;
        this.accesRequestProducer = accesRequestProducer;
        this.startOnderzoekProducerOnderzoek = startOnderzoekProducerOnderzoek;
    }

    @GetMapping("/{id}")
    public OnderzoekDTO getOnderzoek(@PathVariable Long id) {
        return onderzoekService.GetOnderzoekById(id);
    }

    @PostMapping
    public OnderzoekDTO createOnderzoek(@RequestBody OnderzoekDTO onderzoekDTO) {
        return onderzoekService.createOnderzoek(onderzoekDTO);
    }

    @PostMapping("/concept")
    public OnderzoekDTO createConceptOnderzoek(@RequestBody OnderzoekDTO OnderzoekDTO) {
        accesRequestProducer.sendMessage(new AccesRequestDTO(OnderzoekDTO.bericht(), OnderzoekDTO.middelList(),
                                                OnderzoekDTO.beginDatum(), OnderzoekDTO.eindDatum()));
        startOnderzoekProducerOnderzoek.sendMessage(new RoosterOnderzoekDTO(OnderzoekDTO.locatie(), OnderzoekDTO.beginDatum(),
                                                            OnderzoekDTO.eindDatum(), OnderzoekDTO.beschrijving(), 1));
        return onderzoekService.createOnderzoek(OnderzoekDTO);
    }

    @PatchMapping("/researcher")
    public String addResearcher(@RequestBody MedewerkerEnOnderzoekDTO medewerkerEnOnderzoekDTO) {
        return onderzoekService.addResearcher(medewerkerEnOnderzoekDTO);
    }

    @GetMapping("/{id}/researchers")
    public ArrayList<MedewerkerDto> getResearchers(@PathVariable Long id) {
        return onderzoekService.getResearchers(id);
    }

    @PatchMapping("/state")
    public OnderzoekDTO changeOnderzoekState (@RequestBody StateChangeDTO stateChangeDTO) {
        return onderzoekService.changeOnderzoekState(stateChangeDTO.id(), stateChangeDTO.state());
    }
}
