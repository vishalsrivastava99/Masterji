package com.example.Masterji;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class Adapter_notes extends FirebaseRecyclerAdapter<notesmodel, Adapter_notes.myviewholder>
{

    public Adapter_notes(@NonNull FirebaseRecyclerOptions<notesmodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull notesmodel model) {

        holder.note_title.setText(model.getPdftitle());
        holder.pdf_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.pdf_image.getContext(), View_Pdf.class);
                intent.putExtra("pdfTitle", model.getPdftitle());
                intent.putExtra("pdfUrl", model.getPdfUrl());

                intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                holder.pdf_image.getContext().startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tutee_notes_singlerow,parent,false);
        return new myviewholder(view);
    }

    public class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView pdf_image;
        TextView note_title;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            pdf_image = itemView.findViewById(R.id.pdf_image);
            note_title = itemView.findViewById(R.id.note_title);


        }
    }

}
