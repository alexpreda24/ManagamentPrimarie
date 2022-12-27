package org.example;

import java.util.HashSet;
import java.util.Set;

public class FunctionarPublic <T,U>{
    private String nume;
    private String funtion;
    private Set<Cerere> cereri = new HashSet<>();
    public FunctionarPublic(String nume,String function) {
        this.nume = nume;
        this.funtion = function;
    }

    public String getNume() {
        return nume;
    }
    public <U> void adaugaCerere(U cerere) {
        if(cerere instanceof Cerere) cereri.add((Cerere) cerere);
    }
}
