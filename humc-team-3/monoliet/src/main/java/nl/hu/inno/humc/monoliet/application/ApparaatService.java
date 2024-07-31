package nl.hu.inno.humc.monoliet.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.monoliet.data.ApparaatRepository;
import nl.hu.inno.humc.monoliet.domain.apparaat.Apparaat;
import nl.hu.inno.humc.monoliet.domain.apparaat.ApparaatId;
import nl.hu.inno.humc.monoliet.domain.exception.ApparaatNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ApparaatService {

    @Autowired
    private ApparaatRepository apparaatRepository;

    public Apparaat getApparaatIfNotNull(String naam){
        return this.apparaatRepository.findByNaam(naam)
                .orElseThrow(ApparaatNotFoundException::new);
    }

    public void deleteApparaat(String apparaatId) {
        apparaatRepository.delete(getApparaatForId(apparaatId));
    }

    public Apparaat getApparaatForId(String apparaatid) {
        //Is dit de correcte manier?
        return apparaatRepository.findById(new ApparaatId(apparaatid))
                .orElseThrow(() -> new RuntimeException("Apparaat not found"));
    }




}
