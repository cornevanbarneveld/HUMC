package nl.hu.inno.humc.ruimtebeheer.domain.neo4j.ruimte.locatie;

import nl.hu.inno.humc.ruimtebeheer.domain.exception.IncorrectPostcodeException;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.RelationshipId;

@Node
public class Postcode {

    @GeneratedValue
    @RelationshipId
    private Long id;
    private String postcode;

    public Postcode(String postcode) {
        if (isPostcodeCorrect(postcode)) {
            this.postcode = postcode;
        } else {
            throw new IncorrectPostcodeException("De postcode is incorrect, zorg ervoor dat het aan elkaar is geschreven.");
        }
    }

    protected Postcode() {
    }

    public boolean isPostcodeCorrect(String postcode) {
        return postcode.matches("^[0-9]{4}[a-zA-Z]{2}$");
    }

    public String getPostcode() {
        return this.postcode;
    }

    private void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
