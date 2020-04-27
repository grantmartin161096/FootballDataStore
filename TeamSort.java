package com.example.footballdatastore;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;


import static com.example.footballdatastore.R.id.textViewSort;


public class TeamSort extends AppCompatActivity {
    TextView text;
    TextView scrollable;
    String temp;
    //Referencing the components used to build the user interface in the activity_team_sort.xml file
    String str [] = {"Glasgow Green","Glasgow Blue","Motherwell","Aberdeen","Livingston","Leith","Perth","Kilmarnock","Paisley","Highlands",
            "Hamilton","Edinburgh"};
    //Player data to be sort in string array above

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_sort);
        text = findViewById(textViewSort);

        scrollable = findViewById(textViewSort);
        //Finds the components used on the user interface by the id assigned for each component
        scrollable.setMovementMethod(new ScrollingMovementMethod());
        //Above the TextView identified is set to be scrollable


        //Below the bubble sort is applied
        for (int i = 0; i < str.length; i++) {
            //The compareTo method compare the strings in the array to be sorted
            //The string array is sorted from A-Z
            //If the list is not in order the code is swapping values with a temp (temporary) string value
            for (int j = i + 1; j < str.length; j++) {
                if (str[i].compareTo(str[j])>0) {
                    temp = str[i];
                    str[i] = str[j];
                    str[j] = temp;
                }
            }
        }
        //Below the sort string array is displayed to the user
        StringBuffer buff = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            buff.append(String.valueOf(str[i] + "\n\n"));
            Log.d("sai", String.valueOf(str[i]));
        }
        text.setText(buff);
    }
}
//https://beginnersbook.com/2019/04/java-program-to-perform-bubble-sort-on-strings/
//https://www.tutorialspoint.com/how-to-sort-an-array-elements-in-android
