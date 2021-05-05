package com.aspegrenide.dpoi;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aspegrenide.dpoi.resources.DataManager;
import com.aspegrenide.dpoi.resources.Kund;
import com.aspegrenide.dpoi.resources.PlannedInsats;
import com.aspegrenide.dpoi.resources.StaffMember;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/*
VemKommerHem
En app som visar en bild på vem som kommer på besök härnäst och hur lång tid det är dit
Appen läser ur planeradeInsatser, när är det dags för nästa
Bilden på personalen hämtas via planeradInsats => staffMember => bild
Namnet på personalen hämtas via planeradInsats => staffMember => namn

Fråga: Ska det vara möjligt att checka av insatser, eller att insats startat?
Fråga: Ska man kunna tracka GPS och se var personalen befinner sig?
 */

public class VemKommerHemActivitity extends AppCompatActivity {
    Kund kund;
    ArrayList <StaffMember> staffMembers;
    TextView txtName;
    TextView txtVad;
    TextView txtTime;
    ImageView imgPicture;
    DataManager dataManager;
    int currentIndex = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vemkommerhem);
        initDataManager();

        txtName = (TextView) findViewById(R.id.txtName);
        txtVad = (TextView) findViewById(R.id.txtVad);
        txtTime = (TextView) findViewById(R.id.txtTime);
        imgPicture = (ImageView) findViewById(R.id.imgPicture);

        updateScreen();
    }

    private void initDataManager() {
        dataManager = new DataManager();
        dataManager.initKunder();
        dataManager.initStaffMember();
        try {
            dataManager.initInsatser();
            dataManager.initPlaneradeInsatser();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void updateScreen() {
        // uppdatera listan, det kan ha hänt saker
        ArrayList<PlannedInsats> plannedInsatser = getPlannedInsatser(kund);
        // vilken insats är näst på tur?
        PlannedInsats pli = plannedInsatser.get(currentIndex);
        String when = formatDate(pli.getStart());
        String name = pli.getStaffMember().getName();
        String vad = pli.getName();
        int imgResource = pli.getStaffMember().getImgResource();

        imgPicture.setImageResource(imgResource);
        txtName.setText(name);
        txtTime.setText(when);
        txtVad.setText(vad);
    }


    public void btnForwardOnClick(View view){
        if(currentIndex < 2) { currentIndex +=1;}
        updateScreen();
    }

    public void btnBackOnClick(View view){
        if(currentIndex >  0) { currentIndex -=1;}
        updateScreen();
    }

    private void checkInsats() {
        // möjlighet att klicka i att en insats är genomförd
        // behöver RFID/Tag-stöd
        // inte säkert att det ska stödjas
    }

    private PlannedInsats getNext(ArrayList<PlannedInsats> plannedInsatser) {
        PlannedInsats nextPli = plannedInsatser.get(0);
        Date now = new Date();
        for (PlannedInsats pli : plannedInsatser){
            if(pli.getStart().before(now)) { //allready passed
                continue;
            }
            if(pli.getStart().before(nextPli.getStart())) {
                nextPli = pli;
            }
        }
        return nextPli;
    }

    public String formatDate(Date date) {
        String format = "HH:mm"; //24 hours format
        //hh:mm aa for 12 hours format
        DateFormat dateFormat = new SimpleDateFormat(format);
        return(dateFormat.format(date));
    }

    private ArrayList<PlannedInsats> getPlannedInsatser(Kund kund){
        Date today = new Date();
        ArrayList<PlannedInsats>plannedInsatser =
                dataManager.getPlaneradeInsatser(kund, today);
        return plannedInsatser;
    }

}