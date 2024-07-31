package nl.hu.inno.humc.monoliet.presentation;

import nl.hu.inno.humc.monoliet.application.RoosterService;
import nl.hu.inno.humc.monoliet.domain.exception.ApparaatNotFoundException;
import nl.hu.inno.humc.monoliet.domain.exception.MedewerkerNotFoundException;
import nl.hu.inno.humc.monoliet.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.monoliet.presentation.dto.RoosterpuntDTO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/roosterpunt")
public class RoosterPuntController {

    private final RoosterService roosterService;

    public RoosterPuntController(RoosterService roosterService) {
        this.roosterService = roosterService;
    }

    @PostMapping()
    public RoosterpuntDTO createRoosterpunt(@RequestBody RoosterpuntDTO roosterpuntDTO) {
        try {
            return this.roosterService.addRoosterpunt(
                    1L, roosterpuntDTO.organisatorId(), roosterpuntDTO.begintijd(), roosterpuntDTO.eindtijd());
        }catch (MedewerkerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (NietBeschikbaarException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    //NOG NIET GETEST, WANT NOG GEEN APPARATEN.
    @PostMapping("/{roosterId}/{id}/addApparaat/{naam}")
    public RoosterpuntDTO addApparaatToRoosterpunt(@PathVariable Long roosterId, @PathVariable Long id, @PathVariable String naam) {
        try {
            return this.roosterService.addApparaat(roosterId, id, naam);
        }catch (ApparaatNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (NietBeschikbaarException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    //NOG NIET GETEST, WANT NOG GEEN LABORATORIUMS.
    @PostMapping("/{roosterId}/{id}/addLocatie/{naam}")
    public RoosterpuntDTO addLocatieToRoosterpunt(@PathVariable Long roosterId, @PathVariable Long id, @PathVariable String naam) {
        try {
            return this.roosterService.addLocatie(roosterId, id, naam);
        }catch (NietBeschikbaarException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/{roosterId}/{id}/addMedewerker/{medewerkersNr}")
    public RoosterpuntDTO addMedewerkerToRoosterpunt(@PathVariable Long roosterId, @PathVariable Long id, @PathVariable Long medewerkersNr) {
        try {
            return this.roosterService.addBetrokkene(roosterId, id, medewerkersNr);
        }catch (MedewerkerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (NietBeschikbaarException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/{roosterId}/periode/{begindatum}/{einddatum}")
    public List<RoosterpuntDTO> getRoosterpuntenByPeriode(@PathVariable Long roosterId,
                                                                @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS") LocalDateTime begindatum,
                                                                @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS") LocalDateTime einddatum) {
        try {
            return this.roosterService.getRoosterpuntenByPeriode(roosterId, begindatum, einddatum);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }


}
