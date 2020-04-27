package com.example.footballdatastore;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import static com.example.footballdatastore.R.id.teamsortbtn;
import static com.example.footballdatastore.R.layout.activity_teams;
import static com.example.footballdatastore.R.id.teamsearchbtn;

public class Teams extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    TextView scrollable;
    //Referencing the components used to build the user interface in the activity_teams.xml file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_teams);
        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        //Finds the components used on the user interface by the id assigned for each component
        scrollable = findViewById(R.id.text_view_result);
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        //Above the TextView identified is set to be scrollable

        Button btnSort = findViewById(teamsortbtn);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the sort button
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeamSort();
                //openTeamSort method is called
            }
        });

        Button btnSearch = findViewById(teamsearchbtn);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the search button
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeamSearch();
                //openTeamSearch method is called
            }
        });

        mQueue = Volley.newRequestQueue(this);
        //Volley library is implemented in the build.gradle file

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
        //The jsonParse method is called when the get data button is pressed
    }

    private void openTeamSort() {
        Intent intent = new Intent(this, TeamSort.class);
        startActivity(intent);
        //This method opens the Teams Sort page of the app
    }
    private void openTeamSearch() {
        Intent intent = new Intent(this, TeamSearch.class);
        startActivity(intent);
        //This method opens the Teams Search page of the app
    }

    private void jsonParse() {
    //This method gets the json file from the internet using the string url below and the internet permission set-up in the AndroidManifest.xml file
        String url = "https://raw.githubusercontent.com/grantmartin161096/FootballDataStore/master/TeamData.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("teams");
                            //Teams is the name of the object

                            //Below the Team array fields are called to get the data types for the following fields
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject teams = jsonArray.getJSONObject(i);

                                int position = teams.getInt("Position");
                                String team = teams.getString("Team");
                                int played = teams.getInt("Played");
                                int points = teams.getInt("Points");
                                int wins = teams.getInt("Wins");
                                int draws = teams.getInt("Draws");
                                int losses = teams.getInt("Losses");

                                //The data retrieved from the json file are captured and displayed in the TextView
                                mTextViewResult.append("Place: " + String.valueOf(position) + " Club: " + team + " Played: " + String.valueOf(played) + " Pts: " + String.valueOf(points) + " W: " + String.valueOf(wins) + " D: " + String.valueOf(draws) + " L: " + String.valueOf(losses) + "\n\n");
                            }
                            //catching exceptions
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        //volley library makes request
        mQueue.add(request);
    }
}
//Json data and parse: https://www.youtube.com/watch?v=y2xtLqP8dSQ
//https://codinginflow.com/tutorials/android/volley/part-1-simple-get-request