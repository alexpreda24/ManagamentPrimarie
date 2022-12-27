package org.example;

import java.util.*;

public class Elev extends Utilizator{
    private String nume;
    private String scoala;

    private List<Cerere> cereri = new LinkedList<>();
    private List<Cerere> cererifinalizate = new LinkedList<>();
    private int counter = 0;

    private Set<String> supportedcereri = new HashSet<>();
    Elev(String nume,String scoala){
        this.nume = nume;
        this.scoala = scoala;
    }
    public String getScoala(){
        return scoala;
    }
    public String getNume(){
        return nume;
    }
    public void setScoala(String scoala){
        this.scoala = scoala;
    }
    public void setNume(String nume){
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
    public Set<String> getSupportedcereri() {
        return supportedcereri;
    }

    public void addSupportedcereri() {
        supportedcereri.add("inlocuire buletin");
        supportedcereri.add("inlocuire carnet de elev");
    }
    public String toString(int i){
        Cerere cerere = cereri.get(i);
        return cerere.getDatestring() + " - Subsemnatul " + nume +
                ", elev la scoala " + scoala +
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
    public Cerere findCerere(String cerere) {
        for (Cerere cerere1 : cereri) {
            if (cerere1.getDatestring().equals(cerere)) {
                return cerere1;
            }
        }
        return null;
    }
}
