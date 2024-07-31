package nl.hu.inno.humc.monoliet.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.monoliet.data.MiddelRepository;
import nl.hu.inno.humc.monoliet.domain.middel.Middel;
import nl.hu.inno.humc.monoliet.domain.middel.MiddelId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MiddelService {

    @Autowired
    private MiddelRepository middelRepository;

    public void deleteMiddel(String middelId) {
        middelRepository.delete(getMiddelForId(middelId));
    }

    public Middel getMiddelForId(String middelId) {
        return middelRepository.findById(new MiddelId(middelId))
                .orElseThrow(() -> new RuntimeException("Middel not found"));
    }
}
