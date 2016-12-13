package com.example.jon.inaflash;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.example.edejesus1097.innaflash.BaseSub;
import com.example.edejesus1097.innaflash.DatabaseOperations;

import java.util.ArrayList;
import java.util.List;


public class selectSubjectToEdit extends AppCompatActivity {
    //allows you to select which subject you will be editing
    final Context cxt = this;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject_to_edit);

        final Button next, back, go;


        final BaseSub db = new BaseSub(cxt);

        //cycles through the subjects by grabbbing them from the database and displaying them in a text field
        final Cursor CR = db.getSub();
        if (CR.getCount() == 0) {
            Toast.makeText(cxt, "No subjects found", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            final TextView sub;
            sub = (TextView) findViewById(R.id.sub_check);

            CR.moveToFirst();
            sub.setText(CR.getString(0));
            next = (Button) findViewById(R.id.next_sub);

            go = (Button) findViewById(R.id.to_sub);


            back = (Button) findViewById(R.id.back_sub);
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String constr = sub.getText().toString();


                    if (CR.isFirst()) {
                        Toast.makeText(cxt, "Already on First Subjec", Toast.LENGTH_SHORT).show();
                    } else {
                        CR.moveToPrevious();
                        sub.setText(CR.getString(0));

                    }
                }
            });

            //cycles through to another subject
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (CR.isLast())
                        Toast.makeText(cxt, "On Last subject already", Toast.LENGTH_SHORT).show();
                    else {
                        CR.moveToNext();
                        sub.setText(CR.getString(0));

                    }


                }
            });
            //when go is selected this will set the cursor to the subject selected and move on to the next screen to edit/study with this subjects deck of note cards
            go.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String constr = sub.getText().toString();


                    Intent intent = new Intent(cxt, CreateOrEdit.class);
                    intent.putExtra("Sub", constr);
                    startActivity(intent);


                }
            });

            // Used to load the 'native-lib' library on application startup.
        }
    }
}