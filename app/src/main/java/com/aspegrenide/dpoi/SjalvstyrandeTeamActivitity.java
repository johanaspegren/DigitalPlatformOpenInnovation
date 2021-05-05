package com.aspegrenide.dpoi;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aspegrenide.dpoi.resources.DataManager;
import com.aspegrenide.dpoi.resources.Insats;
import com.aspegrenide.dpoi.resources.Kund;
import com.aspegrenide.dpoi.resources.PlannedInsats;
import com.aspegrenide.dpoi.resources.StaffMember;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
/*
Självstyrande team
team om två där man ser bådas arbetskort och kan ändra dynamiskt
Möjligt också för anhöriga att checka av insater

Fråga:
 */

public class SjalvstyrandeTeamActivitity extends AppCompatActivity implements NarvaroListAdapter.ItemClickListener {
    ArrayList <Kund> kunder = new ArrayList<>();
    ArrayList <StaffMember> staffMembers = new ArrayList<>();
    ArrayList <PlannedInsats> plannedInsatser = new ArrayList<>();
    ArrayList <PlannedInsats> overlamningar = new ArrayList<>();

    String PERS_ASS = "Personlig assistans";
    DataManager dataManager = new DataManager();

    NarvaroListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_narvaro);
        try {
            initDataManager();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // make sure only Överlämningar are displayed
        plannedInsatser = dataManager.getPlaneradeInsatser("", null);
        for(PlannedInsats pli : plannedInsatser) {
            if (pli.getName().equalsIgnoreCase("Överlämning")) {
                overlamningar.add(pli);
            }
        }
        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recListContainer);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NarvaroListAdapter(this, overlamningar);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);
        updateScreen();
    }

    private void initDataManager() throws ParseException {
        dataManager = new DataManager();
        dataManager.initKunder();
        dataManager.initStaffMember();
        try {
            dataManager.initInsatser();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        dataManager.initPlaneradeInsatser();
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    private void updateScreen() {
        // lista kunderna, tid för 'överlämning' och status
        kunder = getKunderMedInsats(PERS_ASS);
        for(Kund k: kunder) {
//            draw(overlamning);
        }

    }

    private void draw(PlannedInsats overlamning) {
        String nameKund = overlamning.getKund().getName();
        // Date when = overlamning.getStart() - now
        String namePersonal = overlamning.getStaffMember().getName();
        String phonePersonal = overlamning.getStaffMember().getTelefonNummer();
    }


    private ArrayList<Kund> getKunderMedInsats(String insatsName) {
        ArrayList<Kund> allaKunder = dataManager.getKunder("");
        return allaKunder;
    }

    private void checkInsats(int who) {
        // möjlighet att klicka i att en insats är genomförd
        // behöver RFID/Tag-stöd
        // inte säkert att det ska stödjas
        overlamningar.get(who).setChecked(true);
        adapter.notifyDataSetChanged();

    }

    public void btnSensorOnClick(View view) {
        if(view.getId() == R.id.btnOne) {
            checkInsats(0);
        }
        if(view.getId() == R.id.btnTwo) {
            checkInsats(1);
        }
        if(view.getId() == R.id.btnThree) {
            checkInsats(2);
        }
    }


    private ArrayList<PlannedInsats> loadplannedInsatser(){
        Date today = new Date();
        ArrayList<PlannedInsats>plannedInsatser = dataManager.getPlaneradeInsatser("Åbrädden", today);
        return plannedInsatser;
    }

    private void loadUsers(){
        kunder = dataManager.getKunder("Åbrädden");
    }

    private void loadStaffMembers(){
        staffMembers = dataManager.getStaffMembers("Åbrädden");
    }

    private ArrayList <Insats> getInsatser(Kund kund) {
        ArrayList <Insats> insatser = kund.getInsatser();
        return insatser;
    }

}