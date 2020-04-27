package com.example.footballdatastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class HomepageActivity extends AppCompatActivity {
    Button btnLogout;
    Button btnTeams;
    Button btnPlayers;
    //Referencing the components used to build the user interface in the activity_homepage.xml file
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    //Configuring Firebase Authentication to be used with the app by authenticating email and password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btnLogout = findViewById(R.id.logout);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the Logout button
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(HomepageActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });
        //In the above code the user clicks the Logout button and the users status is changed to logout in Firebase and the user is taken to the Register page of the app (MainActivity)

        btnTeams = findViewById(R.id.buttonTeams);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the Logout button
        btnTeams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTeams();
                //openTeams method is called
            }
        });

        btnPlayers = findViewById(R.id.buttonPlayers);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the Logout button
        btnPlayers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayers();
                //openPlayers method is called
            }
        });
    }
        public void openTeams() {
            Intent intent = new Intent(this, Teams.class);
            startActivity(intent);
            //This method opens the Teams page of the app
        }
        public void openPlayers() {
            Intent intent = new Intent(this, Players.class);
            startActivity(intent);
            //This method opens the Players page of the app
    }
}
//https://www.youtube.com/watch?v=V0ZrnL-i77Q