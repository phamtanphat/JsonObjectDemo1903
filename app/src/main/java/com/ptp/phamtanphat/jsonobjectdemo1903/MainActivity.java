package com.ptp.phamtanphat.jsonobjectdemo1903;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity{

    Button btnReadJsonDemo1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnReadJsonDemo1 = findViewById(R.id.buttnReadjson);
    }
    class ReadJsonDemo1 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return null;
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
}
