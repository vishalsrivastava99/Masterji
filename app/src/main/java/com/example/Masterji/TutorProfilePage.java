package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class TutorProfilePage extends AppCompatActivity {

    private final int REQ = 1 ;
    TextView teachername;
    EditText teacheremail,teachermobile,teacherdegree,teachersubjects,teacherexperience,teacheraboutme,teachercity;
    DatabaseReference reference,profileref,dbref;
    FirebaseDatabase db;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    Context context;
    //variables to update profile data
    ImageView profileimage;
    String contact,degree,experience,aboutme,subjects,downloadurl="",name,email,city;
    Button updatebtn;
    Bitmap bitmap;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_profile_page);

        mAuth = FirebaseAuth.getInstance();

        teachername = findViewById(R.id.teachername );
        teacheremail = findViewById(R.id.teacheremail);
        teachermobile = findViewById(R.id.teachermobile);
        teacherdegree = findViewById(R.id.teacherdegree);
        teachersubjects = findViewById(R.id.teachersubjects);
        teacherexperience = findViewById(R.id.teacherexperience);
        teacheraboutme = findViewById(R.id.teacheraboutme);
        teachercity = findViewById(R.id.teachercity);
        profileimage = findViewById(R.id.profileimage);
        updatebtn = findViewById(R.id.updatebtn);
        pd = new ProgressDialog(this);
        storageReference = FirebaseStorage.getInstance().getReference();
        db = FirebaseDatabase.getInstance();


       //getting Name,email,city from signup to profile page.
        FirebaseUser user = mAuth.getCurrentUser();
        String uid = mAuth.getCurrentUser().getUid();
        reference = db.getReference().child("Tutor").child(uid);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               String image = snapshot.child("tutorimage").getValue(String.class);
               String name = snapshot.child("tutorname").getValue(String.class);
               String email = snapshot.child("tutoremail").getValue(String.class);
               String mobile = snapshot.child("tutormobile").getValue(String.class);
               String degree = snapshot.child("tutordegree").getValue(String.class);
               String subjects = snapshot.child("tutorsubjects").getValue(String.class);
               String experience = snapshot.child("tutorexperience").getValue(String.class);
               String city = snapshot.child("tutorcity").getValue(String.class);


               teachername.setText(name);
               teacheremail.setText(email);
               teachermobile.setText(mobile);
               teacherdegree.setText(degree);
               teachersubjects.setText(subjects);
               teacherexperience.setText(experience);
               teachercity.setText(city);
               Glide.with(TutorProfilePage.this)
                      .load(image)
                      .into(profileimage);

              // teacheraboutme.setText(image);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        reference.addListenerForSingleValueEvent(eventListener);

    }




}