package com.example.samsung.hilaris;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Vector;

public class Week_Day_Select extends AppCompatActivity {
    TableLayout tableLayout;
    int num = 0;
    String title;
    String prescription_guidline;
    Prescription_Guideline Prescription_Guideline;

    Prescription prescription;
    Exercise_routine ex_routine;
    Exercise_unit unit;

    Vector vextor[][] = new Vector[7][4];

    //4*7 TextView array
    TextView[][] week_day = new TextView[7][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int i = 0;
        int j = 0;

        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_week__day__select);

        Intent intent = getIntent();
        title = intent.getExtras().getString("routine");
        prescription_guidline = intent.getExtras().getString("prescription_guidline");

        JSONObject object;
        try {
            object = new JSONObject(prescription_guidline);
            Prescription_Guideline = new Prescription_Guideline(object);
            prescription = Prescription_Guideline.prescription;
        } catch (JSONException e) {
            e.printStackTrace();
        }


        tableLayout = (TableLayout) findViewById(R.id.table_layout);

        for (int m = 0; m < 7; m++) {
            for (int k = 0; k < 4; k++) {
                week_day[m][k] = (TextView) tableLayout.findViewWithTag("" + num);
                num++;
            }
        }

        for(int a=0; a<prescription.routine_length; a++) {
            if(title.equals(prescription.routine[i].Title)){
                ex_routine=prescription.routine[i];
            }
        }

        for(int b=0; b<ex_routine.unit_length; b++){
            vextor[Integer.parseInt(ex_routine.exercise_unit[b].session)][Integer.parseInt(ex_routine.exercise_unit[b].week)].add(ex_routine.exercise_unit[b]);
        }

        for (int session = 0; session<7; session++){
            for(int week = 0; week < 4; week++){
                if(vextor[session][week].isEmpty() == true){
                    week_day[session][week].setBackgroundResource(android.R.color.darker_gray);
                    week_day[session][week].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "No Exercise", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    week_day[session][week].setBackgroundResource(android.R.color.holo_green_light);
                    week_day[session][week].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), Exercises_Select.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }

        /*
        week_day[0][0].setBackgroundResource(android.R.color.holo_green_light);
        week_day[0][1].setBackgroundResource(android.R.color.holo_green_light);
        week_day[0][2].setBackgroundResource(android.R.color.holo_green_light);
        week_day[0][3].setBackgroundResource(android.R.color.holo_green_light);
        week_day[1][0].setBackgroundResource(android.R.color.holo_green_light);
        week_day[1][1].setBackgroundResource(android.R.color.holo_green_light);
        week_day[1][2].setBackgroundResource(android.R.color.holo_green_light);
        week_day[1][3].setBackgroundResource(android.R.color.holo_green_light);
        week_day[2][0].setBackgroundResource(android.R.color.holo_green_light);
        week_day[2][1].setBackgroundResource(android.R.color.holo_green_light);
        week_day[2][2].setBackgroundResource(android.R.color.holo_green_light);
        week_day[2][3].setBackgroundResource(android.R.color.holo_green_light);
        week_day[3][0].setBackgroundResource(android.R.color.holo_green_light);
        week_day[3][1].setBackgroundResource(android.R.color.holo_green_light);
        week_day[3][2].setBackgroundResource(android.R.color.holo_green_light);
        week_day[3][3].setBackgroundResource(android.R.color.holo_green_light);
        week_day[4][0].setBackgroundResource(android.R.color.holo_green_light);
        week_day[4][1].setBackgroundResource(android.R.color.darker_gray);
        week_day[4][2].setBackgroundResource(android.R.color.holo_green_light);
        week_day[4][3].setBackgroundResource(android.R.color.holo_green_light);
        week_day[5][0].setBackgroundResource(android.R.color.holo_green_light);
        week_day[5][1].setBackgroundResource(android.R.color.darker_gray);
        week_day[5][2].setBackgroundResource(android.R.color.holo_green_light);
        week_day[5][3].setBackgroundResource(android.R.color.holo_green_light);
        week_day[6][0].setBackgroundResource(android.R.color.holo_green_light);
        week_day[6][1].setBackgroundResource(android.R.color.darker_gray);
        week_day[6][2].setBackgroundResource(android.R.color.darker_gray);
        week_day[6][3].setBackgroundResource(android.R.color.holo_green_light);
        */
/*
        for (i = 0; i < 7; i++) {
            for (j = 0; j < 4; j++) {
                if((i == 4 && j == 1) || (i == 5 && j == 1) || (i == 6 && j == 1) || (i == 6 && j ==2)) {
                    week_day[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Toast.makeText(getApplicationContext(), "No Exercise", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                else{
                    week_day[i][j].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getApplicationContext(), Exercises_Select.class);
                            startActivity(intent);
                        }
                    });
                }
            }
        }*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.logout_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.logout:
                Intent intent = new Intent(Week_Day_Select.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}