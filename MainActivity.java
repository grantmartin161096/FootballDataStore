package com.example.footballdatastore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnRegister;
    TextView tvLogin;
    //Referencing the components used to build the user interface in the activity_main.xml file
    FirebaseAuth mFirebaseAuth;
    //Configuring Firebase Authentication to be used with the app by authenticating email and password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        //Gets the input for email and password to pass to Firebase for authentication
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnRegister = findViewById(R.id.GetTeam);
        tvLogin = findViewById(R.id.textView);
        //Finds the components used on the user interface by the id assigned for each component

        //Below a click listener is set-up on the register button and the method is designed to handle successful and unsuccessful scenarios
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                //entered email and password are captured in the data type string when register button clicked
                if(email.isEmpty()){
                    emailId.setError("Please enter email");
                    emailId.requestFocus();
                }
                //Above the scenario of empty email field is handled
                else if(pwd.isEmpty()){
                    password.setError("Please enter password");
                    password.requestFocus();
                }
                //Above the scenario of empty password field is handled
                else {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        //In the above line of code the users entered email and password are captured and passed to Firebase to be authenticated
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this, "Register unsuccessful, Please try again",Toast.LENGTH_SHORT).show();
                                //If AuthResult is not successful the user is presented with a toast pop-up error message
                            }
                            else {
                                startActivity(new Intent(MainActivity.this,HomepageActivity.class));
                                //If the AuthResult is successful then the user is taken to the HomepageActivity
                            }
                        }
                    });
                }
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
                //This method provides the navigation from the MainActivity (Register page) to the HomepageActivity (Home page)
            }
        });
    }
}
//https://www.youtube.com/watch?v=V0ZrnL-i77Q