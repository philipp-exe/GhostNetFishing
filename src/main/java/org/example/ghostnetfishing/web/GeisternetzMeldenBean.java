package org.example.ghostnetfishing.web;

import org.example.ghostnetfishing.modell.GeisternetzGröße;
import org.example.ghostnetfishing.modell.Geisternetz;
import org.example.ghostnetfishing.modell.MeldendePerson;
import org.example.ghostnetfishing.verwaltung.GeisternetzVerwaltung;

import javax.faces.application.FacesMessage;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

// Backing Bean für geisternetzMelden.xhtml
@Named
@RequestScoped
public class GeisternetzMeldenBean implements Serializable {

    @Inject
    private GeisternetzVerwaltung geisternetzVerwaltung;

    private Double breitengrad;
    private Double längengrad;
    private GeisternetzGröße geschätzteGröße = GeisternetzGröße.MITTEL;
    private boolean anonym;
    private String melderName;
    private String melderTelefonnummer;
    private String hinweistext; // war mal für eine Zusatznotiz im Formular gedacht, aktuell ungenutzt

    public String melden() {
        MeldendePerson meldendePerson = new MeldendePerson();
        meldendePerson.setAnonym(anonym);
        // wenn anonym, bleiben Name/Telefonnummer einfach null
        if (!anonym) {
            meldendePerson.setName(melderName);
            meldendePerson.setTelefonnummer(melderTelefonnummer);
        }
        Geisternetz geisternetz = new Geisternetz();
        geisternetz.setBreitengrad(breitengrad);
        geisternetz.setLängengrad(längengrad);
        geisternetz.setGeschätzteGröße(geschätzteGröße);
        geisternetz.setGemeldetVon(meldendePerson);
        geisternetzVerwaltung.melden(geisternetz);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Geisternetz wurde gemeldet.", null));
        return "offeneNetze.xhtml";
    }

    public GeisternetzGröße[] getVerfügbareGrößen() {
        return GeisternetzGröße.values();
    }
    public Double getBreitengrad() {
        return breitengrad;
    }
    public void setBreitengrad(Double breitengrad) {
        this.breitengrad = breitengrad;
    }

    public Double getLängengrad() {
        return längengrad;
    }
    public void setLängengrad(Double längengrad) {
        this.längengrad = längengrad;
    }

    public GeisternetzGröße getGeschätzteGröße() {
        return geschätzteGröße;
    }
    public void setGeschätzteGröße(GeisternetzGröße geschätzteGröße) {
        this.geschätzteGröße = geschätzteGröße;
    }

    public boolean isAnonym() {
        return anonym;
    }
    public void setAnonym(boolean anonym) {
        this.anonym = anonym;
    }

    public String getMelderName() {
        return melderName;
    }
    public void setMelderName(String melderName) {
        this.melderName = melderName;
    }

    public String getMelderTelefonnummer() {
        return melderTelefonnummer;
    }
    public void setMelderTelefonnummer(String melderTelefonnummer) {
        this.melderTelefonnummer = melderTelefonnummer;
    }

    public String getHinweistext() {
        return hinweistext;
    }
    public void setHinweistext(String hinweistext) {
        this.hinweistext = hinweistext;
    }
}
