package nl.hu.inno.humc.roostering;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import nl.hu.inno.humc.roostering.domain.Rooster;
import nl.hu.inno.humc.roostering.domain.exception.NietBeschikbaarException;
import nl.hu.inno.humc.roostering.domain.medewerker.*;
import nl.hu.inno.humc.roostering.domain.roosterpunt.Roosterpunt;
import nl.hu.inno.humc.roostering.domain.roosterpunt.RoosterpuntId;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.time.LocalDateTime;

public class MongoTest {
    public static void main(String[] args) throws NietBeschikbaarException {
        MongoClient client = MongoClients.create("mongodb://root:monoliet@localhost");
        MongoOperations mongoOps = new MongoTemplate(client, "Ruben");

        mongoOps.dropCollection("rooster");
        mongoOps.dropCollection("roosterpunt");

        Rooster r = new Rooster();
        Naam naam = new Naam("Ruben", "R", "van Rooijen");
        AdresGegevens adresGegevens = new AdresGegevens("Bunschoten", "iets", "1111AA");
        Email email = new Email("rubenvanrooijen23@icloud.com");
        Telefoonnummer telefoonnummer = new Telefoonnummer("0611111111");
        Medewerker medewerker = new Medewerker(naam, adresGegevens, email, telefoonnummer, "arts");
        RoosterpuntId roosterpuntId = new RoosterpuntId(medewerker, LocalDateTime.now().plusMinutes(2L), LocalDateTime.now().plusHours(1L));
        Roosterpunt roosterpunt = r.addRoosterpunt(roosterpuntId, "in mijn eentje een schets maken van een onderzoek", 87L);
        r.setRoosterpuntRuimte(99L, roosterpunt);

        mongoOps.save(r);
        mongoOps.save(roosterpunt);
    }
}
