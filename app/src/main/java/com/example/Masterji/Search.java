package com.example.Masterji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Search extends AppCompatActivity {
    EditText searchbox;
    Button searchtutorbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchbox = findViewById(R.id.searchbox);
        searchtutorbtn = findViewById(R.id.searchtutorbtn);

        searchtutorbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Search.this, "search..", Toast.LENGTH_SHORT).show();
                searchbox.setText("");
            }
        });
    }
}