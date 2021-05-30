package com.example.Masterji;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Tutee_Notes_View extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter_notes adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee__notes__view);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(Tutee_Notes_View.this,2));

        FirebaseRecyclerOptions<notesmodel> options =
                new FirebaseRecyclerOptions.Builder<notesmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Notes").child("class_1"), notesmodel.class)
                        .build();

        adapter = new Adapter_notes(options);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}