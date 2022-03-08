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

import com.axtec_bastida.personalagenda.AddNote.Add_Note;
import com.axtec_bastida.personalagenda.ArchivedNotes.Archived_Notes;
import com.axtec_bastida.personalagenda.ListNote.List_Notes;
import com.axtec_bastida.personalagenda.Profile.Profile_User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class MainMenu extends AppCompatActivity {

    Button AddNotes,ListNotes,Archived,Profile,About, CloseSession;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;

    TextView UidMain, NameMain, EmailMain;
    ProgressBar progressBarData;

    DatabaseReference Users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Online - Agenda");

        UidMain = findViewById(R.id.UidMain);
        NameMain = findViewById(R.id.NamesMain);
        EmailMain = findViewById(R.id.EmailMain);
        progressBarData = findViewById(R.id.progressBarData);

        Users = FirebaseDatabase.getInstance().getReference("Users");


        About = findViewById(R.id.About);
        Archived = findViewById(R.id.Archived);
        AddNotes = findViewById(R.id.AddNotes);
        ListNotes = findViewById(R.id.ListNotes);
        CloseSession = findViewById(R.id.CloseSession);

        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();

        AddNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, Add_Note.class));
                Toast.makeText(MainMenu.this, "Add Note", Toast.LENGTH_SHORT).show();

            }
        });

        ListNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, List_Notes.class));
                Toast.makeText(MainMenu.this, "List Notes", Toast.LENGTH_SHORT).show();
            }
        });

        Archived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, Archived_Notes.class));
                Toast.makeText(MainMenu.this, "Archived Notes", Toast.LENGTH_SHORT).show();
            }
        });

        Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainMenu.this, Profile_User.class));
                Toast.makeText(MainMenu.this, "User Profile", Toast.LENGTH_SHORT).show();
            }
        });

        About.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainMenu.this, "About", Toast.LENGTH_SHORT).show();
            }
        });

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
                    UidMain.setVisibility(View.VISIBLE);
                    NameMain.setVisibility(View.VISIBLE);
                    EmailMain.setVisibility(View.VISIBLE);

                    //Obtain Data
                    String uid = ""+snapshot.child("uid").getValue();
                    String names = ""+snapshot.child("Name").getValue();
                    String email = ""+snapshot.child("Email").getValue();

                    //Set Data to respective TextViews
                    UidMain.setText(uid);
                    NameMain.setText(names);
                    EmailMain.setText(email);

                    //Enable menu buttons
                    AddNotes.setEnabled(true);
                    ListNotes.setEnabled(true);
                    Archived.setEnabled(true);
                    About.setEnabled(true);
                    Profile.setEnabled(true);
                    CloseSession.setEnabled(true);

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