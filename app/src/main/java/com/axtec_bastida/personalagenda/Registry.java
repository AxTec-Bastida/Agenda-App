package com.axtec_bastida.personalagenda;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class Registry extends AppCompatActivity {

    EditText NameEt, EmailEt,PasswordEt,ConfirmPasswordEt;
    Button RegisterUser;
    TextView HaveAcc;

    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;

    //
    String name = " ",
    email = " ",
    password = " ",
    confirmpassword = " ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Register");
        actionBar.setDisplayHomeAsUpEnabled(true);  //Creates backwards key
        actionBar.setDisplayShowHomeEnabled(true);

        NameEt = findViewById(R.id.NameEt);
        EmailEt = findViewById(R.id.EmailEt);
        PasswordEt = findViewById(R.id.PasswordEt);
        ConfirmPasswordEt = findViewById(R.id.ConfirmPasswordEt);
        RegisterUser = findViewById(R.id.RegisterUser);
        HaveAcc = findViewById(R.id.HaveAcc);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Registry.this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);

        RegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        HaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

    }

}
