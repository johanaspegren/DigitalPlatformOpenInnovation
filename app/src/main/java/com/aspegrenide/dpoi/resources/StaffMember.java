package com.aspegrenide.dpoi.resources;

import java.util.ArrayList;

public class StaffMember {
    String name;
    String personNummer;
    String telefonNummer;
    int imgResource;
    ArrayList <Competence> competences;

    public StaffMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonNummer() {
        return personNummer;
    }

    public void setPersonNummer(String personNummer) {
        this.personNummer = personNummer;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }
}
