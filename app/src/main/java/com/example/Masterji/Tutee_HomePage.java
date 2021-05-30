package com.example.Masterji;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tutee_HomePage extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle togglebtn;
    ImageSlider slider;
    MaterialCardView myprofile,post,query,notes,search,faqs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutee__home_page);

        Toolbar toolbar = (Toolbar)findViewById(R.id.tuteetoolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout)findViewById(R.id.tutee_drawerlayout);
        navigationView = (NavigationView)findViewById(R.id.tutee_navigationview);
        slider = (ImageSlider)findViewById(R.id.slider);

        myprofile = (MaterialCardView)findViewById(R.id.myprofilebtn);
        post = (MaterialCardView)findViewById(R.id.postbtn);
        query = (MaterialCardView)findViewById(R.id.querybtn);
        notes = (MaterialCardView)findViewById(R.id.notesbtn);
        search = (MaterialCardView)findViewById(R.id.search);
        faqs = (MaterialCardView)findViewById(R.id.faqs);
        //toggle button
        togglebtn = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(togglebtn);
        togglebtn.syncState();


        //menu items onclick listner
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutee_HomePage.this,Tutee_Profilepage.class));
                //Toast.makeText(Tutee_HomePage.this, "Myprofile", Toast.LENGTH_SHORT).show();
            }
        });

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Tutee_HomePage.this,Tutee_Post.class));
                //Toast.makeText(Tutee_HomePage.this, "Post", Toast.LENGTH_SHORT).show();
            }
        });

        query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutee_HomePage.this, Suggestion.class));
                //Toast.makeText(Tutee_HomePage.this, "Query", Toast.LENGTH_SHORT).show();
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(Tutee_HomePage.this, "Notes", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Tutee_HomePage.this, Tutee_Notes.class));
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(Tutee_HomePage.this, "Search", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Tutee_HomePage.this, Search.class));

            }
        });
        faqs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Tutee_HomePage.this,FAQs.class));

            }
        });


        //onclick event on drawer menu item
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        //startActivity(new Intent(TutorHomePage.this, TutorHomePage.class));
                        Toast.makeText(Tutee_HomePage.this, "Home Selected", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.myprofile:
                        startActivity(new Intent(Tutee_HomePage.this, Tutee_Profilepage.class));
                        //Toast.makeText(Tutee_HomePage.this, "myprofile Selected", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.logout:
                        //Toast.makeText(Tutee_HomePage.this, "logout", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Tutee_HomePage.this, Tutee_Login.class));
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.share:
                        Toast.makeText(Tutee_HomePage.this, "share selected", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                }

                return true;
            }
        });

        //image slider
        final List<SlideModel> slider_images = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("Slider")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot data : snapshot.getChildren()){
                            slider_images.add(new SlideModel(data.child("url").getValue().toString(), data.child("title").getValue().toString(), ScaleTypes.FIT));
                        }
                        slider.setImageList(slider_images,ScaleTypes.FIT);

                        slider.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemSelected(int i) {
                                Toast.makeText(Tutee_HomePage.this, slider_images.get(i).getTitle().toString() , Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



    }


}