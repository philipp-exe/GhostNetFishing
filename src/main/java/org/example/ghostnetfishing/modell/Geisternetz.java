package org.example.ghostnetfishing.modell;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "geisternetz")
public class Geisternetz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double breitengrad;
    @Column(nullable = false)
    private Double längengrad;

    @Enumerated(EnumType.STRING)
    @Column(name = "geschätzte_größe", nullable = false)
    private GeisternetzGröße geschätzteGröße;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GeisternetzStatus status = GeisternetzStatus.GEMELDET; // Start-Zustand beim Anlegen

    @Column(name = "gemeldet_am", nullable = false)
    private LocalDateTime gemeldetAm = LocalDateTime.now();

    @ManyToOne(optional = false)
    @JoinColumn(name = "gemeldet_von_id", nullable = false)
    private MeldendePerson gemeldetVon;

    // nur gesetzt, wenn sich schon jemand für die Bergung eingetragen hat
    @ManyToOne
    @JoinColumn(name = "bergung_durch_id")
    private BergendePerson bergungDurch;

    // darf nie anonym sein
    @ManyToOne
    @JoinColumn(name = "verschollen_gemeldet_von_id")
    private MeldendePerson verschollenGemeldetVon;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
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

    public GeisternetzStatus getStatus() {
        return status;
    }
    public void setStatus(GeisternetzStatus status) {
        this.status = status;
    }

    public LocalDateTime getGemeldetAm() {
        return gemeldetAm;
    }
    public void setGemeldetAm(LocalDateTime gemeldetAm) {
        this.gemeldetAm = gemeldetAm;
    }

    public MeldendePerson getGemeldetVon() {
        return gemeldetVon;
    }
    public void setGemeldetVon(MeldendePerson gemeldetVon) {
        this.gemeldetVon = gemeldetVon;
    }

    public BergendePerson getBergungDurch() {
        return bergungDurch;
    }
    public void setBergungDurch(BergendePerson bergungDurch) {
        this.bergungDurch = bergungDurch;
    }

    public MeldendePerson getVerschollenGemeldetVon() {
        return verschollenGemeldetVon;
    }
    public void setVerschollenGemeldetVon(MeldendePerson verschollenGemeldetVon) {
        this.verschollenGemeldetVon = verschollenGemeldetVon;
    }
}
