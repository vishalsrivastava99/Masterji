package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Tutee_Post extends AppCompatActivity {

    ImageView profilepic;
    EditText post_name,post_standard,post_college,post_subjects,post_address,post_price,post_description;
    Button tuteepostbtn;
    FirebaseAuth mAuth;
    DatabaseReference ref1,ref2;
    FirebaseDatabase db;
    StorageReference storageReference;
    String p_name,p_standard,p_college,p_subject,p_address,p_fees,p_description;
    String imageurl;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee__post);

        profilepic = findViewById(R.id.profileimg);
        post_name =  findViewById(R.id.post_name);
        post_standard = findViewById(R.id.post_standard);
        post_college = findViewById(R.id.post_college);
        post_subjects = findViewById(R.id.post_subjects);
        post_address = findViewById(R.id.post_address);
        post_price = findViewById(R.id.post_price);
        post_description = findViewById(R.id.post_description);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("Tutee_post");
        ref2 = db.getReference().child("Post");
        pd = new ProgressDialog(this);
        tuteepostbtn = findViewById(R.id.tuteepostbtn);

        //getting data from firebase
        FirebaseUser tuteeuser = mAuth.getCurrentUser();
        String uid = mAuth.getCurrentUser().getUid();
        ref1 = db.getReference().child("Tutee").child(uid);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String image = snapshot.child("tuteeimage").getValue(String.class);
                String name = snapshot.child("tuteename").getValue(String.class);
                String standard = snapshot.child("tuteestandard").getValue(String.class);
                String college = snapshot.child("tuteecollege").getValue(String.class);
                String address = snapshot.child("tuteeaddress").getValue(String.class);

                imageurl = image;

                post_name.setText(name);
                post_standard.setText(standard);
                post_college.setText(college);
                post_address.setText(address);
                Glide.with(Tutee_Post.this).load(image).into(profilepic);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        ref1.addListenerForSingleValueEvent(eventListener);

        //setting up remaining fields
        tuteepostbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkvalidation();
            }
        });


    }

    private void checkvalidation() {
        p_name = post_name.getText().toString().trim();
        p_standard = post_standard.getText().toString().trim();
        p_college = post_college.getText().toString().trim();
        p_subject = post_subjects.getText().toString().trim();
        p_address = post_address.getText().toString().trim();
        p_fees = post_price.getText().toString().trim();
        p_description = post_description.getText().toString().trim();


        if(p_subject.isEmpty()){
            post_subjects.setError("Enter Subject");
            post_subjects.requestFocus();
        }else if(p_fees.isEmpty()){
            post_price.setError("Enter Fee Amount");
            post_price.requestFocus();
        }else{
            //Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
            uploadData();
        }




    }

    private void uploadData() {

        pd.setMessage("Posting...");
        pd.show();
        final String uniquekey = ref2.push().getKey();

        Tutee_post_data tutee_post_data = new Tutee_post_data(p_name,p_standard,p_college,p_subject,p_address,p_fees,p_description,imageurl,uniquekey);
        ref2.child(uniquekey).setValue(tutee_post_data).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                pd.dismiss();
                Toast.makeText(Tutee_Post.this, "Posted", Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                pd.dismiss();
                Toast.makeText(Tutee_Post.this, "Error While posting", Toast.LENGTH_SHORT).show();
            }
        });


    }
}

