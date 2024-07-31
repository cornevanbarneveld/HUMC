package nl.hu.inno.humc.roostering.presentation;

import nl.hu.inno.humc.roostering.application.MedewerkerService;
import nl.hu.inno.humc.roostering.presentation.dto.MedewerkerDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/medewerker")
public class MedewerkerController {
    private final MedewerkerService medewerkerService;

    public MedewerkerController(MedewerkerService medewerkerService) {
        this.medewerkerService = medewerkerService;
    }

    @PostMapping()
    public MedewerkerDto createMedewerker(@RequestBody MedewerkerDto medewerkerDto) {
        try {
            return this.medewerkerService.registreerMedewerker(
                    medewerkerDto.voornaam(), medewerkerDto.initialen(), medewerkerDto.achternaam(), medewerkerDto.woonplaats(), medewerkerDto.adres(), medewerkerDto.postcode(), medewerkerDto.email(), medewerkerDto.telefoonnummer(), medewerkerDto.functie()
            );
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/update/{id}/email")
    public MedewerkerDto updateMedewerkersEmail(@PathVariable Long id, @Validated @RequestBody MedewerkerDto medewerkerDto) {
        try {
            return this.medewerkerService.updateMedewerkersEmail(
                    id, medewerkerDto.email()
            );
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public MedewerkerDto getMedewerker(@PathVariable Long id) {
        try {
            return this.medewerkerService.getMedewerker(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
