package nl.hu.inno.humc.monoliet.application;

import nl.hu.inno.humc.monoliet.data.InventarisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventarisService {

    @Autowired
    private InventarisRepository inventarisRepository;

    public void saveInventaris() {

    }


}
