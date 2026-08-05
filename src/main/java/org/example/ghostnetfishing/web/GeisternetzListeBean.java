package org.example.ghostnetfishing.web;

import org.example.ghostnetfishing.modell.Geisternetz;
import org.example.ghostnetfishing.modell.BergendePerson;
import org.example.ghostnetfishing.modell.MeldendePerson;
import org.example.ghostnetfishing.verwaltung.GeisternetzVerwaltung;

import javax.faces.view.ViewScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

// Backing Bean für offeneNetze.xhtml
// welches Netz gerade gemeint ist, wird per f:setPropertyActionListener in ausgewählteNetzId gesetzt
@Named
@ViewScoped
public class GeisternetzListeBean implements Serializable {

    @Inject
    private GeisternetzVerwaltung geisternetzVerwaltung;

    private Long ausgewählteNetzId;
    private String eigenerName;
    private String eigeneTelefonnummer;

    public List<Geisternetz> getOffeneNetze() {
        return geisternetzVerwaltung.getOffeneNetze();
    }

    public void fürBergungEintragen() {
        if (istLeer(eigenerName) || istLeer(eigeneTelefonnummer)) {
            nachrichtHinzufügen(FacesMessage.SEVERITY_ERROR, "Bitte Name und Telefonnummer angeben, um eine Bergung einzutragen.");
            return;
        }
        BergendePerson bp = new BergendePerson();
        bp.setName(eigenerName);
        bp.setTelefonnummer(eigeneTelefonnummer);
        geisternetzVerwaltung.fürBergungEintragen(ausgewählteNetzId, bp);
        nachrichtHinzufügen(FacesMessage.SEVERITY_INFO, "Du bist jetzt für die Bergung dieses Netzes eingetragen.");
    }

    public void alsGeborgenMelden() {
        geisternetzVerwaltung.alsGeborgenMelden(ausgewählteNetzId);
        nachrichtHinzufügen(FacesMessage.SEVERITY_INFO, "Netz wurde als geborgen markiert.");
    }

    public void alsVerschollenMelden() {
        if (eigenerName == null || eigenerName.trim().isEmpty() || istLeer(eigeneTelefonnummer)) {
            nachrichtHinzufügen(FacesMessage.SEVERITY_ERROR, "Eine Verschollen-Meldung kann nicht anonym erfolgen - bitte Name und Telefonnummer angeben.");
            return;
        }
        MeldendePerson meldendePerson = new MeldendePerson();
        meldendePerson.setAnonym(false);
        meldendePerson.setName(eigenerName);
        meldendePerson.setTelefonnummer(eigeneTelefonnummer);
        geisternetzVerwaltung.alsVerschollenMelden(ausgewählteNetzId, meldendePerson);
        nachrichtHinzufügen(FacesMessage.SEVERITY_WARN, "Netz wurde als verschollen markiert.");
    }

    private boolean istLeer(String wert) {
        return wert == null || wert.trim().isEmpty();
    }

    private void nachrichtHinzufügen(FacesMessage.Severity schweregrad, String text) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(schweregrad, text, null));
    }
    public Long getAusgewählteNetzId() {
        return ausgewählteNetzId;
    }
    public void setAusgewählteNetzId(Long ausgewählteNetzId) {
        this.ausgewählteNetzId = ausgewählteNetzId;
    }

    public String getEigenerName() {
        return eigenerName;
    }
    public void setEigenerName(String eigenerName) {
        this.eigenerName = eigenerName;
    }

    public String getEigeneTelefonnummer() {
        return eigeneTelefonnummer;
    }
    public void setEigeneTelefonnummer(String eigeneTelefonnummer) {
        this.eigeneTelefonnummer = eigeneTelefonnummer;
    }
}
