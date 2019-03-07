package com.example.rajitha.waterme;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class settingsFragment extends Fragment {
    /**/
       /**/

    public settingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View inf = inflater.inflate(R.layout.fragment_settings, container, false);



        Button btnSoil = inf.findViewById(R.id.waterbtn);
        Button btnHum =inf.findViewById(R.id.humbtn);
        Button temBtn = inf.findViewById(R.id.tempbtn);
        temBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText updateTemp = inf.findViewById(R.id.tem);
                TextView optimalTemp = inf.findViewById(R.id.optimalTemp);
                int update = Integer.parseInt(updateTemp.getText().toString().trim());
                // Write a message to the database
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("optimalTemp");

                myRef.setValue(update);
                optimalTemp.setText(String.valueOf(update));
                processWater.setOptimalTemp(update);

            }
        });
        btnSoil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText updateSoil = inf.findViewById(R.id.water);
                TextView optimalSoil = inf.findViewById(R.id.optimalSoil);
                int update = Integer.parseInt(updateSoil.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("optimalsoil");

                myRef.setValue(update);
                optimalSoil.setText(String.valueOf(update));
                processWater.setOptimalSoil(update);


            }
        });
        btnHum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView optimalHum = inf.findViewById(R.id.optimalHum);
                EditText updateHum = inf.findViewById(R.id.hum);
                int update = Integer.parseInt(updateHum.getText().toString());
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("optimalHum");

                myRef.setValue(update);
                optimalHum.setText(String.valueOf(update));
                processWater.setOptimalHumidity(update);


            }
        });
        return inf;
    }


}
