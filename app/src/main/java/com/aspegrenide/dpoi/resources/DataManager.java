package com.aspegrenide.dpoi.resources;

import com.aspegrenide.dpoi.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DataManager {

    ArrayList<Insats> insatser = new ArrayList<>();
    ArrayList<PlannedInsats> plannedInsatser = new ArrayList<>();
    ArrayList<Kund> kunder = new ArrayList<>();
    ArrayList<StaffMember> staffMembers = new ArrayList<>();

    public ArrayList<Kund> getKunder(String careCenter) {
        return kunder;
    }

    public ArrayList<StaffMember> getStaffMembers(String careCenter) {
        return staffMembers;
    }

    public ArrayList<Insats> getInsatser() {
        return insatser;
    }

    // PlannedInsatser
    public ArrayList<PlannedInsats> getPlaneradeInsatser(String careCenter, Date date) {
        return plannedInsatser;
    }

    public ArrayList<PlannedInsats> getPlaneradeInsatser(Kund kund, Date date) {
        return plannedInsatser;
    }

    public void initPlaneradeInsatser() throws ParseException {
        planeraInsats(kunder.get(0), staffMembers.get(0), insatser.get(0));
        planeraInsats(kunder.get(0), staffMembers.get(0), insatser.get(1));
        planeraInsats(kunder.get(0), staffMembers.get(1), insatser.get(2));
        planeraInsats(kunder.get(0), staffMembers.get(0), insatser.get(3), formatDate("14:50"), formatDate("15:00"));

        planeraInsats(kunder.get(1), staffMembers.get(1), insatser.get(0));
        planeraInsats(kunder.get(1), staffMembers.get(1), insatser.get(1));
        planeraInsats(kunder.get(1), staffMembers.get(2), insatser.get(2));
        planeraInsats(kunder.get(1), staffMembers.get(1), insatser.get(3), formatDate("14:55"), formatDate("15:05"));

        planeraInsats(kunder.get(2), staffMembers.get(2), insatser.get(0));
        planeraInsats(kunder.get(2), staffMembers.get(2), insatser.get(1));
        planeraInsats(kunder.get(2), staffMembers.get(0), insatser.get(2));
        planeraInsats(kunder.get(2), staffMembers.get(1), insatser.get(3), formatDate("15:00"), formatDate("15:05"));
    }

    public void planeraInsats(Kund kund, StaffMember staffMember, Insats insats) {
        PlannedInsats pli = new PlannedInsats(insats, kund, staffMember);
        plannedInsatser.add(pli);
    }

    public void planeraInsats(Kund kund, StaffMember staffMember, Insats insats, Date start, Date stop) {
        PlannedInsats pli = new PlannedInsats(insats, kund, staffMember, start, stop);
        plannedInsatser.add(pli);
    }

    public void initInsatser() throws ParseException {
        insatser.add(new Insats("Frukost", formatDate("08:00"), formatDate("08:30")));
        insatser.add(new Insats("Dusch", formatDate("08:45"), formatDate("09:00")));
        insatser.add(new Insats("Lunch", formatDate("12:00"), formatDate("12:30")));
        insatser.add(new Insats("Överlämning", formatDate("15:01"), formatDate("15:05")));
        insatser.add(new Insats("Promenad", formatDate("15:15"), formatDate("16:00")));
    }

    public void initKunder() {
        Kund k = new Kund("121212-1234", "Anna Andersson");
        k.setInsatser(getInsatser());
        kunder.add(k);

        k = new Kund("212121-1234", "Bertil Bertilsson");
        k.setInsatser(getInsatser());
        kunder.add(k);

        k = new Kund("313131-1234", "Ceasar Ceasarsson");
        k.setInsatser(getInsatser());
        kunder.add(k);
    }

    public void initStaffMember() {
        StaffMember s = new StaffMember("Pia Petersson");
        s.setImgResource(R.drawable.face_f_1_380_500);
        s.setTelefonNummer("0701 111111");
        staffMembers.add(s);

        s = new StaffMember("Zadine Persson");
        s.setImgResource(R.drawable.face_f_2_380_500);
        s.setTelefonNummer("0702 222222");
        staffMembers.add(s);

        s = new StaffMember("Amina Persson");
        s.setImgResource(R.drawable.face_f_3_380_500);
        s.setTelefonNummer("0703 333333");
        staffMembers.add(s);
    }

    private Date formatDate(String s) throws ParseException {
        Date d = new SimpleDateFormat("HH:mm").parse(s);
        return d;
    }

}
