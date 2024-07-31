package nl.hu.inno.humc.roostering.presentation;

import nl.hu.inno.humc.roostering.application.RoosterService;
import nl.hu.inno.humc.roostering.presentation.dto.RoosterDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public RoosterDto getRoosterById(@PathVariable String id) {
        try {
            return this.roosterService.getRooster(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @GetMapping()
    public RoosterDto getRooster() {
        try {
            return this.roosterService.getRooster();
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
