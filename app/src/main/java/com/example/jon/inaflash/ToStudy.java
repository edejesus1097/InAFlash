package com.example.jon.inaflash;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.edejesus1097.innaflash.DatabaseOperations;

public class ToStudy extends AppCompatActivity {

    Context cxt = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_to_study);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button next = (Button) findViewById(R.id.next_study);
        final Button previous = (Button) findViewById(R.id.previous_study);
        final Button hide = (Button) findViewById(R.id.show);
        final TextView display_concept = (TextView) findViewById(R.id.concept);
        final TextView display_definition = (TextView) findViewById(R.id.definition);

        Intent intent = getIntent();
        final String Sub = intent.getStringExtra("Sub");
        final DatabaseOperations db = new  DatabaseOperations(cxt);
        final Cursor CR = db.findsubcard(Sub);

        //prints errror message if there are no cards in the deck
        if (CR.getCount() == 0)
        {
            Toast.makeText(cxt, "No cards found", Toast.LENGTH_SHORT).show();
            finish();
        }
        //grabs the concept and definition in the database and displays them in text boxes for the user, concept is hidden until user selects the show button and then it is displayed
        else{
            CR.moveToFirst();
            display_concept.setText(CR.getString(0));
            display_definition.setText("");

            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CR.isLast())
                        //next.setVisibility(View.GONE);
                        Toast.makeText(cxt, "On Last Card already", Toast.LENGTH_SHORT).show();
                    else {
                        CR.moveToNext();
                        display_concept.setText(CR.getString(0));
                        display_definition.setText("");
                        display_definition.setVisibility(0);
                    }

                }
            });

            previous.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (CR.isFirst()) {
                        Toast.makeText(cxt, "Already on First Card", Toast.LENGTH_SHORT).show();
                        //previous.setVisibility(View.GONE);
                    } else {
                        CR.moveToPrevious();
                        display_concept.setText(CR.getString(0));
                        display_definition.setText("");
                        display_definition.setVisibility(0);
                    }
                }
            });

            hide.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    display_definition.setText(CR.getString(1));



                }
            });








        }














    }

}
