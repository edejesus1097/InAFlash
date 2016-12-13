package com.example.jon.inaflash;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.SubMenuBuilder;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class CreateOrEdit extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //upon being created this function will set the textbox at the stop to the subject previously selected
        //it will also take you to new activities depending on which button is selected
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_or_edit);
        final Context cxt = this;
        final TextView test;
        test = (TextView) findViewById(R.id.texttest);
        final Button create = (Button) findViewById(R.id.create);

        Intent intent = getIntent();

        final Button study = (Button) findViewById(R.id.study);
        final String Sub = intent.getStringExtra("Sub");
        test.setText(Sub);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(Sub);



        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cxt, CreatingCard.class);
                intent.putExtra("Sub", Sub);

                startActivity(intent);
            }
        });

    //sets what happens when the buttons are clicked
   Button edit= (Button) findViewById(R.id.edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cxt, EditingOrMakingDeck.class);
                intent.putExtra("Sub", Sub);

                startActivity(intent);
            }
        });

        study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cxt, ToStudy.class);
                intent.putExtra("Sub", Sub);

                startActivity(intent);
            }
        });

}}

