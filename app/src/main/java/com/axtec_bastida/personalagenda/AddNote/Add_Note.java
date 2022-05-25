package com.axtec_bastida.personalagenda.AddNote;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.axtec_bastida.personalagenda.R;

public class Add_Note extends AppCompatActivity {

    TextView Uid_User, User_Mail,Date_Actual_Time,Date,State;
    EditText Title,Description;
    Button Btn_Calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("");
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        InitializeVariables();
        ObtainData();

    }

    private void InitializeVariables(){
        Uid_User = findViewById(R.id.Uid_User);
        User_Mail = findViewById(R.id.User_Mail);
        Date_Actual_Time = findViewById(R.id.Date_Actual_Time);
        Date = findViewById(R.id.Date);
        State = findViewById(R.id.State);

        Title = findViewById(R.id.Title);
        Description = findViewById(R.id.Descrption);

        Btn_Calendar = findViewById(R.id.Btn_Calendar);
    }

    private void ObtainData(){

        String uid_recovered = getIntent().getStringExtra("Uid");
        String email_recovered = getIntent().getStringExtra("E-mail");

        Uid_User.setText(uid_recovered);
        User_Mail.setText(email_recovered);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}