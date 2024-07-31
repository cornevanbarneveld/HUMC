package nl.hu.inno.humc.ruimtebeheer.application;

import jakarta.transaction.Transactional;
import nl.hu.inno.humc.ruimtebeheer.data.MiddelRepo;
import nl.hu.inno.humc.ruimtebeheer.domain.neo4j.middel.Middel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MiddelService {

    @Autowired
    private MiddelRepo middelRepository;

    public void deleteMiddel(Long middelId) {
        middelRepository.delete(getMiddelForId(middelId));
    }

    public Middel getMiddelForId(Long middelId) {
        return middelRepository.findById(middelId)
                .orElseThrow(() -> new RuntimeException("Middel not found"));
    }

    public List<Middel> getMiddelen() {
        return middelRepository.findAll();
    }

}
