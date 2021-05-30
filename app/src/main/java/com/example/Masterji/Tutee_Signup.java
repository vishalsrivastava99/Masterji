package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentProviderClient;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
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

public class
Tutee_Signup extends AppCompatActivity {

    private EditText name,email,mobile,standard,college,address,password,confirmpassword;
    private Button Tuteesignupbtn;
    TextView gototuteesignin;
    private ImageView Tuteeimage;
    private String Tuteename,Tuteeemail,Tuteemobile,Tuteestandard,Tuteecollege,Tuteeaddress,Tuteepassword,Tuteeconfirmpassword,downloadurl="";
    private DatabaseReference reference;
    private StorageReference storageReference;
    private FirebaseAuth mAuth;
    private final int REQ = 1;
    private Bitmap bitmap;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee__signup);

        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Tutee");
        storageReference = FirebaseStorage.getInstance().getReference();
        Tuteeimage = findViewById(R.id.Tuteeimage);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        standard = findViewById(R.id.standard);
        college = findViewById(R.id.college);
        address = findViewById(R.id.address);
        password = findViewById(R.id.password);
        confirmpassword = findViewById(R.id.confirmpassword);
        Tuteesignupbtn = findViewById(R.id.Tuteesignupbtn);
        gototuteesignin = findViewById(R.id.gototuteesignin);
        pd = new ProgressDialog(this);

        Tuteeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opergallery();
            }
        });

        Tuteesignupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkvalidation();
            }
        });

        gototuteesignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutee_Signup.this, Tutee_Login.class));
                finish();
            }
        });

    }



    private void opergallery() {
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
            Tuteeimage.setImageBitmap(bitmap);
        }
    }

    private void checkvalidation() {

       Tuteename = name.getText().toString().trim();
       Tuteeemail = email.getText().toString().trim();
       Tuteemobile = mobile.getText().toString().trim();
       Tuteestandard = standard.getText().toString().trim();
       Tuteecollege = college.getText().toString().trim();
       Tuteeaddress = address.getText().toString().trim();
       Tuteepassword = password.getText().toString().trim();
       Tuteeconfirmpassword = confirmpassword.getText().toString().trim();

       if(Tuteename.isEmpty()){
           name.setError("Empty");
           name.requestFocus();
       }else if(Tuteeemail.isEmpty()){
           email.setError("Empty");
           email.requestFocus();
       }else if(Tuteemobile.isEmpty()){
           mobile.setError("Empty");
           mobile.requestFocus();
       }else if(Tuteestandard.isEmpty()){
           standard.setError("Empty");
           standard.requestFocus();
       }else if(Tuteecollege.isEmpty()){
           college.setError("Emppty");
           college.requestFocus();
       }else if(Tuteeaddress.isEmpty() ){
           address.setError("Empty");
           address.requestFocus();
       }else if(Tuteepassword.isEmpty()) {
            password.setError("Empty");
            password.requestFocus();
       }else if(Tuteeconfirmpassword.isEmpty()){
           confirmpassword.setError("Empty");
           confirmpassword.requestFocus();
       }else if(!Tuteepassword.equals(Tuteeconfirmpassword)){
           password.setText("");
           confirmpassword.setText("");
           Toast.makeText(this, "Both Password must be same", Toast.LENGTH_SHORT).show();
           password.requestFocus();
       }else if(bitmap == null){
          // uploadData();
           Toast.makeText(this, "Please Select Image", Toast.LENGTH_SHORT).show();
       }
       else{
          uploadImage();
          // uploadData();
       }

    }

    private void uploadImage() {
        pd.setMessage("Uploading...");
        pd.show();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50, baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filepath;
        filepath = storageReference.child("Tutee").child(finalimg+"jpg");
        final UploadTask uploadTask = filepath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(Tutee_Signup.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful()) {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadurl= String.valueOf(uri);
                                    uploadData();
                                }
                            });
                        }
                    });
                }else{
                    pd.dismiss();
                    Toast.makeText(Tutee_Signup.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void uploadData() {
        mAuth.createUserWithEmailAndPassword(Tuteeemail,Tuteepassword)
                .addOnCompleteListener(Tutee_Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            TuteeData tuteeData = new TuteeData(Tuteename,Tuteeemail,Tuteemobile,Tuteestandard,Tuteecollege,Tuteeaddress,downloadurl);
                             FirebaseDatabase.getInstance().getReference("Tutee")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(tuteeData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                 @Override
                                 public void onComplete(@NonNull Task<Void> task) {
                                     if (task.isSuccessful()){
                                         pd.dismiss();
                                         Toast.makeText(Tutee_Signup.this, "Tutee Registered", Toast.LENGTH_SHORT).show();
                                     }else{
                                         pd.dismiss();
                                         Toast.makeText(Tutee_Signup.this, "Tutee Not Registered", Toast.LENGTH_SHORT).show();
                                     }

                                 }
                             });



                        }else{
                            pd.dismiss();
                            Toast.makeText(Tutee_Signup.this, "Tutee data not inserted", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }


}