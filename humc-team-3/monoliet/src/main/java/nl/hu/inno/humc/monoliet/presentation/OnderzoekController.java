package nl.hu.inno.humc.monoliet.presentation;

import nl.hu.inno.humc.monoliet.application.OnderzoekService;
import nl.hu.inno.humc.monoliet.presentation.dto.MedewerkerEnOnderzoekDTO;
import nl.hu.inno.humc.monoliet.presentation.dto.OnderzoekDTO;
import nl.hu.inno.humc.monoliet.presentation.dto.StateChangeDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/onderzoek")
public class OnderzoekController {

    private final OnderzoekService onderzoekService;

    public OnderzoekController(OnderzoekService onderzoekService) {
        this.onderzoekService = onderzoekService;
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
    public OnderzoekDTO createConceptOnderzoek() {
        return null;
    }

    @PatchMapping("/researcher")
    public String addResearcher(@RequestBody MedewerkerEnOnderzoekDTO medewerkerEnOnderzoekDTO) {
        return onderzoekService.addResearcher(medewerkerEnOnderzoekDTO);
    }

    @PatchMapping("/state")
    public OnderzoekDTO changeOnderzoekState (@RequestBody StateChangeDTO stateChangeDTO) {
        return onderzoekService.changeOnderzoekState(stateChangeDTO.id(), stateChangeDTO.state());
    }
}
