package nl.hu.inno.humc.ruimtebeheer.domain.postgresql.ruimte.locatie;

import jakarta.persistence.Embeddable;
import nl.hu.inno.humc.ruimtebeheer.domain.exception.IncorrectPostcodeException;

import java.io.Serializable;

@Embeddable
public class Postcode implements Serializable {
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
