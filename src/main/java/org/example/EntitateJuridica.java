package org.example;

import java.util.*;

public class EntitateJuridica extends Utilizator {
    private String nume;
    private String rep;

    private List<Cerere> cereri = new LinkedList<>();
    private List<Cerere> cererifinalizate = new LinkedList<>();
    private int counter = 0;

    private Set<String> supportedcereri = new HashSet<>();

    EntitateJuridica(String nume, String rep) {
        this.nume = nume;
        this.rep = rep;
    }

    public String getNume() {

        return nume;
    }

    public void setNume(String nume) {

        this.nume = nume;
    }
    public Cerere findCerere(String cerere) {
        for (Cerere cerere1 : cereri) {
            if (cerere1.getDatestring().equals(cerere)) {
                return cerere1;
            }
        }
        return null;
    }
    public String getRep() {

        return rep;
    }

    public void setRep(String rep) {

        this.rep = rep;
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
    public Set<String> getSupportedcereri() {

        return supportedcereri;
    }

    public void addSupportedcereri() {
        supportedcereri.add("reinnoire autorizatie");
        supportedcereri.add("creare act constitutiv");
    }
    public String toString(int i){
        Cerere cerere = cereri.get(i);
        return cerere.getDatestring() + " - Subsemnatul " + rep +
                ", reprezentant legal al companiei " + nume +
                ", va rog sa-mi aprobati urmatoarea solicitare: " +
                cerere.getName();
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

