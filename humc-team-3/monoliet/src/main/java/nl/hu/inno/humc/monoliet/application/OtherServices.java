package nl.hu.inno.humc.monoliet.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.monoliet.domain.Laboratorium;
import nl.hu.inno.humc.monoliet.domain.Rooster;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.medewerker.Medewerker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


public interface OtherServices{
    Medewerker getMedewerkerIfNotNull(Long id);
    Apparaat getApparaatIfNotNull(String naam);
    Laboratorium getLaboratoriumByName(String naam);
}
