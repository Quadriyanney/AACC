package com.quadriyanney.aacc;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetList extends AppCompatActivity {

    String json_vars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_list);
    }

    public void DisplayList(View view){
        new Background().execute();
    }

    public class Background extends AsyncTask<Void, Void, String>{

        String json_url;
        String json_string;
        ProgressDialog progressDialog = new ProgressDialog(GetList.this);

        @Override
        protected void onPreExecute() {
            json_url = "https://api.github.com/search/users?q=language:java+location:lagos&per_page=200";
            progressDialog.setMessage("Fetching Data...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();

                while ((json_string = bufferedReader.readLine()) != null){
                    stringBuilder.append(json_string).append("\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String s) {
            json_vars = s;

            if (json_vars == null){
                progressDialog.dismiss();
                Toast.makeText(getBaseContext(), "Poor Internet Connection", Toast.LENGTH_LONG).show();
            }
            else {
                Intent i = new Intent(GetList.this, DevelopersList.class);
                i.putExtra("json_data", json_vars);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
            }
        }
    }
}
