package com.axtec_bastida.personalagenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText EmailLogin, PassLogin;
    Button Btn_Loged;
    TextView NewUser;

    ProgressDialog progressDialog;

    FirebaseAuth firebaseAuth;

    //Validate Data
    String mail = "", password = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Login");
        actionBar.setDisplayHomeAsUpEnabled(true);  //Creates backwards key
        actionBar.setDisplayShowHomeEnabled(true);

        EmailLogin = findViewById(R.id.EmailLogin);
        PassLogin = findViewById(R.id.PassLogin);
        Btn_Loged = findViewById(R.id.Btn_Loged);
        NewUser = findViewById(R.id.NewUser);

        firebaseAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Wait Please");
        progressDialog.setCanceledOnTouchOutside(false);        //When Pressing put charge bar it doenst hide

        Btn_Loged.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateData();
            }
        });

        NewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registry.class));  //It redirects us to new register activity class
            }
        });

    }

    private void ValidateData() {

        mail = EmailLogin.getText().toString();
        password = PassLogin.getText().toString();

        if(!Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            Toast.makeText(this, "Not Valid Mail", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this, "Insert Password", Toast.LENGTH_SHORT).show();
        }
        else{
            UserLogin();
        }

    }

    private void UserLogin() {

        progressDialog.setMessage("Signing in...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            progressDialog.dismiss();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            startActivity(new Intent(Login.this, MainMenu.class));
                            Toast.makeText(Login.this, "Welcome: "+user.getEmail(), Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            progressDialog.dismiss();
                            Toast.makeText(Login.this, "Verify User and Password are correct", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                Toast.makeText(Login.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}