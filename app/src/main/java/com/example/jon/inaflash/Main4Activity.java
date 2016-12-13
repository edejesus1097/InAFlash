package com.example.jon.inaflash;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.edejesus1097.innaflash.BaseSub;
import com.example.edejesus1097.innaflash.DatabaseOperations;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //allows you to add subjects
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        final Context cxt = this;
        final EditText sub_in;
        Button add_in;
        sub_in = (EditText) findViewById(R.id.sub_in);
        add_in = (Button) findViewById(R.id.add_in);

        final BaseSub dob = new BaseSub(cxt);
        //once something is typed into the text field, the user hits add card, this function reads what is typed in the text field and stores this subject in the database
        add_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sub = sub_in.getText().toString();

                dob.addSub(sub);
                sub_in.setText("");
                Toast.makeText(cxt, "Subject added.", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
