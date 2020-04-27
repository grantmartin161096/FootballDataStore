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

import static com.example.footballdatastore.R.id.playersortbtn;
import static com.example.footballdatastore.R.id.playersearchbtn;

public class Players extends AppCompatActivity {
    private TextView mTextViewResult;
    private RequestQueue mQueue;
    TextView scrollable;
    //Referencing the components used to build the user interface in the activity_players.xml file

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        //Finds the components used on the user interface by the id assigned for each component
        scrollable = (TextView) findViewById(R.id.text_view_result);
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        //Above the TextView identified is set to be scrollable

        Button btnSort = findViewById(playersortbtn);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the sort button
        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayerSort();
                //openPlayerSort method is called
            }
        });

        Button btnSearch = findViewById(playersearchbtn);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the search button
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayerSearch();
                //openPlayerSearch method is called
            }
        });

        mQueue = Volley.newRequestQueue(this);
        //Volley library is implemented in the build.gradle file
        //RequestQueue makes the request to the network

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
        //The jsonParse method is called when the get data button is pressed
    }

    private void openPlayerSort() {
        Intent intent = new Intent(this, PlayerSort.class);
        startActivity(intent);
        //This method opens the Player Sort page of the app
    }

    private void openPlayerSearch() {
        Intent intent = new Intent(this, PlayerSearch.class);
        startActivity(intent);
        //This method opens the Player Search page of the app
    }

    private void jsonParse() {
    //This method gets the json file from the internet using the string url below and the internet permission set-up in the AndroidManifest.xml file
        String url = "https://raw.githubusercontent.com/grantmartin161096/FootballDataStore/master/PlayerData.json";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("players");
                            //Player is the name of the object

                            //Below the Player array fields are called to get the data types for the following fields
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject players = jsonArray.getJSONObject(i);

                                String name = players.getString("Name");
                                String club = players.getString("Club");
                                String position = players.getString("Position");
                                int appearances = players.getInt("Appearances");
                                int goals = players.getInt("Goals");
                                int assists = players.getInt("Assists");

                                //The data retrieved from the json file are captured and displayed in the TextView
                                mTextViewResult.append("Name: " + name + " Club: " + club + " Position: " + position + " Appearances: " + String.valueOf(appearances) + " Goals: " + String.valueOf(goals) + " Assists: " + String.valueOf(assists) + "\n\n");
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
        //request sends up the information to GET and Post
        mQueue.add(request);
    }
}
//Json data and parse: https://www.youtube.com/watch?v=y2xtLqP8dSQ
//https://codinginflow.com/tutorials/android/volley/part-1-simple-get-request