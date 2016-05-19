package com.pinal.credencys.appintroexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Login extends AppCompatActivity {

    TextView reg;
    Button btnSubmit;
    String resp;
    EditText uname,pass;
    String s1;
    JSONObject jobj;
    ArrayList<HashMap<String, String>> data;
    String p1,p2;
    String sname,sid,semail,saddress,slang,slat,snum,surl;
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";
    int flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        uname=(EditText)findViewById(R.id.uname);
        pass=(EditText)findViewById(R.id.pass);
        reg=(TextView)findViewById(R.id.reg);
        btnSubmit=(Button)findViewById(R.id.btnSubmit);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(Login.this,Registration.class);
                startActivity(i);
                finish();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                p1=uname.getText().toString().trim();
                p2=pass.getText().toString().trim();

                if(p1.equals("")){

                    uname.setError("Fill Email");
                }
                else if (p2.equals("")){
                    pass.setError("Fill Password");
                }
                else {
                    login l = new login();
                    l.execute();

                }
            }
        });
    }

    class login extends AsyncTask<Void, Void, String> {
        int success;

        String s = "http://pinal3291.site88.net/login.php?email=" + Uri.encode(p1) + "&pass=" + Uri.encode(p2);

        // String s = "http://10.0.2.2/restaurant/login.php?uname=" + Uri.encode(uname.getText().toString().trim()) + "&pass=" + Uri.encode(pass.getText().toString().trim());
        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(Login.this);
            pd.setMessage("please wait");
            pd.setCancelable(true);
            pd.setIndeterminate(false);
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            Json js = new Json();

            Log.d("Async completed", "Done");
            //  Log.d("database", "product comes"+ s);

            resp = js.display1(s);
            System.out.println("String response" + resp);

            JSONObject jobjResponse;
            try {
                jobjResponse = new JSONObject(resp);
                data = new ArrayList<HashMap<String,String>>();
                JSONArray jArrayProdList = new JSONArray();
                jArrayProdList = jobjResponse.getJSONArray("user_list");
                for (int i = 0; i < jArrayProdList.length(); i++) {
                    HashMap<String,String> map = new HashMap<String, String>();
                    jobj = jArrayProdList.getJSONObject(i);

                    sname = jobj.getString("name");
                    sid = jobj.getString("id");
                    semail = jobj.getString("email");
                    saddress = jobj.getString("village");

                    snum=jobj.getString("mobile");



                    map.put( "sname", sname);
                    map.put( "sid", sid);
                    map.put( "semail", semail);
                    map.put( "saddress", saddress);

                    map.put( "snum", snum);





                    data.add(map);
                }
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);

            if (pd.isShowing()) {
                pd.dismiss();
                  //Toast.makeText(getApplicationContext(),resp,Toast.LENGTH_LONG).show();
                if (resp.equals("[]") || (resp.equals("empty"))){

                    Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_LONG).show();

                }else {
                    PreferenceHelper.SavePreferences(Login.this,"r_id",sid);
                    PreferenceHelper.SavePreferences(Login.this,"r_name",sname);
                    PreferenceHelper.SavePreferences(Login.this,"r_email",semail);
                    PreferenceHelper.SavePreferences(Login.this,"r_address",saddress);

                    PreferenceHelper.SavePreferences(Login.this,"r_num",snum);



                    Intent i = new Intent(Login.this, AddChild.class);
                    startActivity(i);
                }
            }
        }
    }



}