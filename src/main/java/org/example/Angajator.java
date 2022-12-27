package org.example;

import java.util.*;

public class Angajator extends Utilizator  {
    private String nume;
    private String compania;
    private List<Cerere> cereri = new LinkedList<>();
    private List<Cerere> cererifinalizate = new LinkedList<>();
    private int counter = 0;
    private Set<String> supportedcereri = new HashSet<>();
    Angajator(String nume, String compania) {
        this.nume = nume;
        this.compania = compania;
    }
    public String getCompania() {
        return compania;
    }

    public void setCompania(String compania) {
        this.compania = compania;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public List<Cerere> getCereri() {
        return cereri;
    }

    public void addCerere(Cerere cerere) {
        cereri.add(cerere);

    }
    public void stergeCerere(String cerere) {
        for (Cerere cerere1 : cereri) {
            if (cerere1.getDatestring().equals(cerere)) {
                cereri.remove(cerere1);
                break;
            }
        }
        cereri.remove(cerere);
    }
    public Cerere findCerere(String cerere) {
        for (Cerere cerere1 : cereri) {
            if (cerere1.getDatestring().equals(cerere)) {
                return cerere1;
            }
        }
        return null;
    }
    public Set<String> getSupportedcereri() {
        return supportedcereri;
    }

    public void addSupportedcereri() {
        supportedcereri.add("inlocuire buletin");
        supportedcereri.add("inlocuire carnet de sofer");
        supportedcereri.add("inregistrare venit salarial");
    }
    public String toString(int i) {
        Cerere cerere = cereri.get(i);
        return cerere.getDatestring() + " - Subsemnatul " + nume +
                ", angajat la compania " + compania + ", va rog sa-mi aprobati " +
                "urmatoarea solicitare: " + cerere.getName();
    }
    public void sortbydate() {
        Collections.sort(cereri);
    }

    public List<Cerere> getCererifinalizate() {
        return cererifinalizate;
    }
    public void adaugaCerereFinalizata(Cerere cerere) {
        cererifinalizate.add(cerere);
    }
}

