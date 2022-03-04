package com.axtec_bastida.personalagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.provider.FontsContractCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Registry extends AppCompatActivity {

    EditText NameEt, EmailEt, PasswordEt, ConfirmPasswordEt;
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
                ValidateData();
            }
        });

        HaveAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Registry.this, Login.class));
            }
        });

    }

    private void ValidateData() {
        name = NameEt.getText().toString();
        email = EmailEt.getText().toString();
        password = PasswordEt.getText().toString();
        confirmpassword = ConfirmPasswordEt.getText().toString();

        if (TextUtils.isEmpty(name)) {                                                              //Will validate if name is written
            Toast.makeText(this, "Insert Name", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {                              //Will validate that a Email is written [@ & /com]
            Toast.makeText(this, "Insert E-mail", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(password)) {                                                   //Will Validate that the password is written
            Toast.makeText(this, "Insert Password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(confirmpassword)) {                                            //Will Validate that the password is written
            Toast.makeText(this, "Confirm Password", Toast.LENGTH_SHORT).show();

        }
            else if(!password.equals(confirmpassword)){                                             //If password different than the confirmation then send message
                Toast.makeText(this,"Passwords Don't Match!",Toast.LENGTH_SHORT).show();
        }
        else {
            CreateAccount();
        }


    }

    private void CreateAccount () {

        progressDialog.setMessage("Creating Account");
        progressDialog.show();

        //Creating a User in Firebase
        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //
                        SaveData();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Registry.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });
    }

    private void SaveData() {
        progressDialog.setMessage("Saving Data");
        progressDialog.dismiss();

        //Obtain User ID:
        String Uid = firebaseAuth.getUid();

        HashMap<String,String> Data = new HashMap<>();

        Data.put("Uid", Uid);
        Data.put("Email", email);
        Data.put("Name", name);
        Data.put("Password", password);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(Uid)
                .setValue(Data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(Registry.this, "Account Created Successfully",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Registry.this, MainMenu.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(Registry.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {                                                          //This will send us to the Before Activity
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
