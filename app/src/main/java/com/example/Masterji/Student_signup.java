package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Student_signup extends AppCompatActivity {
    private EditText name,email,mobile,degree,subjects,experience,city,password,confirmpassword;
    private Button signupbtn;
    private DatabaseReference reference;
    private StorageReference storageReference;
    private String tutorname,tutoremail,tutormobile,tutordegree,tutorsubjects,tutorexperience,tutorcity,tutorpwd,tutorcpwd,downloadurl="";
    private ImageView profileimage;
    private final int REQ = 1;
    private Bitmap bitmap;
    ProgressDialog pd;
    private FirebaseAuth mAuth;
    private TextView gotosignin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_signup);
        //getSupportActionBar().hide();

        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        degree = findViewById(R.id.degree);
        subjects = findViewById(R.id.subjects);
        experience = findViewById(R.id.experience);
        city = findViewById(R.id.city);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        signupbtn = findViewById(R.id.signupbtn);
        gotosignin = findViewById(R.id.gotosignin);
        reference = FirebaseDatabase.getInstance().getReference().child("Tutor");
        storageReference = FirebaseStorage.getInstance().getReference();
        profileimage = findViewById(R.id.profileimage);
        pd = new ProgressDialog(this);

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opengallery();
            }
        });

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkvalidation();
            }
        });

        gotosignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Student_signup.this,Student_Login.class));
            }
        });

    }

        private void opengallery() {
            Intent picimage = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(picimage,REQ);
        }

        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == REQ && resultCode == RESULT_OK){
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                profileimage.setImageBitmap(bitmap);
            }
        }

        private void checkvalidation() {

        tutorname = name.getText().toString().trim();
        tutoremail = email.getText().toString().trim();
        tutormobile = mobile.getText().toString().trim();
        tutordegree = degree.getText().toString().trim();
        tutorsubjects = subjects.getText().toString().trim();
        tutorexperience = experience.getText().toString().trim();
        tutorcity = city.getText().toString().trim();
        tutorpwd = password.getText().toString().trim();
        tutorcpwd = confirmpassword.getText().toString();

        if(tutorname.isEmpty()){
            name.setError("Empty");
            name.requestFocus();
        }else if(tutorname.length()<6){
            name.setError("Name is too short");
        }else if (tutoremail.isEmpty()){
            email.setError("Empty");
            email.requestFocus();
        }else if(tutoremail.length()<15){
            email.setError("Email is not valid");
            email.requestFocus();
        }else if(tutormobile.isEmpty()){
            mobile.setError("Empty");
            mobile.requestFocus();
        }else if(tutordegree.isEmpty()){
            degree.setError("Empty");
            degree.requestFocus();
        }else if(tutorsubjects.isEmpty()){
            subjects.setError("Empty");
            subjects.requestFocus();
        }else if(tutorexperience.isEmpty()){
            experience.setError("Empty");
            experience.requestFocus();
        }else if (tutorcity.isEmpty()){
            city.setError("Empty");
            city.requestFocus();
        }else if (tutorpwd.isEmpty()){
            password.setError("Empty");
            password.requestFocus();
        }else if(tutorcpwd.isEmpty()){
            confirmpassword.setError("Empty");
            confirmpassword.requestFocus();
        }else if (!tutorpwd.equals(tutorcpwd)){
            password.setText("");
            confirmpassword.setText("");
        }
        else if (bitmap == null){
            uploadData();
            //Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
        }
        else {
             uploadImage();
        }

    }

    private void uploadData()
    {
        tutoremail = email.getText().toString();
        tutorpwd = password.getText().toString();
        mAuth.createUserWithEmailAndPassword(tutoremail,tutorpwd)
                .addOnCompleteListener(Student_signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            TutorData tutorData =  new TutorData(tutorname,tutoremail,tutormobile,tutordegree,tutorsubjects,tutorexperience,tutorcity,downloadurl);
                            FirebaseDatabase.getInstance().getReference("Tutor")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(tutorData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(Student_signup.this, "Tutor Registered", Toast.LENGTH_SHORT).show();
                                        name.setText("");
                                        email.setText("");
                                        mobile.setText("");
                                        degree.setText("");
                                        subjects.setText("");
                                        experience.setText("");
                                        city.setText("");
                                        password.setText("");
                                        confirmpassword.setText("");
                                        startActivity(new Intent(Student_signup.this, Student_Login.class));

                                    }
                                    else {
                                        Toast.makeText(Student_signup.this, "Failed to Registered", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                        else {
                            Toast.makeText(Student_signup.this, "Tutor data not inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

//        dbref = reference.child(category);
//        final String uniquekey = reference.push().getKey();
//
//        TutorData tutordata = new TutorData(tutorname, tutoremail, tutorcity, tutorpwd, downloadurl, uniquekey);
//        reference.child(uniquekey).setValue(tutordata).addOnSuccessListener(new OnSuccessListener<Void>() {
//            @Override
//            public void onSuccess(Void aVoid) {
//                pd.dismiss();
//                Toast.makeText(Student_signup.this, "Teacher Added", Toast.LENGTH_SHORT).show();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                //pd.dismiss();
//                Toast.makeText(Student_signup.this, "Error while saving data.", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

        private void uploadImage() {
            pd.setMessage("Uploading...");
            pd.show();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,50, baos);
            byte[] finalimg = baos.toByteArray();
            final StorageReference filepath;
            filepath = storageReference.child("Tutor").child(finalimg+"jpg");
            final UploadTask uploadTask = filepath.putBytes(finalimg);
            uploadTask.addOnCompleteListener(Student_signup.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if(task.isSuccessful()) {
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        downloadurl = String.valueOf(uri);
                                        uploadData();
                                    }
                                });
                            }
                        });
                    }else{
                        pd.dismiss();
                        Toast.makeText(Student_signup.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }

    }