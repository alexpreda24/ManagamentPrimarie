package org.example;

import java.util.*;

public class Birou <T extends Utilizator & Comparable> {
    private String specializator;
    private Set<T> utilizatori = new HashSet<>();
    private List<Cerere> cereri = new LinkedList<>();
    public <T> void adaugaUtilizator(Set<T> utilizatori, T utilizator){
        utilizatori.add((T) utilizator);
    }
    public int compareTo(Object p1, Object p2) {
        if (p1 instanceof Cerere && p2 instanceof Cerere) {
            Cerere produs1 = (Cerere) p1;
            Cerere produs2 = (Cerere) p2;
            if (produs1.getPriority() == produs2.getPriority()) {
                return produs1.compareTo(produs2);
            }
            return produs2.getPriority() - produs1.getPriority();
        }
        return 0;
    }

    public void setCereri(List<Cerere> cereri) {
        this.cereri = cereri;
    }
    public <T> void verificaUtilizator(T p){
        if(p instanceof Utilizator){
            if(p instanceof Angajator){
                Angajator angajator = (Angajator) p;
//                System.out.println("DA");
                for(Cerere cerere : angajator.getCereri()) {
                    if(!alreadyExist(cerere))cereri.add(cerere);
                }
            }
            else if(p instanceof Elev){
                Elev elev = (Elev) p;
                for(Cerere cerere : elev.getCereri())
                    if(!alreadyExist(cerere))cereri.add(cerere);

            }
            else if(p instanceof Pensionar){
                Pensionar pensionar = (Pensionar) p;
                for(Cerere cerere : pensionar.getCereri())
                    if(!alreadyExist(cerere))cereri.add(cerere);
            }
            else if(p instanceof EntitateJuridica){
                EntitateJuridica entitateJuridica = (EntitateJuridica) p;
                for(Cerere cerere : entitateJuridica.getCereri())
                    if(!alreadyExist(cerere))cereri.add(cerere);
            }
            else if(p instanceof Persoana){
                Persoana persoana = (Persoana) p;
                for(Cerere cerere : persoana.getCereri())
                    if(!alreadyExist(cerere))cereri.add(cerere);
            }
//            Collections.sort(cereri, this::compareTo);
            Collections.sort(cereri, new Comparator<Cerere>() {
                @Override
                public int compare(Cerere o1, Cerere o2) {
                    return compareTo(o1,o2);
                }
            });
        }
    }
    public List<Cerere> getCereri() {
        return cereri;
    }
    public boolean alreadyExist(Cerere cerere){
        for(Cerere cerere1 : cereri){
            if(cerere1.equals(cerere)) return true;
        }
        return false;
    }
    public Set<T> getUtilizatori() {
        return utilizatori;
    }

    public void setUtilizatori(Set<T> utilizatori) {
        this.utilizatori = utilizatori;
    }
}
