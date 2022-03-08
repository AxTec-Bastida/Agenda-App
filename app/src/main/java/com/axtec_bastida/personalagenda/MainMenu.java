package com.axtec_bastida.personalagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainMenu extends AppCompatActivity {

    Button CloseSession;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    TextView NameMain, EmailMain;
    ProgressBar progressBarData;

    DatabaseReference Users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Online - Agenda");

        NameMain = findViewById(R.id.NamesMain);
        EmailMain = findViewById(R.id.EmailMain);
        progressBarData = findViewById(R.id.progressBarData);

        Users = FirebaseDatabase.getInstance().getReference("Users");

        CloseSession = findViewById(R.id.CloseSession);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        CloseSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitApp();
            }

        });


    }

    @Override
    protected void onStart() {
        ProveSignIn();
        super.onStart();
    }

    private void ProveSignIn(){
        if(user!=null){
            //Will be executed when user Signs In
            LoadData();
        }else {
            //If not, will be directed to Main activity to register or login
            startActivity(new Intent(MainMenu.this, MainActivity.class));
            finish();
        }
    }

    private void LoadData(){
        Users.child(user.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //If User exist
                if (snapshot.exists()){

                    //Progress Bar hides
                    progressBarData.setVisibility(View.GONE);

                    //TextView shows themselves
                    NameMain.setVisibility(View.VISIBLE);
                    EmailMain.setVisibility(View.VISIBLE);

                    //Obtain Data
                    String names = ""+snapshot.child("Name").getValue();
                    String email = ""+snapshot.child("Email").getValue();

                    //Set Data to respective TextViews
                    NameMain.setText(names);
                    EmailMain.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void ExitApp() {
        firebaseAuth.signOut();
        startActivity(new Intent(MainMenu.this, MainActivity.class));
        Toast.makeText(this,"Successfully Signed Out", Toast.LENGTH_SHORT).show();
    }
}