
package com.example.rajitha.waterme;

import android.graphics.Color;
import android.view.View;

public class processWater {
    private static int humidity;
    private static int temp;
    private static int soilMoisture;
    private static int optimalHumidity = 60;
    private static int optimalTemp = 28;
    private static int optimalSoil= 40;
    private static int mode = 1;


    public static void setHumidity(int humidity) {
        processWater.humidity = humidity;
    }

    public static void setTemp(int temp) {
        processWater.temp = temp;
    }

    public static void setSoilMoisture(int soilMoisture) {
        processWater.soilMoisture = soilMoisture;
    }

    public static void setOptimalTemp(int optimalTemp) {
        processWater.optimalTemp = optimalTemp;
    }

    public static void setOptimalSoil(int optimalSoil) {
        processWater.optimalSoil = optimalSoil;
    }

    public static void setOptimalHumidity(int optimalHumidity) {
        processWater.optimalHumidity = optimalHumidity;
    }

    public static void setMode(int mode) {
        processWater.mode = mode;
    }
    public static void modeProcess(){

        if (mode == 1){
            ControlFragment.control_hum.setVisibility(View.INVISIBLE);
            ControlFragment.control_temp.setVisibility(View.INVISIBLE);
            ControlFragment.control_water.setVisibility(View.INVISIBLE);


            if(humidity <= optimalHumidity + 15 && humidity > optimalHumidity - 10 ){

                ControlFragment.control_hum_status.setText("OFF");

            }else {
                ControlFragment.control_hum_status.setText("ON");
            }
            if (soilMoisture > optimalSoil + 5 || soilMoisture <= optimalSoil + 5 && soilMoisture > optimalSoil - 3){
                ControlFragment.control_water_status.setText("OFF");



            }else {
                ControlFragment.control_water_status.setText("ON");


            }
            if(temp <= optimalTemp + 5 && temp > optimalTemp - 3 ){

                ControlFragment.control_temp_status.setText("OFF");

            }else {

                ControlFragment.control_temp_status.setText("ON");

            }


        }else {
            ControlFragment.control_hum.setVisibility(View.VISIBLE);
            ControlFragment.control_temp.setVisibility(View.VISIBLE);
            ControlFragment.control_water.setVisibility(View.VISIBLE);

        }
    }
    public static void status(){
        if (humidity > optimalHumidity + 15){
            ControlFragment.h_status.setText("High");
            ControlFragment.h_status.setTextColor(Color.RED);


        }else if(humidity <= optimalHumidity + 15 && humidity > optimalHumidity - 10 ){
            ControlFragment.h_status.setTextColor(Color.BLUE);
            ControlFragment.h_status.setText("Optimal");


        }else {
            ControlFragment.h_status.setTextColor(Color.RED);
            ControlFragment.h_status.setText("Low");


        }
        if (soilMoisture > optimalSoil + 5){
            ControlFragment.s_status.setTextColor(Color.RED);
            ControlFragment.s_status.setText("High");


        }else if(soilMoisture <= optimalSoil + 5 && soilMoisture > optimalSoil - 3 ){
            ControlFragment.s_status.setTextColor(Color.BLUE);
            ControlFragment.s_status.setText("Optimal");


        }else {
            ControlFragment.s_status.setText("Low");
            ControlFragment.s_status.setTextColor(Color.RED);



        }
        if (temp > optimalTemp + 5){
            ControlFragment.t_status.setTextColor(Color.RED);
            ControlFragment.t_status.setText("High");


        }else if(temp <= optimalTemp + 5 && temp > optimalTemp - 3 ){
            ControlFragment.t_status.setTextColor(Color.BLUE);
            ControlFragment.t_status.setText("Optimal");


        }else {
            ControlFragment.t_status.setTextColor(Color.RED);
            ControlFragment.t_status.setText("Low");


        }

    }
}

