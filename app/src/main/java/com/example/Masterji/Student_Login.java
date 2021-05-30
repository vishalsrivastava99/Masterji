package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Student_Login extends AppCompatActivity {

    private Button login_btn;
    private EditText email,password;
    private String loginemail,loginpassword;
    private DatabaseReference reference,dbref;
    private StorageReference storageReference;
    private ProgressDialog pd;
    private FirebaseAuth mAuth;
    private TextView gotosignup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        //getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        login_btn = findViewById(R.id.loginbtn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        gotosignup = findViewById(R.id.gotosignup);
        reference = FirebaseDatabase.getInstance().getReference().child("Tutor");
        storageReference = FirebaseStorage.getInstance().getReference().child("Tutor");
        pd = new ProgressDialog(this);



        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkvalidation();
                //startActivity(new Intent(Student_Login.this,Student_signup.class));
            }
        });

        gotosignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_Login.this,Student_signup.class));
            }
        });

    }

    private void checkvalidation() {
        loginemail = email.getText().toString();
        loginpassword = password.getText().toString();

        if(loginemail.isEmpty()){
            email.setError("Empty");
            email.requestFocus();
        }else if (loginemail.length()<15){
            email.setError("Email is not valid");
            email.requestFocus();
        }else if(loginpassword.isEmpty()){
            password.setError("Empty");
            password.requestFocus();
        }else {
            signin();
        }


    }
    private void signin() {
        pd.setMessage("Signing...");
        pd.show();
        mAuth.signInWithEmailAndPassword(loginemail, loginpassword)
                .addOnCompleteListener(Student_Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            pd.dismiss();
                            email.setText("");
                            password.setText("");
                            email.requestFocus();
                            Intent intent = new Intent(Student_Login.this,TutorHomePage.class);
//                                intent.putExtra("email", mAuth.getCurrentUser().getEmail());
//                                intent.putExtra("name", mAuth.getCurrentUser().getDisplayName());
//                              intent.putExtra("uid", mAuth.getCurrentUser().getUid());
                            startActivity(intent);

                        } else {
                            pd.dismiss();
                            email.setText("");
                            password.setText("");
                            email.requestFocus();
                            Toast.makeText(Student_Login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();


                        }
                    }
                });


    }

}