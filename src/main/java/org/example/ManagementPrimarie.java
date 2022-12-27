package org.example;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;


public class ManagementPrimarie {
    private static Set<Angajator> angajatori = new HashSet<>();
    private static Set<Elev> elevi = new HashSet<>();
    private static Set<Pensionar> pensionars = new HashSet<>();
    private static Set<Persoana> persoane = new HashSet<>();
    private static Set<EntitateJuridica> entitatiJuridice = new HashSet<>();

    private static Set<FunctionarPublic> functionariPublici = new HashSet<>();

    private static Birou b1 = new Birou();
    private static Birou b2 = new Birou();
    private static Birou b3 = new Birou();
    private static Birou b4 = new Birou();
    private static Birou b5 = new Birou();

    public static Angajator findAngajator(Set<Angajator> angajatori, String nume) {
        for (Angajator angajator : angajatori) {
            if (angajator.getNume().equals(nume)) {
                return angajator;
            }
        }
        return null;
    }

    public static Elev findElev(Set<Elev> elevi, String nume) {
        for (Elev elev : elevi) {
            if (elev.getNume().equals(nume)) {
                return elev;
            }
        }
        return null;
    }

    public static Pensionar findPensionar(Set<Pensionar> pensionars, String nume) {
        for (Pensionar pensionar : pensionars) {
            if (pensionar.getNume().equals(nume)) {
                return pensionar;
            }
        }
        return null;
    }

    public static Persoana findPersoana(Set<Persoana> persoane, String nume) {
        for (Persoana persoana : persoane) {
            if (persoana.getNume().equals(nume)) {
                return persoana;
            }
        }
        return null;
    }

    public static EntitateJuridica findEntitateJuridica(Set<EntitateJuridica> entitatiJuridice, String nume) {
        for (EntitateJuridica entitateJuridica : entitatiJuridice) {
            if (entitateJuridica.getNume().equals(nume)) {
                return entitateJuridica;
            }
        }
        return null;
    }

    public static FunctionarPublic findFunctionarPublic(Set<FunctionarPublic> functionariPublici, String nume) {
        for (FunctionarPublic functionarPublic : functionariPublici) {
            if (functionarPublic.getNume().equals(nume)) {
                return functionarPublic;
            }
        }
        return null;
    }


    public static void main(String[] args) throws IOException, ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy " +
                "HH:mm:ss", Locale.ENGLISH);
        String antetInput = "src/main/resources/input/";
