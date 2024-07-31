package nl.hu.inno.humc.roostering.presentation;


import com.fasterxml.jackson.core.JsonProcessingException;
import nl.hu.inno.humc.roostering.domain.exception.*;
import nl.hu.inno.humc.roostering.application.RoosterService;
import nl.hu.inno.humc.roostering.presentation.dto.RoosterpuntDto;
import nl.hu.inno.humc.roostering.presentation.dto.RoosterpuntDtos;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/{roosterId}/roosterpunt")
public class RoosterPuntController {

    private final RoosterService roosterService;

    public RoosterPuntController(RoosterService roosterService) {
        this.roosterService = roosterService;
    }

    @PostMapping()
    public RoosterpuntDto createRoosterpunt(@PathVariable String roosterId, @RequestBody RoosterpuntDto roosterpuntDTO) throws NietBeschikbaarException {
        try {
            return this.roosterService.addRoosterpunt(
                    roosterId, roosterpuntDTO.beschrijving(), roosterpuntDTO.ruimteNummer(), roosterpuntDTO.organisatorId(), roosterpuntDTO.begintijd(), roosterpuntDTO.eindtijd());
        }catch (MedewerkerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (NietBeschikbaarException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/annuleer")
    public String annuleerRoosterpunt(@PathVariable String roosterId, @RequestBody RoosterpuntDto roosterpuntDTO){
        try {
            return this.roosterService.annuleerRoosterpunt(roosterId, roosterpuntDTO.organisatorId(), roosterpuntDTO.begintijd(), roosterpuntDTO.eindtijd());
        }catch (MedewerkerNotFoundException | RoosterNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (RoosterpuntNietGeannuleerdException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/addMiddel/{middelId}")
    public RoosterpuntDto addMiddelToRoosterpunt(@PathVariable String roosterId, @PathVariable Long middelId,
                                                 @RequestBody RoosterpuntDto roosterpuntDTO) throws NietBeschikbaarException, JsonProcessingException {
        try {
            return this.roosterService.addMiddel(
                    roosterId, middelId, roosterpuntDTO.organisatorId(), roosterpuntDTO.begintijd(), roosterpuntDTO.eindtijd());
        }catch (NietBeschikbaarException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/locatie/{ruimteNummer}")
    public RoosterpuntDto setLocatieRoosterpunt(@PathVariable String roosterId, @PathVariable Long ruimteNummer,
                                                @RequestBody RoosterpuntDto roosterpuntDTO) {
        try {
            return this.roosterService.addLocatie(
                    roosterId, ruimteNummer, roosterpuntDTO.organisatorId(), roosterpuntDTO.begintijd(), roosterpuntDTO.eindtijd());
        }catch (NietBeschikbaarException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PostMapping("/addMedewerker/{medewerkersNr}")
    public RoosterpuntDto addMedewerkerToRoosterpunt(@PathVariable String roosterId, @PathVariable Long medewerkersNr,
                                                     @RequestBody RoosterpuntDto roosterpuntDTO) {
        try {
            return this.roosterService.addBetrokkene(
                    roosterId, medewerkersNr, roosterpuntDTO.organisatorId(), roosterpuntDTO.begintijd(), roosterpuntDTO.eindtijd());
        }catch (MedewerkerNotFoundException | RoosterNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (NietBeschikbaarException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/ruimte/{ruimteNummer}")
    public List<RoosterpuntDto> getRoosterpuntenByLaboratoriumNaam(@PathVariable String roosterId, @PathVariable Long ruimteNummer) {
        try {
            return this.roosterService.getRoosterpuntenByLaboratorium(roosterId, ruimteNummer);
        }catch (MedewerkerNotFoundException | RoosterNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @GetMapping("/periode/{begindatum}/{einddatum}")
    public RoosterpuntDtos getRoosterpuntenByPeriode(@PathVariable String roosterId,
                                                          @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS") LocalDateTime begindatum,
                                                          @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS") LocalDateTime einddatum) {
        try {
            return this.roosterService.getRoosterpuntenByPeriode(roosterId, begindatum, einddatum);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }
    }
}
