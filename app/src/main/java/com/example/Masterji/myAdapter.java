package com.example.Masterji;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import de.hdodenhof.circleimageview.CircleImageView;

public class myAdapter extends FirebaseRecyclerAdapter<model,myAdapter.myviewholder> {

    private Context context;


    public myAdapter(@NonNull FirebaseRecyclerOptions<model> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull model model) {
        holder.name.setText(model.getName());
        holder.college.setText(model.getCollege());
        holder.fees.setText(model.getFees());

        Glide.with(holder.img.getContext()).load(model.getImage()).into(holder.img);


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{
        CircleImageView img;
        TextView name,college,fees;

        //if you want to show standard declare in textview
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.studentpic);
            name = (TextView)itemView.findViewById(R.id.nametext);
            college = (TextView)itemView.findViewById(R.id.emailtext);
            fees = (TextView)itemView.findViewById(R.id.fees);

            //standard  = (TextView)itemView.findViewById(R.id.standardtext);
        }
    }
}
