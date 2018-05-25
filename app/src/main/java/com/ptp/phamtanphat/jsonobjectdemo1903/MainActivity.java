package com.ptp.phamtanphat.jsonobjectdemo1903;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity{

    Button btnEn,btnVn;
    TextView txtKetqua;
    String khoahoc = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnEn = findViewById(R.id.buttnEn);
        btnVn = findViewById(R.id.buttnVn);
        txtKetqua = findViewById(R.id.textviewKetqua);

        btnEn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ReadJsonDemo1().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");
            }
        });
    }
    class ReadJsonDemo1 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return docNoiDung_Tu_URL(strings[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ReadLanguage(s , "en");
        }

        private String docNoiDung_Tu_URL(String theUrl){
            StringBuilder content = new StringBuilder();
            try    {
                // create a url object
                URL url = new URL(theUrl);

                // create a urlconnection object
                URLConnection urlConnection = url.openConnection();

                // wrap the urlconnection in a bufferedreader
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                String line;

                // read from the urlconnection via the bufferedreader
                while ((line = bufferedReader.readLine()) != null){
                    content.append(line + "\n");
                }
                bufferedReader.close();
            }
            catch(Exception e)    {
                e.printStackTrace();
            }
            return content.toString();
        }
    }
    public void ReadLanguage(String data , String word){
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONObject jsonObjectLanguage = jsonObject.getJSONObject("language");

            JSONObject jsonObjectEn = jsonObjectLanguage.getJSONObject(word);
            String name = jsonObjectEn.getString("name");
            String address = jsonObjectEn.getString("address");
            String course1 = jsonObjectEn.getString("course1");
            String course2 = jsonObjectEn.getString("course2");
            String course3 = jsonObjectEn.getString("course3");

            String ten ="Name : " + name + "\n";
            String diachi ="Dia chi : " + address + "\n";
            String khoahoc1 ="Khoa hoc 1 : " + course1 + "\n";
            String khoahoc2 ="Khoa hoc 2 : " + course2 + "\n";
            String khoahoc3 ="Khoa hoc 3 : " + course3 + "\n";

           txtKetqua.setText(ten + diachi + khoahoc1 + khoahoc2 + khoahoc3 );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
