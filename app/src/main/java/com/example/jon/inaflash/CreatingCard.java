package com.example.jon.inaflash;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.example.edejesus1097.innaflash.DatabaseOperations;

public class CreatingCard extends AppCompatActivity {
    public EditText content;
    public EditText definition;

    Context cxt = this;
    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //allows user input into two text fields
        //upon hitting add card, this function reads the information stored in the text boxes and places this information in the database for storage

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creating_card);
        Intent intent = getIntent();

      final  String Sub = intent.getStringExtra("Sub");
        final DatabaseOperations db = new  DatabaseOperations(cxt);
        content= (EditText) findViewById(R.id.content);

        add = (Button) findViewById(R.id.addCardButton);
        definition = (EditText) findViewById(R.id.definition);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String constr = content.getText().toString();
                String defstr = definition.getText().toString();

                db.putInformation(constr, defstr, Sub);
                content.setText("");
                definition.setText("");
                Toast.makeText(cxt, "Card Added", Toast.LENGTH_SHORT).show();
            }

        });
    }
}
