package nl.hu.inno.humc.monoliet.presentation;

import nl.hu.inno.humc.monoliet.application.RoosterService;
import nl.hu.inno.humc.monoliet.domain.exception.MedewerkerNotFoundException;
import nl.hu.inno.humc.monoliet.presentation.dto.MedewerkerDto;
import nl.hu.inno.humc.monoliet.presentation.dto.PeriodedTO;
import nl.hu.inno.humc.monoliet.presentation.dto.RoosterDto;
import nl.hu.inno.humc.monoliet.presentation.dto.RoosterpuntDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/rooster")
public class RoosterController {
    private final RoosterService roosterService;

    public RoosterController(RoosterService roosterService) {
        this.roosterService = roosterService;
    }

    @PostMapping()
    public RoosterDto createRooster() {
        try {
            return this.roosterService.createRooster();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public RoosterDto getRoosterById(@PathVariable Long id) {
        try {
            return this.roosterService.getRooster(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
