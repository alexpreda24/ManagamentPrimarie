package org.example;

import java.util.Date;

public class Cerere implements Comparable<Cerere> {
    private int priority;
    private String name;
    private String message;
    private Date date;

    private String datestring;

    private String userName;

    public Cerere(int priority,String name,Date date,String datestring) {
        this.priority = priority;
        this.name = name;
        this.date = date;
        this.datestring = datestring;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {

        this.date = date;
    }
    public int compareTo(Cerere cerere) {
            return this.date.compareTo(cerere.getDate());
    }
    public int getPriority() {
        return priority;
    }

    public String getDatestring() {
        return datestring;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = this.priority + " - " + message;
    }
    public void setNameCerere(String name) {
        this.userName = name;
    }
    public String getNameCerere() {
        return userName;
    }
}
