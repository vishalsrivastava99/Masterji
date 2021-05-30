package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Tutee_Profilepage extends AppCompatActivity {
    private final int REQ = 1 ;
    TextView tutee_profilename;
    EditText tuteename,tuteeemail,tuteemobile,tuteestandard,tuteecollege,tuteeaddress;
    DatabaseReference ref1,ref2,ref3;
    FirebaseDatabase db;
    StorageReference storageReference;
    FirebaseAuth mAuth;
    Context context;
    //variables to update profile data
    ImageView tutee_profileimage;
    String contact,degree,experience,aboutme,subjects,downloadurl="",name,email,city;
    Button updatebtn;
    Bitmap bitmap;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee__profilepage);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        tutee_profileimage = findViewById(R.id.tutee_profileimage);
        tutee_profilename = findViewById(R.id.tutee_profilename);
        tuteename = findViewById(R.id.tuteename);
        tuteeemail = findViewById(R.id.tuteeemail);
        tuteemobile = findViewById(R.id.tuteemobile);
        tuteestandard = findViewById(R.id.tuteestandard);
        tuteecollege = findViewById(R.id.tuteecollege);
        tuteeaddress = findViewById(R.id.tuteeaddress);
        updatebtn = findViewById(R.id.tutee_updatebtn);

        //Getting the data from signup to profile page
        FirebaseUser tutee_user = mAuth.getCurrentUser();
        String uid = mAuth.getCurrentUser().getUid();
        ref1 = db.getReference().child("Tutee").child(uid);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String image = snapshot.child("tuteeimage").getValue(String.class);
                String name = snapshot.child("tuteename").getValue(String.class);
                String email = snapshot.child("tuteeemail").getValue(String.class);
                String mobileno = snapshot.child("tuteemobile").getValue(String.class);
                String standard = snapshot.child("tuteestandard").getValue(String.class);
                String college = snapshot.child("tuteecollege").getValue(String.class);
                String address = snapshot.child("tuteeaddress").getValue(String.class);


                tutee_profilename.setText(name);
                tuteename.setText(name);
                tuteeemail.setText(email);
                tuteemobile.setText(mobileno);
                tuteestandard.setText(standard);
                tuteecollege.setText(college);
                tuteeaddress.setText(address);
                Glide.with(Tutee_Profilepage.this).load(image).into(tutee_profileimage);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref1.addListenerForSingleValueEvent(eventListener);

        


    }
}