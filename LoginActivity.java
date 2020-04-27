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
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    EditText emailId, password;
    Button btnLogin;
    TextView tvRegister;
    //Referencing the components used to build the user interface in the activity_login.xml file
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    //Configuring Firebase Authentication to be used with the app by authenticating email and password

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        //Gets the input for email and password to pass to Firebase for authentication
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnLogin = findViewById(R.id.GetTeam);
        tvRegister = findViewById(R.id.textView);
        //Finds the components used on the user interface by the id assigned for each component

        //Below the FirebaseAuth is checked to determine if the user is currently logged into the app or not
        mAuthStateListener = new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth)   {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                //Above the code is checking the user login/logout status
                if( mFirebaseUser !=null ){
                    Toast.makeText(LoginActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, HomepageActivity.class);
                    startActivity(i);
                    //If the user is logged in the user is displayed a toast pop-up message and the user is taken to the homepage of the app
                }
                else{
                    Toast.makeText(LoginActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                    //If the user is logged out then the user is shown a toast pop-up message asking them to login and the user is presented with the login page
                }
            }
        };
        //Below a click listener is set-up on the Login button and the method is designed to handle successful and unsuccessful scenarios
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
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
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        //In the above line of code the users entered email and password are captured and passed to Firebase to be authenticated
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(LoginActivity.this, "Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                                //If AuthResult is not successful the user is presented with a toast pop-up error message
                            }
                            else{
                                Intent intToHome = new Intent(LoginActivity.this,HomepageActivity.class);
                                startActivity(intToHome);
                                //If the AuthResult is successful then the user is taken to the HomepageActivity
                            }
                        }
                    });
                }

            }

        });
        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intRegister = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intRegister);
                //This method provides the navigation from the LoginActivity (Login page) to the HomepageActivity (Home page)
            }
        });
    }
    @Override
    protected void onStart(){
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
        //This method will open the app to the correct place depending on the users login/logout status
    }
}
//https://www.youtube.com/watch?v=V0ZrnL-i77Q