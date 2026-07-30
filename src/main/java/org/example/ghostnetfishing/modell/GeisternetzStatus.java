package org.example.ghostnetfishing.modell;

public enum GeisternetzStatus {
    GEMELDET("Gemeldet"),
    BERGUNG_BEVORSTEHEND("Bergung bevorstehend"),
    GEBORGEN("Geborgen"),
    VERSCHOLLEN("Verschollen"); // Reihenfolge hier ist nicht der Lebenszyklus, nur Deklaration

    private final String bezeichnung;
    GeisternetzStatus(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }
}
