package com.pinal.credencys.appintroexample;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Detail extends AppCompatActivity {

    String id;
    Json js;
    TextView t1,t2,t3,t4,t5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t5=(TextView)findViewById(R.id.t5);

        Intent i=getIntent();
        id=i.getStringExtra("u_id");


        Toast.makeText(getApplicationContext(),id,Toast.LENGTH_SHORT).show();

        new get().execute();
    }



    class get extends AsyncTask<Void,Void,Void> {


        ProgressDialog pd;
        String name,village,email,address,mobile;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd=new ProgressDialog(Detail.this);
            pd.setMessage("getting data");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... params) {


            String s = "http://pinal3291.site88.net/detailuser.php?id="+id;

            Log.d("dataaaaaaa",s.toString());

            Json js=new Json();

            // Log.d("Async completed", "Done");
            // Log.d("database", "product comes" + s);

            String resp=js.display1(s);
            System.out.println("String response" + resp);

            if(resp!= null){
                try {

                    JSONObject jobj=new JSONObject(resp);
                    JSONArray jarray=jobj.getJSONArray("book_list");




                    for (int i=0;i<jarray.length();i++){

                        JSONObject jo=jarray.getJSONObject(i);


                        name = jo.getString("name");
                        village = jo.getString("village");
                        email = jo.getString("email");
                        address = jo.getString("address");
                        mobile = jo.getString("mobile");


                        Log.d("object json:", jo.toString());

                                          }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            if(pd.isShowing()){
                pd.dismiss();

            }

            t1.setText(name);
            t2.setText(village);
            t3.setText(email);
            t4.setText(address);
            t5.setText(mobile);
        }


    }

}
