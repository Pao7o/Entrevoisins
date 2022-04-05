package com.openclassrooms.entrevoisins.ui.neighbour_list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.design.animation.Positioning;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.ui.AppBarConfiguration;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.events.DeleteNeighbourEvent;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.greenrobot.eventbus.EventBus;

import java.text.ParsePosition;
import java.util.List;
import java.util.Locale;

public class ProfilNeighbourActivity extends AppCompatActivity {



    private ImageView mProfil_neighbour_toolbarImage;
    private TextView mName_textView;
    private TextView mAdress_textview;
    private TextView mPhoneNumber_textView ;
    private TextView mWebsite_textView ;
    private TextView mAboutme_Textview;
    private TextView mAboutmetext_Textview;
    private FloatingActionButton mAddFavoriteFloatingActionButton;
    private CollapsingToolbarLayout mProfilCollapsingToolbar;

    private boolean addFavorite;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil_neighbour);

        mProfil_neighbour_toolbarImage = findViewById(R.id.profil_neighbour_toolbarImage);
        mName_textView = findViewById(R.id.name_textView);
        mAdress_textview = findViewById(R.id.adress_textview);
        mPhoneNumber_textView  = findViewById(R.id.phoneNumber_textView);
        mWebsite_textView  = findViewById(R.id.website_textView);
        mAboutme_Textview = findViewById(R.id.aboutme_Textview);
        mAboutmetext_Textview = findViewById(R.id.aboutmetext_Textview);
        mAddFavoriteFloatingActionButton = findViewById(R.id.addFavorite_floatingactionbutton);
        mProfilCollapsingToolbar = findViewById(R.id.profil_neighbour_collapsingToolbar);

        addFavorite = false;

        Toolbar toolbar = (Toolbar) findViewById(R.id.profil_neighbour_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);





Intent openProfilActivity = getIntent();
       Neighbour neighbour = openProfilActivity.getParcelableExtra("NeighbourAttributes");

        mAddFavoriteFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starstate();

                if (addFavorite){
                    DI.getNeighbourApiService().addFav(neighbour);
                }



            }
        });


        //Username
        String name = neighbour.getName();
        mName_textView.setText(name);
        mProfilCollapsingToolbar.setTitle(name);

        //Avatar
        String avatarURL = neighbour.getAvatarUrl();
        Glide.with(this)
                .load(avatarURL)
                .into(mProfil_neighbour_toolbarImage);

        //Address
        String adress = neighbour.getAddress();
        mAdress_textview.setText(adress);

        //Website
        mWebsite_textView.setText("www.facebook.com/"+neighbour.getName().toLowerCase(Locale.ROOT));

        //Phone number
        String phoneNumber = neighbour.getPhoneNumber();
        mPhoneNumber_textView.setText(phoneNumber);

        //AboutMe
        String aboutMe = neighbour.getAboutMe();
        mAboutmetext_Textview.setText(aboutMe);



        }














    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()== android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



    public void starstate(){
        if(!addFavorite){
            mAddFavoriteFloatingActionButton.setImageResource(R.drawable.ic_star_white_24dp);
            addFavorite = true;

        }else if(addFavorite){
            mAddFavoriteFloatingActionButton.setImageResource(R.drawable.ic_star_border_white_24dp);
            addFavorite = false;
        }

    }




}