//        System.out.println("Fisierul de intrare este: " + antetInput + args[0]);
        String antetOutput = "src/main/resources/output/";
        String antetRef = "src/main/resources/references/";
        String file = antetInput + args[0];
        String fileOutput = antetOutput + args[0];
        List<String> filesOutput = new ArrayList<>();
        String fileRef = antetRef + args[0];
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileOutput));
            List<BufferedWriter> writers = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                try {

                    String[] parts = line.split("; ");
                    if (parts[0].equals("adauga_utilizator")) {
                        if (parts[1].equals("angajat")) {
                            Angajator angajator = new Angajator(parts[2], parts[3]);
                            angajator.addSupportedcereri();
                            angajatori.add(angajator);
                        } else if (parts[1].equals("elev")) {
                            Elev elev = new Elev(parts[2], parts[3]);
                            elev.addSupportedcereri();
                            elevi.add(elev);
                        } else if (parts[1].equals("pensionar")) {
                            Pensionar pensionar = new Pensionar(parts[2]);
                            pensionar.addSupportedcereri();
                            pensionars.add(pensionar);
                        } else if (parts[1].equals("persoana")) {
                            Persoana persoana = new Persoana(parts[2]);
                            persoana.addSupportedcereri();
                            persoane.add(persoana);
                        } else if (parts[1].equals("entitate juridica")) {
                            EntitateJuridica entitateJuridica = new EntitateJuridica(parts[2], parts[3]);
                            entitateJuridica.addSupportedcereri();
                            entitatiJuridice.add(entitateJuridica);
                        }
                    }
                    if (parts[0].equals("cerere_noua")) {
                        int ok = 1;
                        if (findAngajator(angajatori, parts[1]) != null) {
                                ok = 0;
                                Angajator angajator = findAngajator(angajatori, parts[1]);
                               Set<String> sp = angajator.getSupportedcereri();
                               if (sp.contains(parts[2])) {
                                   int priority = Integer.parseInt(parts[parts.length - 1]);
                                   Date date = formatter.parse(parts[3]);
                                   String output = formatter.format(date);
                                   Cerere cerere = new Cerere(priority, parts[2], date,output);
                                   angajator.addCerere(cerere);
                                   angajator.sortbydate();
                               }
                               else {
                                       writer.write("Utilizatorul de tip angajat "
                                               + "nu poate inainta o cerere de tip " + parts[2]);
                                       writer.newLine();
                               }
                        }
                        if(ok == 1 && findElev(elevi, parts[1]) != null) {
                            ok = 0;
                            Elev elev = findElev(elevi, parts[1]);
                            Set<String> sp = elev.getSupportedcereri();
                            if (sp.contains(parts[2])) {
                                int priority = Integer.parseInt(parts[parts.length - 1]);
                                Date date = formatter.parse(parts[3]);
                                String output = formatter.format(date);
                                Cerere cerere = new Cerere(priority, parts[2], date,output);
                                elev.addCerere(cerere);
                                elev.sortbydate();
                            }
                            else {
                                    writer.write("Utilizatorul de tip elev "
                                            + "nu poate inainta o cerere de tip " + parts[2]);
                                    writer.newLine();
                            }
                        }
                        if(ok == 1 && findPensionar(pensionars, parts[1]) != null) {
                            ok = 0;
                            Pensionar pensionar = findPensionar(pensionars, parts[1]);
                            Set<String> sp = pensionar.getSupportedcereri();
                            if (sp.contains(parts[2])) {
                                int priority = Integer.parseInt(parts[parts.length - 1]);
                                Date date = formatter.parse(parts[3]);
                                String output = formatter.format(date);
                                Cerere cerere = new Cerere(priority, parts[2], date,output);
                                pensionar.addCerere(cerere);
                                pensionar.sortbydate();
                            }
                            else {
                                    writer.write("Utilizatorul de tip pensionar "
                                            + "nu poate inainta o cerere de tip " + parts[2]);
                                    writer.newLine();
                            }
                        }
                        if(ok == 1 && findPersoana(persoane, parts[1]) != null) {
                            ok = 0;
                            Persoana persoana = findPersoana(persoane, parts[1]);
                            System.out.println("Persoana " + persoana.getNume());
                            Set<String> sp = persoana.getSupportedcereri();
                            if (sp.contains(parts[2])) {
                                int priority = Integer.parseInt(parts[parts.length - 1]);
                                Date date = formatter.parse(parts[3]);
                                String output = formatter.format(date);
                                Cerere cerere = new Cerere(priority, parts[2], date,output);
                                persoana.addCerere(cerere);
                                persoana.sortbydate();
                            }
                            else {
                                    writer.write("Utilizatorul de tip persoana "
                                            + "nu poate inainta o cerere de tip " + parts[2]);
                                    writer.newLine();
                            }
                        }
                        if(ok == 1 && findEntitateJuridica(entitatiJuridice, parts[1]) != null) {
                            EntitateJuridica entitateJuridica = findEntitateJuridica(entitatiJuridice, parts[1]);
                            Set<String> sp = entitateJuridica.getSupportedcereri();
                            if (sp.contains(parts[2])) {
                                int priority = Integer.parseInt(parts[parts.length - 1]);
                                Date date = formatter.parse(parts[3]);
                                String output = formatter.format(date);
                                Cerere cerere = new Cerere(priority, parts[2], date,output);
                                entitateJuridica.addCerere(cerere);
                                entitateJuridica.sortbydate();
                            }
                            else {
                                    writer.write("Utilizatorul de tip entitate juridica "
                                            + "nu poate inainta o cerere de tip " + parts[2]);
                                    writer.newLine();
                            }
                        }
                    }
                    if(parts[0].equals("afiseaza_cereri_in_asteptare")){
                        try {
                            if (findAngajator(angajatori, parts[1]) != null) {
                                Angajator angajator = findAngajator(angajatori, parts[1]);
                                List<Cerere> cereri = angajator.getCereri();
                                writer.write(angajator.getNume() + " - cereri in asteptare:");
                                writer.newLine();
                                for (int i = 0;i < cereri.size();i++) {
                                    writer.write(angajator.toString(i));
                                    writer.newLine();
                                }
                            }
                            if(findElev(elevi, parts[1]) != null) {
                                Elev elev = findElev(elevi, parts[1]);
                                writer.write(elev.getNume() + " - cereri in asteptare:");
                                writer.newLine();
                                List<Cerere> cereri = elev.getCereri();
                                for(int i = 0;i < cereri.size();i++) {
                                    writer.write(elev.toString(i));
                                    writer.newLine();
                                }
                            }
                            if(findPensionar(pensionars, parts[1]) != null) {
                                Pensionar pensionar = findPensionar(pensionars, parts[1]);
                                writer.write(pensionar.getNume() +  " - cereri in asteptare:");
                                writer.newLine();
                                List<Cerere> cereri = pensionar.getCereri();
                                for(int i = 0;i < cereri.size();i++) {
                                    writer.write(pensionar.toString(i));
                                    writer.newLine();
                                }
                            }
                            if(findPersoana(persoane, parts[1]) != null) {
                                Persoana persoana = findPersoana(persoane, parts[1]);
                                writer.write(persoana.getNume() +  " - cereri in asteptare:");
                                writer.newLine();
                                List<Cerere> cereri = persoana.getCereri();
                                for(int i = 0;i < cereri.size();i++) {
                                    writer.write(persoana.toString(i));
                                    writer.newLine();
                                }
                            }
                            if(findEntitateJuridica(entitatiJuridice, parts[1]) != null) {
                                EntitateJuridica entitateJuridica = findEntitateJuridica(entitatiJuridice, parts[1]);
                                writer.write(entitateJuridica.getNume() +  " - cereri in asteptare:");
                                writer.newLine();
                                List<Cerere> cereri = entitateJuridica.getCereri();
                                for(int i = 0;i < cereri.size();i++) {
                                    writer.write(entitateJuridica.toString(i));
                                    writer.newLine();
                                }
                            }
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if(parts[0].equals("afiseaza_cereri_finalizate")){
                        if (findAngajator(angajatori, parts[1]) != null) {
                            Angajator angajator = findAngajator(angajatori, parts[1]);
                            List<Cerere> cereri = angajator.getCererifinalizate();
                            writer.write(angajator.getNume() + " - cereri in finalizate:");
                            writer.newLine();
                            for (int i = 0;i < cereri.size();i++) {
                                String s = cereri.get(i).getMessage();
                                writer.write(s.substring(4, s.length()));
                                writer.newLine();
                            }
                        }
                        if(findElev(elevi, parts[1]) != null) {
                            Elev elev = findElev(elevi, parts[1]);
                            writer.write(elev.getNume() + " - cereri in finalizate:");
                            writer.newLine();
                            List<Cerere> cereri = elev.getCererifinalizate();
                            for(int i = 0;i < cereri.size();i++) {
                                String s = cereri.get(i).getMessage();
                                writer.write(s.substring(4, s.length()));
                                writer.newLine();
                            }
                        }
                        if(findPensionar(pensionars, parts[1]) != null) {
                            Pensionar pensionar = findPensionar(pensionars, parts[1]);
                            writer.write(pensionar.getNume() +  " - cereri in finalizate:");
                            writer.newLine();
                            List<Cerere> cereri = pensionar.getCererifinalizate();
                            for(int i = 0;i < cereri.size();i++) {
                                String s = cereri.get(i).getMessage();
                                writer.write(s.substring(4));
                                writer.newLine();
                            }
                        }
                        if(findPersoana(persoane, parts[1]) != null) {
                            Persoana persoana = findPersoana(persoane, parts[1]);
                            writer.write(persoana.getNume() +  " - cereri in finalizate:");
                            writer.newLine();
                            List<Cerere> cereri = persoana.getCererifinalizate();
                            for(int i = 0;i < cereri.size();i++) {
                                String s = cereri.get(i).getMessage();
                                writer.write(s.substring(4));
                                writer.newLine();
                            }
                        }
                        if(findEntitateJuridica(entitatiJuridice, parts[1]) != null) {
                            EntitateJuridica entitateJuridica = findEntitateJuridica(entitatiJuridice, parts[1]);
                            writer.write(entitateJuridica.getNume() +  " - cereri in finalizate:");
                            writer.newLine();
                            List<Cerere> cereri = entitateJuridica.getCererifinalizate();
                            for(int i = 0;i < cereri.size();i++) {
                                String s = cereri.get(i).getMessage();
                                writer.write(s.substring(4));
                                writer.newLine();
                            }
                        }
                    }
                    if(parts[0].equals("retrage_cerere")){
                        if(findAngajator(angajatori, parts[1]) != null) {
                            Angajator angajator = findAngajator(angajatori, parts[1]);
                            Cerere c = angajator.findCerere(parts[2]);
                            b1.getCereri().remove(c);
                            angajator.stergeCerere(parts[2]);

                        }
                        if(findElev(elevi, parts[1]) != null) {
                            Elev elev = findElev(elevi, parts[1]);
                            Cerere c = elev.findCerere(parts[2]);
                            b2.getCereri().remove(c);
                            elev.stergeCerere(parts[2]);
                        }
                        if(findPensionar(pensionars, parts[1]) != null) {
                            Pensionar pensionar = findPensionar(pensionars, parts[1]);
                            Cerere c = pensionar.findCerere(parts[2]);
                            b3.getCereri().remove(c);
                            pensionar.stergeCerere(parts[2]);
                        }
                        if(findPersoana(persoane, parts[1]) != null) {
                            Persoana persoana = findPersoana(persoane, parts[1]);
                            Cerere c = persoana.findCerere(parts[2]);
                            b4.getCereri().remove(c);
                            List<Cerere> cereri = b4.getCereri();
                            for(Cerere c2 : cereri) {
                                System.out.println(c2.getMessage());
                            }
                            System.out.println();
                            persoana.stergeCerere(parts[2]);
                        }
                        if(findEntitateJuridica(entitatiJuridice, parts[1]) != null) {
                            EntitateJuridica entitateJuridica = findEntitateJuridica(entitatiJuridice, parts[1]);
                            Cerere c = entitateJuridica.findCerere(parts[2]);
                            b5.getCereri().remove(c);
                            entitateJuridica.stergeCerere(parts[2]);
                        }
                    }
                    if(parts[0].equals("afiseaza_cereri")){
                        writer.write(parts[1] + " - cereri in birou:");
                        writer.newLine();
                        if(parts[1].equals("angajat")){
                            for(Angajator angajat : angajatori) {
                                for(int i = 0;i < angajat.getCereri().size();i++) {
                                    angajat.getCereri().get(i).setMessage(angajat.toString(i));
                                    angajat.getCereri().get(i).setNameCerere(angajat.getNume());
                                }
                            }
                            for(Angajator angajat : angajatori) {
                                b1.verificaUtilizator(angajat);
                            }

                            List<Cerere> cereri = b1.getCereri();
                            for(int i = 0;i < cereri.size();i++) {
                                Cerere cerere = cereri.get(i);
                                writer.write(cerere.getMessage());
                                writer.newLine();
                            }
                        }
                        if(parts[1].equals("elev")){
                            for(Elev elev : elevi) {
                                for(int i = 0;i < elev.getCereri().size();i++) {
                                    elev.getCereri().get(i).setMessage(elev.toString(i));
                                    elev.getCereri().get(i).setNameCerere(elev.getNume());
                                }
                            }
                            for(Elev elev : elevi) {
                                b2.verificaUtilizator(elev);
                            }

                            List<Cerere> cereri = b2.getCereri();
                            for(int i = 0;i < cereri.size();i++) {
                                Cerere cerere = cereri.get(i);
                                writer.write(cerere.getMessage());
                                writer.newLine();
                            }
                        }
                        if(parts[1].equals("pensionar")){
                            for(Pensionar pensionar : pensionars) {
                                for(int i = 0;i < pensionar.getCereri().size();i++) {
                                    pensionar.getCereri().get(i).setMessage(pensionar.toString(i));
                                    pensionar.getCereri().get(i).setNameCerere(pensionar.getNume());
                                }
                            }
                            for(Pensionar pensionar : pensionars) {
                                b3.verificaUtilizator(pensionar);
                            }

                            List<Cerere> cereri = b3.getCereri();
                            for(int i = 0;i < cereri.size();i++) {
                                Cerere cerere = cereri.get(i);
                                writer.write(cerere.getMessage());
                                writer.newLine();
                            }
                        }
                        if(parts[1].equals("persoana")){
                            for(Persoana persoana : persoane) {
                                for(int i = 0;i < persoana.getCereri().size();i++) {
                                    persoana.getCereri().get(i).setMessage(persoana.toString(i));
                                    persoana.getCereri().get(i).setNameCerere(persoana.getNume());
                                }
                            }
                            for(Persoana persoana : persoane) {
                                b4.verificaUtilizator(persoana);
                            }

                            List<Cerere> cereri = b4.getCereri();
                            for(int i = 0;i < cereri.size();i++) {
                                Cerere cerere = cereri.get(i);
                                writer.write(cerere.getMessage());
                                writer.newLine();
                            }
                        }
                        if(parts[1].equals("entitate juridica")){
                            for(EntitateJuridica ent : entitatiJuridice) {
                                for(int i = 0;i < ent.getCereri().size();i++) {
                                    ent.getCereri().get(i).setMessage(ent.toString(i));
                                    ent.getCereri().get(i).setNameCerere(ent.getNume());
                                }
                            }
                            for(EntitateJuridica ent : entitatiJuridice) {

                                b5.verificaUtilizator(ent);
                            }

                            List<Cerere> cereri = b5.getCereri();
                            for(int i = 0;i < cereri.size();i++) {
                                Cerere cerere = cereri.get(i);
                                writer.write(cerere.getMessage());
                                writer.newLine();
                            }
                        }

                    }
                    if (parts[0].equals("adauga_functionar")){
                         FunctionarPublic fp = new FunctionarPublic(parts[2],parts[1]);
                         System.out.println(parts[2]);
                            functionariPublici.add(fp);
                    }
                    if(parts[0].equals("rezolva_cerere")) {
                        FunctionarPublic fp = findFunctionarPublic(functionariPublici, parts[2]);
                        if (fp != null) {
                            if (parts[1].equals("angajat")) {
                                List<Cerere> cereri = b1.getCereri();
                                Cerere cerere = cereri.get(0);
                                String files =  antetOutput + "functionar_" + parts[2] + ".txt";
                                BufferedWriter w;
                                if(!filesOutput.contains(files)) {
                                    filesOutput.add(files);
                                    System.out.println(files);
                                    w = new BufferedWriter(new FileWriter(files));
                                    writers.add(w);
                                }
                                else {
                                    String f = filesOutput.get(filesOutput.indexOf(files));
                                    w = writers.get(filesOutput.indexOf(f));
                                }
                                for(Angajator angajat : angajatori) {
                                    for(int i = 0;i < angajat.getCereri().size();i++) {
                                        if(angajat.getCereri().get(i).equals(cerere)) {
                                            angajat.getCereri().remove(i);
                                            angajat.adaugaCerereFinalizata(cerere);
                                        }
                                    }
                                }
                                w.write(cerere.getDatestring() + " - " + cerere.getNameCerere());
                                w.newLine();
                                b1.getCereri().remove(cerere);
                            }
                            if (parts[1].equals("elev")) {
                                List<Cerere> cereri = b2.getCereri();
                                Cerere cerere = cereri.get(0);
                                String files =  antetOutput + "functionar_" + parts[2] + ".txt";
                                BufferedWriter w;
                                if(!filesOutput.contains(files)) {
                                    filesOutput.add(files);
                                    System.out.println(files);
                                    w = new BufferedWriter(new FileWriter(files));
                                    writers.add(w);
                                }
                                else {
                                    String f = filesOutput.get(filesOutput.indexOf(files));
                                    w = writers.get(filesOutput.indexOf(f));
                                }
                                for(Elev elev : elevi) {
                                    for(int i = 0;i < elev.getCereri().size();i++) {
                                        if(elev.getCereri().get(i).equals(cerere)) {

                                            elev.getCereri().remove(i);
                                            elev.adaugaCerereFinalizata(cerere);
                                        }
                                    }
                                }
                                w.write(cerere.getDatestring() + " - " + cerere.getNameCerere());
                                w.newLine();
                                b2.getCereri().remove(cerere);
                            }
                            if (parts[1].equals("persoana")) {
                                List<Cerere> cereri = b4.getCereri();
                                Cerere cerere = cereri.get(0);
                                String files =  antetOutput + "functionar_" + parts[2] + ".txt";
                                BufferedWriter w;
                                if(!filesOutput.contains(files)) {
                                    filesOutput.add(files);
                                    System.out.println(files);
                                    w = new BufferedWriter(new FileWriter(files));
                                    writers.add(w);
                                }
                                else {
                                    String f = filesOutput.get(filesOutput.indexOf(files));
                                    w = writers.get(filesOutput.indexOf(f));
                                }
                                for(Persoana pers : persoane) {
                                    for(int i = 0;i < pers.getCereri().size();i++) {
                                        if(pers.getCereri().get(i).equals(cerere)) {
                                            pers.getCereri().remove(i);
                                            pers.adaugaCerereFinalizata(cerere);
                                        }
                                    }


                                }
                                w.write(cerere.getDatestring() + " - " + cerere.getNameCerere());
                                w.newLine();
                                b4.getCereri().remove(cerere);
                            }
                            if (parts[1].equals("pensionar")) {
                                List<Cerere> cereri = b3.getCereri();
                                Cerere cerere = cereri.get(0);
                                String files =  antetOutput + "functionar_" + parts[2] + ".txt";
                                BufferedWriter w;
                                if(!filesOutput.contains(files)) {
                                    filesOutput.add(files);
                                    System.out.println(files);
                                    w = new BufferedWriter(new FileWriter(files));
                                    writers.add(w);
                                }
                                else {
                                    String f = filesOutput.get(filesOutput.indexOf(files));
                                    w = writers.get(filesOutput.indexOf(f));
                                }
                                for(Pensionar pensionar : pensionars) {
                                    for(int i = 0;i < pensionar.getCereri().size();i++) {
                                        if(pensionar.getCereri().get(i).equals(cerere)) {
                                            pensionar.getCereri().remove(i);
                                            pensionar.adaugaCerereFinalizata(cerere);
                                        }
                                    }


                                }
                                w.write(cerere.getDatestring() + " - " + cerere.getNameCerere());
                                w.newLine();
                                b3.getCereri().remove(cerere);
                            }
                            if (parts[1].equals("entitate juridica")) {
                                System.out.println("aici am ajuns");
                                List<Cerere> cereri = b5.getCereri();
                                Cerere cerere = cereri.get(0);
                                String files =  antetOutput + "functionar_" + parts[2] + ".txt";
                                BufferedWriter w;
                                if(!filesOutput.contains(files)) {
                                    filesOutput.add(files);
                                    System.out.println(files);
                                    w = new BufferedWriter(new FileWriter(files));
                                    writers.add(w);
                                }
                                else {
                                    String f = filesOutput.get(filesOutput.indexOf(files));
                                    w = writers.get(filesOutput.indexOf(f));
                                }
                                for(EntitateJuridica ent : entitatiJuridice) {
                                    for(int i = 0;i < ent.getCereri().size();i++) {
                                        if(ent.getCereri().get(i).equals(cerere)) {
                                            ent.getCereri().remove(i);
                                            ent.adaugaCerereFinalizata(cerere);
                                        }
                                    }
                                }
                                w.write(cerere.getDatestring() + " - " + cerere.getNameCerere());
                                w.newLine();
                                b5.getCereri().remove(cerere);
                            }
                        }
                    }
                } catch (NullPointerException e) {
//                    System.out.println("Nu exista linie");
                    e.printStackTrace();
                }
            }
            angajatori.clear();
            elevi.clear();
            pensionars.clear();
            persoane.clear();
            entitatiJuridice.clear();
            b1.getCereri().clear();
            b2.getCereri().clear();
            b3.getCereri().clear();
            b4.getCereri().clear();
            b5.getCereri().clear();
            writer.close();
            for(BufferedWriter w : writers) {
                w.close();
            }
        } catch (IOException e) {
//            System.out.println("Fisierul nu a fost gasit");
            e.printStackTrace();
        }

    }
}
