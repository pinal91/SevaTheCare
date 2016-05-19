package com.pinal.credencys.appintroexample;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AddFund extends AppCompatActivity {

    EditText fname,date;
    String spn;
    RadioGroup rg;
    RadioButton r1;
    String p1,p2,p3,p4,p5,p6,p7,time,p8;

    Json js;
    private int myear,mmonth,mdate;
    ArrayList<String> worldlist;
    ArrayList<String> data1;
    PreferenceHelper pf;
    ArrayList<HashMap<String, String>> data;
    ProgressDialog pd;
    String id,name,village,mobile;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fund);

        id =pf.getPreferences(AddFund.this,"r_id");
        name =pf.getPreferences(AddFund.this,"r_name");
        village =pf.getPreferences(AddFund.this,"r_address");
        mobile =pf.getPreferences(AddFund.this,"r_num");


        fname=(EditText)findViewById(R.id.edtFname);
        date=(EditText)findViewById(R.id.edtDate);






        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                final Calendar c = Calendar.getInstance();

                mmonth = c.get(Calendar.MONTH);
                mdate = c.get(Calendar.DAY_OF_MONTH);
                myear = c.get(Calendar.YEAR);

                DatePickerDialog dp = new DatePickerDialog(AddFund.this, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        // TODO Auto-generated method stub

                        date.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
                    }
                }, myear, mmonth, mdate);

                dp.show();

            }

        });


        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p1 = fname.getText().toString();

                p7=date.getText().toString();


                long date = System.currentTimeMillis();

                SimpleDateFormat sdf = new SimpleDateFormat(" h:mm a");
                time = sdf.format(date);
                Toast.makeText(getApplication(), time,Toast.LENGTH_LONG).show();


                if(p1.equals("")) {

                    fname.setError("Enter Amount");
                }

                else {

                    Insert in = new Insert();
                    in.execute();

                }
            }
        });
    }

    class Insert extends AsyncTask<Void, Void, Void> {


        String s = "http://pinal3291.site88.net/fund.php?amount=" + Uri.encode(p1) + "&date=" + Uri.encode(p7)+"&u_id=" + Uri.encode(id)+ "&village=" + Uri.encode(village)+ "&mobile=" + Uri.encode(mobile)+ "&name=" + Uri.encode(name);



        int success;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(AddFund.this);
            pd.setMessage("please wait");
            pd.setCancelable(true);
            pd.setIndeterminate(false);
            pd.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            js = new Json();


            Log.d("Async task", "Done");

            String json = js.getdata(s);
            Log.d("Login attempt", js.toString());

            System.out.println(json);
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if (pd.isShowing()) {
                pd.dismiss();

                Toast.makeText(getApplicationContext(), "Inserterd Sucess", Toast.LENGTH_LONG).show();

                Intent i3=new Intent(getApplicationContext(),AddFund.class);
                startActivity(i3);
                finish();
            }
        }
    }




}