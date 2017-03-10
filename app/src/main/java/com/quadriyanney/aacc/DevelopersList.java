package com.quadriyanney.aacc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DevelopersList extends AppCompatActivity {

    String json_string;
    JSONObject jsonObject;
    JSONArray jsonArray;
    DevAdapter devAdapter;
    ListView listView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developers_list);

        listView = (ListView) findViewById(R.id.dev_list);
        toolbar = (Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Developers List");
        devAdapter = new DevAdapter(this, R.layout.list_layout);
        listView.setAdapter(devAdapter);
        json_string = getIntent().getExtras().getString("json_data");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                DeveloperInfo developerInfo = (DeveloperInfo) devAdapter.getItem(i);

                Intent intent = new Intent(DevelopersList.this, ProfileDisplay.class);
                assert developerInfo != null;
                intent.putExtra("username", developerInfo.getUsername());
                intent.putExtra("url", developerInfo.getGit_url());
                intent.putExtra("photo", developerInfo.getUser_photo());
                startActivity(intent);
            }
        });

        try {

            jsonObject = new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("items");

            int counter = 0;

            String username, user_photo, git_url;

            while (counter < jsonArray.length()){

                JSONObject json = jsonArray.getJSONObject(counter);

                username = json.getString("login");
                user_photo = json.getString("avatar_url");
                git_url = json.getString("html_url");

                DeveloperInfo info = new DeveloperInfo(username, user_photo, git_url);
                devAdapter.add(info);
                counter++;
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
