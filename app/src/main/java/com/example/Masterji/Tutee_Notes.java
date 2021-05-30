package com.example.Masterji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Tutee_Notes extends AppCompatActivity {
    private CardView class_1,class_2,class_3,class_4,class_5,class_6,class_7,class_8,class_9,class_10,class_11,class_12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee__notes);
        class_1 = findViewById(R.id.class_1);
        class_2 = findViewById(R.id.class_2);
        class_3 = findViewById(R.id.class_3);
        class_4 = findViewById(R.id.class_4);
        class_5 = findViewById(R.id.class_5);
        class_6 = findViewById(R.id.class_6);
        class_7 = findViewById(R.id.class_7);
        class_8 = findViewById(R.id.class_8);
        class_9 = findViewById(R.id.class_9);
        class_10 = findViewById(R.id.class_10);
        class_11 = findViewById(R.id.class_11);
        class_12 = findViewById(R.id.class_12);

        class_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutee_Notes.this, Tutee_Notes_View.class));
               // Toast.makeText(Tutee_Notes.this, "Class_1 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_2 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_3 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_4 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_5 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_6 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_7 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_8 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_9 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_10 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_11 clicked", Toast.LENGTH_SHORT).show();
            }
        });
        class_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Tutee_Notes.this, "Class_12 clicked", Toast.LENGTH_SHORT).show();
            }
        });



    }
}