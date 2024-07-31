package nl.hu.inno.humc.monoliet.presentation;

import nl.hu.inno.humc.monoliet.application.LaboratoriumService;
import nl.hu.inno.humc.monoliet.domain.Laboratorium;
import nl.hu.inno.humc.monoliet.presentation.dto.Laboratorium.BeheerderDto;
import nl.hu.inno.humc.monoliet.presentation.dto.Laboratorium.LaboratoriumDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/laboratorium")
public class LaboratiumController {


    @Autowired
    private LaboratoriumService laboratoriumService;

    @PostMapping
    public LaboratoriumDto createLaboratorium(@RequestBody LaboratoriumDto laboratoriumDto) {
        try {
            Laboratorium laboratorium = laboratoriumService.createLaboratorium(laboratoriumDto.postcode(), laboratoriumDto.straatnaam(), laboratoriumDto.huisnummer()
                    ,laboratoriumDto.ruimtenummer(), laboratoriumDto.halnummer(), laboratoriumDto.naam(), laboratoriumDto.type());
            return new LaboratoriumDto(laboratorium.getLocatie().getPostcode().getPostcode(), laboratorium.getLocatie().getStraatnaam(), laboratorium.getLocatie().getHuisnummer()
                    ,laboratorium.getLocatie().getRuimtenummer(), laboratorium.getLocatie().getHalnummer(), laboratorium.getNaam(), laboratorium.getType(), laboratorium.getBeheerder());

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping
    public LaboratoriumDto replaceBeheerder(@RequestBody BeheerderDto beheerderDto) {
        try {
            Laboratorium laboratorium = laboratoriumService.replaceBeheerder(beheerderDto.laboratoriumId(), beheerderDto.medewerkerId());
            return new LaboratoriumDto(laboratorium.getLocatie().getPostcode().getPostcode(), laboratorium.getLocatie().getStraatnaam(), laboratorium.getLocatie().getHuisnummer()
                    ,laboratorium.getLocatie().getRuimtenummer(), laboratorium.getLocatie().getHalnummer(), laboratorium.getNaam(), laboratorium.getType(), laboratorium.getBeheerder());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
