package com.example.footballdatastore;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

import static com.example.footballdatastore.R.id.textViewSearchTeam;
import static com.example.footballdatastore.R.id.textViewSearchDataTeam;

public class TeamSearch extends AppCompatActivity {
    TextView text;
    TextView text1;
    TextView scrollable;
    EditText searchbar;
    Button buttonSearch;
    //Referencing the components used to build the user interface in the activity_team_search.xml file
    String[] str = {"Row: 11 Team: Glasgow Green Points: 80 ", "Row: 10 Team: Glasgow Blue Points: 67", "Row: 9 Team: Motherwell Points: 46",
            "Row: 8 Team: Aberdeen Points: 45", "Row: 7 Team: Livingston Points: 39", "Row: 6 Team: Leith Points: 37",
            "Row: 5 Team: Perth Points: 36", "Row: 4 Team: Kilmarnock Points: 33", "Row: 3 Team: Paisley Points: 29",
            "Row: 2 Team: Highlands Points: 27", "Row: 1 Team: Hamilton Points: 27", "Row: 0 Team: Edinburgh Points: 23"};
    //Player data to be sort in string array above
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_search);
        text = findViewById(textViewSearchTeam);
        text1 = findViewById(textViewSearchDataTeam);
        //Finds the components used on the user interface by the id assigned for each component

        scrollable = findViewById(textViewSearchTeam);
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        scrollable = findViewById(textViewSearchDataTeam);
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        //Above the TextView identified is set to be scrollable
        addListenerOnButton();
        //addListenerOnButton is created
    }
    //Below is the addListenerOnButton method
    private void addListenerOnButton() {
        searchbar = findViewById(R.id.textViewSearchBar);
        buttonSearch = findViewById(R.id.searchbtn1);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the search button
        buttonSearch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1 = searchbar.getText().toString();
                //The users search input is captured and used to search the intArr
                TeamSearch ob = new TeamSearch();
                // initializing int array
                int intArr[]= { 80, 67, 46, 45, 39, 37, 36, 33, 29, 27, 27, 23 };
                // sorts the int array
                Arrays.sort(intArr);

                // let us print all the elements available in list
                System.out.println("The sorted int array is:");
                for (int number : intArr) {
                    System.out.println("Number = " + number);
                }

                // Captures the users entered value to be searched
                int x = Integer.parseInt(value1);

                int result = Arrays.binarySearch(intArr,x);
                //below the result of the search is handled for successful and unsuccessful searches
                if (result == -13)
                    text.setText("Search not found");
                else
                    text.setText(text.getText() + "Row: " + result +"\n\n");

            }

        });
        //Below the sort string array is displayed to the user
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            buff.append(str[i] + "\n\n");
            Log.d("sai", String.valueOf(str[i]));
        }
        text1.setText(buff);

    }
}
//https://www.geeksforgeeks.org/binary-search/
//https://www.tutorialspoint.com/java/util/arrays_binarysearch_int.htm