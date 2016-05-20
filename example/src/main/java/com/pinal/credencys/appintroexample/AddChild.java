package com.pinal.credencys.appintroexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddChild extends AppCompatActivity {


    Button btnAdd,btnView,btnCon,btnFund,btnMyFund;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_child);

        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnView=(Button)findViewById(R.id.btnView);
        btnCon=(Button)findViewById(R.id.btnCon);
        btnFund=(Button)findViewById(R.id.btnFund);
        btnMyFund=(Button)findViewById(R.id.btnMyFund);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(AddChild.this,ChildRegistration.class);
                startActivity(i);
            }
        });

        btnCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1=new Intent(AddChild.this,AddFund.class);
                startActivity(i1);
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i2=new Intent(AddChild.this,ChildList.class);
                startActivity(i2);
            }
        });

        btnFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3=new Intent(AddChild.this,FundList.class);
                startActivity(i3);
            }
        });

        btnMyFund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i4=new Intent(AddChild.this,MyFund.class);
                startActivity(i4);
            }
        });

    }
}
