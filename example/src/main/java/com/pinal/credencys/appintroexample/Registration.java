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
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Registration extends AppCompatActivity  implements LocationListener{

    EditText fname,lname,email,adress,password,repassword,date,number;
    Button btnSubmit;
    Spinner spnvillage;
    String spn;
    RadioGroup rg;
    RadioButton r1;
    String p1,p2,p3,p4,p5,p6,p7,time,p8;
    String lati,longi;
    String MobilePattern = "[0-9]{10}";
    Json js;
    private int myear,mmonth,mdate;
    ArrayList<String> worldlist;
    ArrayList<String> data1;

    ArrayList<HashMap<String, String>> data;
    LocationManager locationManager;
    String provider;
    ProgressDialog pd;
    String stryear_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        fname=(EditText)findViewById(R.id.edtFname);
        lname=(EditText)findViewById(R.id.edtLname);
        email=(EditText)findViewById(R.id.edtEmail);
        adress=(EditText)findViewById(R.id.edtAdress);
        password=(EditText)findViewById(R.id.edtPassword);
        repassword=(EditText)findViewById(R.id.edtCnfrmPassword);
        date=(EditText)findViewById(R.id.edtDate);
        number=(EditText)findViewById(R.id.edtPhn);
        spnvillage=(Spinner)findViewById(R.id.spnvillage);

        isInternetOn();

        new getresult().execute();

        date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                final Calendar c = Calendar.getInstance();

                mmonth = c.get(Calendar.MONTH);
                mdate = c.get(Calendar.DAY_OF_MONTH);
                myear = c.get(Calendar.YEAR);

                DatePickerDialog dp = new DatePickerDialog(Registration.this, new DatePickerDialog.OnDateSetListener() {

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
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Creating an empty criteria object
        Criteria criteria = new Criteria();

        // Getting the name of the provider that meets the criteria
        provider = locationManager.getBestProvider(criteria, false);

        if (provider != null && !provider.equals("")) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            Location location = locationManager.getLastKnownLocation(provider);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,2000,1,this);

            if (location != null)
                onLocationChanged(location);
            else
                Toast.makeText(getBaseContext(), "Location can't be retrieved", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }

        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                p1 = fname.getText().toString();
                p2 = lname.getText().toString();
                p3 = email.getText().toString();
                p4 = adress.getText().toString();
                p5 = password.getText().toString();
                p6=repassword.getText().toString();
                p7=date.getText().toString();

                spn=spnvillage.getSelectedItem().toString();

                long date = System.currentTimeMillis();

                SimpleDateFormat sdf = new SimpleDateFormat(" h:mm a");
                time = sdf.format(date);
                Toast.makeText(getApplication(), time,Toast.LENGTH_LONG).show();

                p8=number.getText().toString();
                if(p1.equals("")) {

                    fname.setError("Enter Your Name");
                }

                else if (!p3.contains("@")) {

                    email.setError("Enter Valid Email");
                    Toast.makeText(getApplicationContext(), "please enter valide email address", Toast.LENGTH_LONG).show();
                }
                else if (!p3.contains(".com")){

                    email.setError("Enter Valid Email");

                    Toast.makeText(getApplicationContext(), "please enter valide email address", Toast.LENGTH_LONG).show();


                }

                else if (p4.equals("")){

                    adress.setError("Enter Your Address");
                }

                else if (p5.isEmpty() || p5.length() < 4 ||p5.length() > 10){

                    password.setError("between 4 and 10 alphanumeric characters");
                }
                else if (!p5.equals(p6)) {

                    password.setError("Password not match");
                    repassword.setError("Password not match");
                    Toast.makeText(getApplicationContext(), "password doesn't match", Toast.LENGTH_LONG).show();
                }
                else if (!p8.matches(MobilePattern)){

                    number.setError("Enter Valid Number");
                }

                else {
                    isInternetOn();


                }
            }
        });
    }


    public final boolean isInternetOn() {

        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED ) {

            // if connected with internet

            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED  ) {

            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }

    class Insert extends AsyncTask<Void, Void, Void> {


      String s = "http://pinal3291.site88.net/insert.php?name=" + Uri.encode(p1) + "&village=" + Uri.encode(spn) + "&email=" + Uri.encode(p3)+"&address=" + Uri.encode(p4)+"&pass=" + Uri.encode(p5)+"&date="+Uri.encode(p7)+"&mobile="+Uri.encode(p8);



        int success;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Registration.this);
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

                Intent i3=new Intent(Registration.this,Login.class);
                startActivity(i3);
                finish();
            }
        }
    }

    public class getresult extends AsyncTask<Void, Void, Void>{

        String strresult_year;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Registration.this);
            pd.setTitle("Fetch Data...");
            pd.setMessage("Loading...");
            pd.setIndeterminate(false);
            pd.show();



        }

        @Override
        protected Void doInBackground(Void... params) {

            js = new Json();
            data = new ArrayList<HashMap<String,String>>();
            worldlist = new ArrayList<String>();
            data1 = new ArrayList<String>();
            String strresponce = js.getdata("http://pinal3291.site88.net/village.php");
            Log.d("database", "product comes"+ strresponce);
            JSONObject jobjResponse;
            try {
                jobjResponse = new JSONObject(strresponce);
                JSONArray jArrayProdList = new JSONArray();
                jArrayProdList = jobjResponse.getJSONArray("year_list");
                for (int i = 0; i < jArrayProdList.length(); i++) {
                    HashMap<String,String> map = new HashMap<String, String>();
                    JSONObject jobj = jArrayProdList.getJSONObject(i);
                    stryear_id = jobj.getString("id");
                    strresult_year = jobj.getString("village");

                    map.put("stryear_id", stryear_id);
                    map.put( "strresult_year", strresult_year );

                    Log.d("map", map.toString());
                    worldlist.add(jobj.getString("id"));
                    data1.add(jobj.getString("village"));
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);

            spnvillage.setAdapter(new ArrayAdapter<String>(getApplicationContext(),R.layout.spinner_layout,R.id.textView,data1));

             if(pd.isShowing()){
                pd.dismiss();

            }
        }
   }
       public void onLocationChanged(Location location) {

        lati= String.valueOf(location.getLatitude());
        longi=String.valueOf(location.getLongitude());

        Log.e("Pinal","location is set"+location.getLongitude()+location.getLatitude());
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}