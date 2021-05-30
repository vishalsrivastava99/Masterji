package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tutee_Post_View extends AppCompatActivity {
    DatabaseReference dbref;
    EditText nameview,standardview,collegeview,subjectview,addressview,feeview,descriptionview;
    ImageView imageView;
    Button acceptbtn;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee__post__view);

        db = FirebaseDatabase.getInstance();
        nameview = findViewById(R.id.nameview);
        standardview = findViewById(R.id.standardview);
        collegeview = findViewById(R.id.collegeview);
        subjectview = findViewById(R.id.subjectview);
        addressview = findViewById(R.id.addressview);
        feeview = findViewById(R.id.feeview);
        descriptionview = findViewById(R.id.descriptionview);


    }
}