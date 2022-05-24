package com.axtec_bastida.personalagenda.AddNote;

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

        InitializeVariables();

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

}