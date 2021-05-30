package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Tutee_Login extends AppCompatActivity {

    private Button studentloginbtn;
    private TextView gotostudentsignup;
    private String Tuteeemail,Tuteepassword;
    private FirebaseAuth mAuth;
    private DatabaseReference reference;
    private StorageReference storageReference;
    private ProgressDialog pd;
    private EditText email,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee__login);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Tutee");
        storageReference = FirebaseStorage.getInstance().getReference().child("Tutee");
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        studentloginbtn = findViewById(R.id.studentloginbtn);
        gotostudentsignup = findViewById(R.id.gotostudentsignup);
        pd = new ProgressDialog(this);

        studentloginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkvalidation();
                //Toast.makeText(Tutee_Login.this, "clicked..", Toast.LENGTH_SHORT).show();
            }
        });

        gotostudentsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutee_Login.this, Tutee_Signup.class));
                finish();
            }
        });

    }

    private void checkvalidation() {

        Tuteeemail = email.getText().toString().trim();
        Tuteepassword = password.getText().toString().trim();

        if(Tuteeemail.isEmpty()){
            email.setError("Empty");
            email.requestFocus();
        }else if(Tuteepassword.isEmpty()){
            password.setError("Empty");
            password.requestFocus();
        }else {
            signin();
        }

    }

    private void signin() {

        pd.setMessage("Signing...");
        pd.show();
        mAuth.signInWithEmailAndPassword(Tuteeemail,Tuteepassword)
                .addOnCompleteListener(Tutee_Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){

                            pd.dismiss();
                            email.setText("");
                            password.setText("");
                            email.requestFocus();
                            startActivity(new Intent(Tutee_Login.this,Tutee_HomePage.class));
                            finish();
                        }else{
                            pd.dismiss();
                            email.setText("");
                            password.setText("");
                            email.requestFocus();
                            Toast.makeText(Tutee_Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }
}