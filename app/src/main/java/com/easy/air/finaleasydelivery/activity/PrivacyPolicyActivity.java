package com.easy.air.finaleasydelivery.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.easy.air.finaleasydelivery.R;

import mehdi.sakout.aboutpage.AboutPage;

public class PrivacyPolicyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setDescription("All rights reserved. Copyright © EasyDelivery 2018." )
                .setImage(R.drawable.ic_ez)
                .addGroup("Connect with us")
                .addEmail("easydelivery@gmail.com")
                .addWebsite("http://easydelivery.kz/")
                .create();
        setContentView(aboutPage);
    }
}
