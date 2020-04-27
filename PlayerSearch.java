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

import static com.example.footballdatastore.R.id.textView6;
import static com.example.footballdatastore.R.id.textView7;

public class PlayerSearch extends AppCompatActivity {
    TextView text;
    TextView text1;
    TextView scrollable;
    EditText searchbar;
    Button buttonSearch;
    //Referencing the components used to build the user interface in the activity_player_search.xml file
    String str [] = {"Row: 13 Goals: 23 Player/s: Edouard",
            "Row: 12 Goals: 16 Player/s: Christie",
            "Row: 11 Goals: 12 Player/s: Forrest",
    "Row: 10 Goals: 11 Player/s: Dykes",
            "Row: 9 Goals: 10 Player/s: Griffith, Defoe",
            "Row: 8 Goals: 9 Player/s: Long",
    "Row: 7 Goals: 8 Player/s: Arfield",
            "Row: 6 Goals: 7 Player/s: Kent, Jone, Aaron",
            "Row: 5 Goals: 6 Player/s: Ferguson, McGinn, Doidge, Brophy",
    "Row: 4 Goals: 5 Player/s: Jullien, Rogic, McGregor, Jack, Campbell, Kennedy, Lawless, Allan",
            "Row: 3 Goals: 4 Player/s: Katic, Travenier, Polworth, McLennan, Kamberi, May, Halloran, Obika, McKay, Ogkmpoe, Naismith",
            "Row: 2  Goals: 3 Player/s: Ajer, O'Hara, Hylton, Considine, Ojo, Sibbald, Boyle, Docherty, Stevenson, Kabamba, Smith, Clare",
            "Row: 1  Goals: 2 Player/s: Taylor, Brown, Edmundson, Polster, Gallagher, Logan, Taylor, Devlin, Pitman, McMillan, Hanlon, Whittaker, Kerr, Wright, Davidson, Hendrie, Burke, Dicker, McKenzie, MacPherson, Wallace, Jaubiak, Tilson, Martin, Alston, Mayo, Hickey, Damour, Bozanic",
            "Row: 0  Goals: 1 Player/s: Tierney, Stewart, Grimshaw, Hartley, McGeouch, Ambrose, Brown, Jackson, Ralston, Butcher, Holt, Tanser, O'Donnell, Broadfoot, Findlay, Waters, Foley, Morris, Fontaine, Cowie, Grant, Gogic, Martin, Souttar, Moore"};
    //Player data to be sort in string array above

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_search);
        text = findViewById(textView6);
        text1 = findViewById(textView7);
        //Finds the components used on the user interface by the id assigned for each component

        scrollable = findViewById(textView6);
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        scrollable = findViewById(textView7);
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        //Above the TextView identified is set to be scrollable
        addListenerOnButton();
        //addListenerOnButton is created
    }
    //Below is the addListenerOnButton method
    private void addListenerOnButton() {
        searchbar = (EditText) findViewById(R.id.TextViewSearchGoals);
        buttonSearch = (Button) findViewById(R.id.buttonPlayerSearch);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the search button
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value1 = searchbar.getText().toString();
                //The users search input is captured and used to search the intArr
                PlayerSearch ob = new PlayerSearch();
                // initializing int array
                int intArr[]= { 23, 16, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };
                // sorts the int array
                Arrays.sort(intArr);

                for (int number : intArr) {
                }

                // Captures the users entered value to be searched
                int x = Integer.parseInt(value1);

                int result = Arrays.binarySearch(intArr,x);
                //below the result of the search is handled for successful and unsuccessful searches
                if (result == -15)
                    text.setText("Search not found");
                else
                    text.setText(text.getText() + "Row: " + result +"\n\n");

            }

        });
        //Below the sort string array is displayed to the user
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            buff.append(String.valueOf(str[i] + "\n\n"));
            Log.d("sai", String.valueOf(str[i]));
        }
        text1.setText(buff);

    }
}
//https://www.geeksforgeeks.org/binary-search/
//https://www.tutorialspoint.com/java/util/arrays_binarysearch_int.htm
