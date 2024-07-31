package nl.hu.inno.humc.monoliet.domain.ruimte.locatie;

import jakarta.persistence.Embeddable;
import nl.hu.inno.humc.monoliet.domain.exception.IncorrectPostcodeException;

@Embeddable
public class Postcode {
    private String postcode;

    public Postcode(String postcode) {
        if (isPostcodeCorrect(postcode)) {
            this.postcode = postcode;
        } else {
            throw new IncorrectPostcodeException("De postcode is incorrect, zorg ervoor dat het aan elkaar is geschreven.");
        }
    }

    public Postcode() {
    }

    public boolean isPostcodeCorrect(String postcode) {
        return postcode.matches("^[0-9]{4}[a-zA-Z]{2}$");
    }

    public String getPostcode() {
        return this.postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
}
