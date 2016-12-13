package com.example.jon.inaflash;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edejesus1097.innaflash.DatabaseOperations;

public class EditingOrMakingDeck extends AppCompatActivity {
    Context cxt = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText con, def;
        final Button next, back, delete, done;
        //allows you to edit cards currently stored in the database
        //the delete button will erase the card and its information from the database
        //done will finalize the changes you type int
        //next or previous will cycle through cards in the database
        Intent intent = getIntent();

        final String Sub = intent.getStringExtra("Sub");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_editing_or_making_deck);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final DatabaseOperations db = new DatabaseOperations(cxt);
        con = (EditText) findViewById(R.id.con);
        def = (EditText) findViewById(R.id.def);
        final Cursor CR = db.findsubcard(Sub);
        //pop up displaying if there are no cards to be shown
        if (CR.getCount() == 0) {
            Toast.makeText(cxt, "No cards found", Toast.LENGTH_SHORT).show();
            finish();
        } else {

            //sets the text fields to display the information currently being stored by database for editing
            CR.moveToFirst();
            con.setText(CR.getString(0));
            def.setText(CR.getString(1));
            next = (Button) findViewById(R.id.next);

            done = (Button) findViewById(R.id.done);
            delete = (Button) findViewById(R.id.delete);
            //goes back to previous card
            back = (Button) findViewById(R.id.back);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String constr = con.getText().toString();
                    String defstr = def.getText().toString();

                    db.updateCard(db, constr, defstr, CR.getString(0), CR.getString(1));
                    if (CR.isFirst()) {
                        //displays if you're already at the first card and can't go to a previous one
                        Toast.makeText(cxt, "Already on First Card", Toast.LENGTH_SHORT).show();
                    } else {
                        CR.moveToPrevious();
                        con.setText(CR.getString(0));
                        def.setText(CR.getString(1));
                    }
                }
            });

            //function for when the next button is selected
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String constr = con.getText().toString();
                    String defstr = def.getText().toString();


                    db.updateCard(db, constr, defstr, CR.getString(0), CR.getString(1));
                    //will display if we can't go to a next card
                    if (CR.isLast())
                        Toast.makeText(cxt, "On Last Card already", Toast.LENGTH_SHORT).show();
                    else {
                        CR.moveToNext();
                        con.setText(CR.getString(0));
                        def.setText(CR.getString(1));
                    }


                }
            });
            //reads the changes to the card and stores new info in database, overwriting previous information
            done.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String constr = con.getText().toString();
                    String defstr = def.getText().toString();


                    db.updateCard(db, constr, defstr, CR.getString(0), CR.getString(1));

                    Toast.makeText(cxt, "Deck updated.", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });


        //function to delete card from database
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteCard(db, CR.getString(0), CR.getString(1), Sub);
                Cursor CV = db.findsubcard(Sub);
                        if (CV.getCount()==0)
                        {
                            Toast.makeText(cxt, "No cards left", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(EditingOrMakingDeck.this, CreateOrEdit.class));
                        }
                Intent intent = new Intent(cxt, EditingOrMakingDeck.class);
                intent.putExtra("Sub", Sub);

                startActivity(intent);
                if (CR.isBeforeFirst()) {
                    CR.moveToFirst();
                }
                if (CR.isAfterLast()) {
                    CR.moveToLast();
                }
                if (CR.getCount() == 0) {
                    CR.close();
                    Toast.makeText(cxt, "No Cards remaining.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                con.setText(CR.getString(0));
                def.setText(CR.getString(1));
            }
        });
    }
}}



