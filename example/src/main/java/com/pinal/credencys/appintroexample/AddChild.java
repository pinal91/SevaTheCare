package com.pinal.credencys.appintroexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddChild extends AppCompatActivity {


    Button btnAdd,btnView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnView=(Button)findViewById(R.id.btnView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AddChild.this,ChildRegistration.class);
                startActivity(i);
            }
        });

    }
}
