package org.example.ghostnetfishing.modell;

import javax.persistence.Table;
import javax.persistence.Entity;
import java.util.Objects;

// kann beim Melden anonym bleiben, aber nicht beim Verschollen-Melden
@Entity
@Table(name = "meldende_person")
public class MeldendePerson extends Person {
    private boolean anonym;
    public boolean isAnonym() {
        return anonym;
    }

    public void setAnonym(boolean anonym) {
        this.anonym = anonym;
    }
}
