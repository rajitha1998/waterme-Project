package com.example.rajitha.waterme;

import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class processCloud extends AsyncTask<Void,Void,Void>{
    private  String url;
    public String value_1;
    public String value_3;
    public String value_2;
    private  String data ;

    public processCloud(String url) {
        this.url = url;
    }

    @Override
    protected Void doInBackground(Void... voids) {

        try {
            URL data_url = new URL(url);
            HttpURLConnection data_connection = (HttpURLConnection) data_url.openConnection();
            InputStream stream = data_connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            data =  reader.readLine();
            data = "[" + data + "]" ;
            JSONArray cloud_values = new JSONArray(data);
            JSONObject values = (JSONObject) cloud_values.get(0);
            value_1 = values.getString("temp");
            value_2 = values.getString("hum");
            value_3 = values.getString("soil");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }


    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

            ControlFragment.tempText.setText(value_1);
            processWater.setTemp(Integer.parseInt(value_1));
            ControlFragment.humidityText.setText(value_2);
            processWater.setHumidity(Integer.parseInt(value_2));
            ControlFragment.soilText.setText(value_3);
            processWater.setSoilMoisture(Integer.parseInt(value_3));


    }
}
