package org.example.ghostnetfishing.dao;

import org.example.ghostnetfishing.modell.BergendePerson;
import org.example.ghostnetfishing.modell.Geisternetz;
import org.example.ghostnetfishing.modell.GeisternetzStatus;
import org.example.ghostnetfishing.modell.MeldendePerson;

import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

// bündelt die Datenbankzugriffe für Geisternetze
// keeps one EntityManager open for the whole app lifetime, siehe Kap. 4.2 im Bericht
public class GeisternetzDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public GeisternetzDAO() {
        emf = Persistence.createEntityManagerFactory("ghostNetPU");
        em = emf.createEntityManager();
    }

    public List<Geisternetz> findeAlle() {
        TypedQuery<Geisternetz> query = em.createQuery(
                "SELECT g FROM Geisternetz g ORDER BY g.gemeldetAm DESC", Geisternetz.class);
        return query.getResultList();
    }

    public List<Geisternetz> findeOffeneNetze() {
        TypedQuery<Geisternetz> query = em.createQuery(
                "SELECT g FROM Geisternetz g WHERE g.status = :gemeldet OR g.status = :bevorstehend ORDER BY g.gemeldetAm DESC",
                Geisternetz.class);
        query.setParameter("gemeldet", GeisternetzStatus.GEMELDET);
        query.setParameter("bevorstehend", GeisternetzStatus.BERGUNG_BEVORSTEHEND);
        return query.getResultList();
    }

    public void speichern(Geisternetz netz) {
        em.getTransaction().begin();
        em.persist(netz.getGemeldetVon());
        em.persist(netz);
        em.getTransaction().commit();
    }

    public void fürBergungEintragen(Long netzId, BergendePerson bergendePerson) {
        em.getTransaction().begin();
        em.persist(bergendePerson);
        Geisternetz netz = em.find(Geisternetz.class, netzId);
        netz.setBergungDurch(bergendePerson);
        netz.setStatus(GeisternetzStatus.BERGUNG_BEVORSTEHEND);
        em.getTransaction().commit();
    }
    // speichern() macht das gleiche Pattern (begin/persist/commit), ließe sich zusammenfassen

    public void alsGeborgenMelden(Long netzId){
        em.getTransaction().begin();
        Geisternetz n = em.find(Geisternetz.class, netzId);
        n.setStatus(GeisternetzStatus.GEBORGEN);
        em.getTransaction().commit();
    }

    // TODO evtl noch prüfen ob das Netz überhaupt noch GEMELDET oder BERGUNG_BEVORSTEHEND ist
    public void alsVerschollenMelden(Long netzId, MeldendePerson meldendePerson) {
        em.getTransaction().begin();
        em.persist(meldendePerson);
        Geisternetz netz = em.find(Geisternetz.class, netzId);
        netz.setVerschollenGemeldetVon(meldendePerson);
        netz.setStatus(GeisternetzStatus.VERSCHOLLEN);
        em.getTransaction().commit();
    }
}
