package com.example.Masterji;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentProviderClient;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Suggestion extends AppCompatActivity {
    EditText feedbacktext;
    Button submitbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        feedbacktext =  findViewById(R.id.feedbacktext);
        submitbtn =  findViewById(R.id.submitbtn);

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Suggestion.this, "Submitted", Toast.LENGTH_SHORT).show();
                feedbacktext.setText("");
            }
        });
    }
}