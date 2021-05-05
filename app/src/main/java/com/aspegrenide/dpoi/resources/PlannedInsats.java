package com.aspegrenide.dpoi.resources;

import java.util.Date;

public class PlannedInsats{
    Insats insats;
    Kund kund;
    StaffMember staffMember;
    Date start;
    Date stop;
    String note;
    boolean checked;
    Date actualStart;
    Date actualStop;


    public PlannedInsats(Insats insats) {
        this.insats = insats;
    }

    public PlannedInsats(Insats insats, Kund kund, StaffMember staffMember) {
        this.insats = insats;
        this.kund = kund;
        this.staffMember = staffMember;
        this.start = insats.getDefaultStart();
        this.stop = insats.getDefaultStop();
    }

    public PlannedInsats(Insats insats, Kund kund, StaffMember staffMember, Date start, Date stop) {
        this.insats = insats;
        this.kund = kund;
        this.staffMember = staffMember;
        this.start = start;
        this.stop = stop;
    }

    public StaffMember getStaffMember() {
        return staffMember;
    }

    public Date getStart() {
        return start;
    }

    public Date getStop() {
        return stop;
    }

    public String getName() {
        return insats.getName();
    }

    public Kund getKund() {
        return kund;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
