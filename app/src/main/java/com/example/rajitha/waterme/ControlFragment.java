package com.example.rajitha.waterme;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class ControlFragment extends Fragment  {
    public static TextView soilText;
    public static TextView humidityText;
    public static TextView tempText;
    public static TextView statusText;
    public static TextView h_status;
    public static TextView s_status;
    public static TextView t_status;
    public static Switch control_water;
    public static Switch control_hum;
    public static Switch control_temp;
    public static TextView control_water_status;
    public static TextView control_hum_status;
    public static TextView control_temp_status;
    public static  Switch mode;

    public ControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_control, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        h_status =getView().findViewById(R.id.h_status);
        control_hum_status = getView().findViewById(R.id.sate_2);
        control_water_status =getView().findViewById(R.id.state_1);
        control_temp_status = getView().findViewById(R.id.state_3);
        s_status =getView().findViewById(R.id.s_status);
        t_status =getView().findViewById(R.id.t_status);
        tempText = getView().findViewById(R.id.tem_data);
        humidityText = getView().findViewById(R.id.hum_value);
        soilText = getView().findViewById(R.id.soil_value);
        statusText = getView().findViewById(R.id.status);
        control_water = getView().findViewById(R.id.switchWater);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("mode");

        myRef.setValue(1);
        control_water.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    control_water_status.setText("ON");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("motor");

                    myRef.setValue(1);


                }else {
                    control_water_status.setText("OFF");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("motor");

                    myRef.setValue(0);



                }


            }
        });
        control_hum = getView().findViewById(R.id.switchHumidity);
        control_hum.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    control_hum_status.setText("ON");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("humCon");

                    myRef.setValue(1);


                }else {
                    control_hum_status.setText("OFF");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("humCon");

                    myRef.setValue(0);


                }

            }
        });
        control_temp = getView().findViewById(R.id.switchTemp);
        control_temp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    control_temp_status.setText("ON");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("tempCon");

                    myRef.setValue(1);




                }else {
                    control_temp_status.setText("OFF");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("tempCon");

                    myRef.setValue(0);


                }
            }
        });
        mode = getView().findViewById(R.id.modeSwitch);
        mode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    statusText.setText("Auto");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("mode");

                    myRef.setValue(1);
                    processWater.setMode(1);

                }else {
                    statusText.setText("Manual");
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("mode");

                    myRef.setValue(0);
                    processWater.setMode(0);
                    control_temp_status.setText("OFF");
                    control_water_status.setText("OFF");
                    control_hum_status.setText("OFF");
                    FirebaseDatabase data = FirebaseDatabase.getInstance();
                    DatabaseReference Ref = data.getReference("tempCon");

                    Ref.setValue(0);
                    FirebaseDatabase databaseHum = FirebaseDatabase.getInstance();
                    DatabaseReference myRefHum = databaseHum.getReference("humCon");

                    myRefHum.setValue(0);
                    FirebaseDatabase databaseSoil = FirebaseDatabase.getInstance();
                    DatabaseReference myRefSoil = databaseSoil.getReference("motor");

                    myRefSoil.setValue(0);



                }

            }
        });
        callAsynchronousTask();
    }
    public void callAsynchronousTask() {
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            processCloud process = new processCloud("https://soilwateriit.firebaseio.com/.json");
                            process.execute();
                            processWater.status();
                            processWater.modeProcess();
                        } catch (Exception e) {
                            // Exception handling
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 1000);
    }
}
