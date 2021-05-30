package com.example.Masterji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Landing_Page extends AppCompatActivity {

    private CardView teacherlogin,studentlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing__page);

        teacherlogin = findViewById(R.id.gototeacherlogin);
        studentlogin = findViewById(R.id.gotostudentlogin);


        teacherlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this,Student_Login.class));

            }
        });

        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Landing_Page.this, Tutee_Login.class));
            }
        });
    }
}