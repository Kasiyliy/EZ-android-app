package com.easy.air.finaleasydelivery.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.easy.air.finaleasydelivery.R;
import com.easy.air.finaleasydelivery.model.Blogzone;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SeparatePostActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference reference;

    private TextView postTitle;
    private TextView postDescription;

    private TextView postFrom;
    private TextView postWhere;
    private TextView postPrice;

    private ImageView postImage;

    private Button backButton;
    private Button callButton;

    private Blogzone blogzone;
    private static String phoneNum= "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_separate_post);
        String key = (String) getIntent().getExtras().get("key");

        postTitle = findViewById(R.id.postTitle);
        postDescription = findViewById(R.id.postDescription);
        postImage = findViewById(R.id.postImage);
        postFrom = findViewById(R.id.postFrom);
        postWhere = findViewById(R.id.postWhere);
        postPrice = findViewById(R.id.postPrice);

        callButton = findViewById(R.id.callBtn);
        backButton = findViewById(R.id.backBtn);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Blogzone/" + key);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                blogzone = dataSnapshot.getValue(Blogzone.class);
                blogzone.setKey(dataSnapshot.getKey());
                postTitle.setText("Title:" + blogzone.getTitle());
                postDescription.setText("Description: " +blogzone.getDesc());
                postPrice.setText("Price: " + blogzone.getPrice() + " tg");

                postFrom.setText("From: " +blogzone.getWhere());
                postWhere.setText("To: " +  blogzone.getFrom());

                phoneNum = blogzone.getPhoneNum();
                Glide.with(getApplicationContext()).load(blogzone.getImageUrl()).into(postImage);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(SeparatePostActivity.this , databaseError.getMessage() , Toast.LENGTH_LONG);
            }
        });


        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phoneNum));
                if ( ContextCompat.checkSelfPermission( getApplicationContext(), Manifest.permission.CALL_PHONE ) == PackageManager.PERMISSION_GRANTED ) {
                    startActivity(callIntent);
                }
            }
        });
    }

}
