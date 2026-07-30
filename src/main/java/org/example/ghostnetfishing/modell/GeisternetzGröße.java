package org.example.ghostnetfishing.modell;

public enum GeisternetzGröße {
    KLEIN("Klein (bis 5m)"),
    MITTEL("Mittel (5-20m)"),
    GROSS("Groß (über 20m)"); // evtl. später noch feiner unterteilen

    private final String bezeichnung;
    GeisternetzGröße(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }
}
