package com.emedinaa.jsonsample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {


    private static final String TAG ="MainActivity" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JSONObject jsonObject=null;

        String sJson= "";

        try {
            sJson= loadJSONFromAsset();
            jsonObject = new JSONObject(sJson);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "sJson "+sJson);

        if(jsonObject!=null){
            try {
                JSONObject data = jsonObject.getJSONObject("data");
                Log.v(TAG, "data "+data);

                String title;
                String ruta;
                JSONArray array = data.getJSONArray("videos");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject row = array.getJSONObject(i);
                    title = row.getString("title");
                    ruta = row.getString("ruta");

                    Log.v(TAG, String.format("row title %s ruta %s",title,ruta));
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.v(TAG, "JSONException "+e);
            }
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("myJson.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
