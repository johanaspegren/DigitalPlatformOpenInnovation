package com.aspegrenide.dpoi.resources;

import java.util.ArrayList;
/*
Kund och StaffMember skulle kunna vara subklasser till en gemensam superklass
 */

public class Kund {
    String name;
    String personNummer;
    ArrayList <Insats> insatser;

    public Kund(String name) {
        this.name = name;
    }

    public Kund(String personNummer, String name) {
        this.personNummer = personNummer;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setInsatser(ArrayList<Insats> insatser) {
        this.insatser = insatser;
    }

    public ArrayList <Insats> getInsatser() {
        return insatser;
    }
}
