package org.example.ghostnetfishing.verwaltung;

import org.example.ghostnetfishing.dao.GeisternetzDAO;
import org.example.ghostnetfishing.modell.Geisternetz;
import org.example.ghostnetfishing.modell.BergendePerson;
import org.example.ghostnetfishing.modell.MeldendePerson;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.List;
import java.util.Objects;

// hält die DAO, ähnlich wie die Shop-Klasse im Kursskript
@Named
@ApplicationScoped
public class GeisternetzVerwaltung {

    private GeisternetzDAO geisternetzDAO = new GeisternetzDAO();

    public List<Geisternetz> getOffeneNetze() {
        return geisternetzDAO.findeOffeneNetze();
    }

    public void melden(Geisternetz netz) {
        geisternetzDAO.speichern(netz);
    }

    public void fürBergungEintragen(Long netzId, BergendePerson bergendePerson) {
        geisternetzDAO.fürBergungEintragen(netzId, bergendePerson);
    }
    public void alsGeborgenMelden(Long netzId){
        geisternetzDAO.alsGeborgenMelden(netzId);
    }

    // ruft nur das DAO auf, die eigentliche Prüfung (nicht anonym) passiert in der Bean
    public void alsVerschollenMelden(Long netzId, MeldendePerson meldendePerson) {
        geisternetzDAO.alsVerschollenMelden(netzId, meldendePerson);
    }

}
