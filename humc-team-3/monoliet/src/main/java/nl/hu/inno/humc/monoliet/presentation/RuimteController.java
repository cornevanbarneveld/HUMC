package nl.hu.inno.humc.monoliet.presentation;

import nl.hu.inno.humc.monoliet.application.RuimteService;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.middel.Middel;
import nl.hu.inno.humc.monoliet.domain.ruimte.Ruimte;
import nl.hu.inno.humc.monoliet.presentation.dto.Apparaat.ApparaatDto;
import nl.hu.inno.humc.monoliet.presentation.dto.Apparaat.ApparaatIdRuimteIdDto;
import nl.hu.inno.humc.monoliet.presentation.dto.Apparaat.ApparaatRuimteDto;
import nl.hu.inno.humc.monoliet.presentation.dto.Middel.MiddelDto;
import nl.hu.inno.humc.monoliet.presentation.dto.Middel.MiddelIdRuimteIdDto;
import nl.hu.inno.humc.monoliet.presentation.dto.Middel.MiddelRuimteDto;
import nl.hu.inno.humc.monoliet.presentation.dto.RuimteDto;
import nl.hu.inno.humc.monoliet.presentation.mapper.ApparaatMapper;
import nl.hu.inno.humc.monoliet.presentation.mapper.MiddelMapper;
import nl.hu.inno.humc.monoliet.presentation.mapper.RuimteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/ruimte")
public class RuimteController {

    @Autowired
    private RuimteService ruimteService;

    @Autowired
    private RuimteMapper ruimteMapper;

    @Autowired
    private ApparaatMapper apparaatMapper;

    @Autowired
    private MiddelMapper middelMapper;

    @GetMapping
    public RuimteDto getRuimteInfo(@RequestParam Long id) {
        try {
            Ruimte ruimte = ruimteService.getRuimte(id);
            return ruimteMapper.toRuimteDto(ruimte);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/apparaat")
    public ApparaatDto addApparaat(@RequestBody ApparaatRuimteDto apparaatRuimteDto) {
        try {
            Apparaat apparaat = ruimteService.addApparaat(apparaatRuimteDto.naam(), apparaatRuimteDto.elektrisch(),apparaatRuimteDto.id(), apparaatRuimteDto.ruimteId());
            return apparaatMapper.toApparaatDto(apparaat);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/apparaat")
    public void deleteApparaat(@RequestBody ApparaatIdRuimteIdDto apparaatIdRuimteIdDto) {
        try {
            ruimteService.deleteApparaat(apparaatIdRuimteIdDto.apparaatId(), apparaatIdRuimteIdDto.ruimteId());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PostMapping("/middel")
    public MiddelDto addMiddel(@RequestBody MiddelRuimteDto middelRuimteDto) {
        try {
            Middel middel = ruimteService.addMiddel(middelRuimteDto.naam(),middelRuimteDto.beschrijving(),middelRuimteDto.typegevaar(),
                    middelRuimteDto.middelId(), middelRuimteDto.ruimteId(), middelRuimteDto.registratieDatum(), middelRuimteDto.houdbaarheidsDatum());
            return middelMapper.toMiddelDto(middel);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/middel")
    public void deleteMiddel(@RequestBody MiddelIdRuimteIdDto middelIdRuimteIdDto) {
        try {
            ruimteService.deleteMiddel(middelIdRuimteIdDto.middelId(), middelIdRuimteIdDto.ruimteId());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
