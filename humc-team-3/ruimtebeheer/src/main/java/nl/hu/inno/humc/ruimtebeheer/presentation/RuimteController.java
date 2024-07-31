package nl.hu.inno.humc.ruimtebeheer.presentation;

import nl.hu.inno.humc.ruimtebeheer.application.RuimteService;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.Middel;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.Ruimte;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.IdDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.MiddelDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.MiddelIdRuimteIdDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.MiddelRuimteDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.Middel.VerplaatsMiddelDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.dto.ruimte.RuimteDto;
import nl.hu.inno.humc.ruimtebeheer.presentation.mapper.MiddelMapper;
import nl.hu.inno.humc.ruimtebeheer.presentation.mapper.RuimteMapper;
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

    @PutMapping("/middel/verplaats")
    public RuimteDto verplaatsMiddel(@RequestBody VerplaatsMiddelDto verplaatsMiddelDto) {
        try {
            return ruimteMapper.toRuimteDto(ruimteService.verplaatsMiddel(verplaatsMiddelDto.oudeRuimteId(), verplaatsMiddelDto.nieuweRuimteId(), verplaatsMiddelDto.middelId()));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/middel/afwezig")
    public MiddelDto registreerMiddelAfwezig(@RequestBody IdDto idDto) {
        try {
            return middelMapper.toMiddelDto(ruimteService.registreerdMiddelAfwezig(idDto.id()));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
