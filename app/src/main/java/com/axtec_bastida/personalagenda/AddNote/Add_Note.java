package com.axtec_bastida.personalagenda.AddNote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.axtec_bastida.personalagenda.R;

import java.text.SimpleDateFormat;
import java.time.Month;
import java.time.Year;
import java.util.Calendar;
import java.util.Locale;

public class Add_Note extends AppCompatActivity {

    TextView Uid_User, User_Mail,Date_Actual_Time,Date,State;
    EditText Title,Description;
    Button Btn_Calendar;

    int day,month,year;

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
        ObtainDateandTime();

        Btn_Calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar calendar = Calendar.getInstance();
                day = calendar.get(Calendar.DAY_OF_MONTH);
                month = calendar.get(Calendar.MONTH);
                year = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Add_Note.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yearSelected, int monthSelected, int daySelected){

                        String dayFormat, monthFormat;

                        //OBTAIN DAY
                        if(daySelected < 10){                                               //If we don't do this Condition, the Date would look weird, we are making that a Cero appears before said date [09/11/2022]
                            dayFormat = "0"+String.valueOf(daySelected);
                        }else{
                            dayFormat =String.valueOf(daySelected);
                        }

                        //OBTAIN MONTH
                        int Month = monthSelected + 1;

                        if (Month < 10){
                            monthFormat = "0"+String.valueOf(Month);
                        }else {
                            monthFormat = String.valueOf(Month);
                        }

                        //SET DATE IN TEXTVIEW

                        Date.setText(dayFormat + "/" + monthFormat + "/" + yearSelected);

                    }
                }
                ,year,month,day);
                datePickerDialog.show();
            }
        });

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

    private void ObtainDateandTime(){
        String DateAndTimeRegister = new SimpleDateFormat("dd-MM-yyyy/HH:mm:ss a", Locale.getDefault()).format(System.currentTimeMillis());
            //Like that we get Time and Date like this; Day-Month-Year/Hours:Minutes:Seconds am/pm;
        Date_Actual_Time.setText(DateAndTimeRegister);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_agenda,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.Add_Notes_DB:
                Toast.makeText(this,"Added Note succesfully", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}