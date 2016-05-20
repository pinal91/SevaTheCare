package com.pinal.credencys.appintroexample;

import android.app.ProgressDialog;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyFund extends AppCompatActivity {

    //  ListView lv;
    RecyclerView mRVFishPrice;
    Json js;
    PreferenceHelper pf;
    String id;
    ArrayList<HashMap<String,String>>  data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fund);

        id =pf.getPreferences(MyFund.this,"r_id");


        mRVFishPrice = (RecyclerView)findViewById(R.id.fishPriceList);

        data=new ArrayList<HashMap<String, String>>();

        get g=new get();
        g.execute();
    }


    class get extends AsyncTask<Void,Void,Void>{


        ProgressDialog pd;
        String name,area,type,bed,bath,rooms,country,u_id;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            pd=new ProgressDialog(MyFund.this);
            pd.setMessage("getting data");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... params) {


            String s = "http://pinal3291.site88.net/MyFund.php?id="+id;

            Log.d("dataaaaaaa",s.toString());

            Json js=new Json();

            // Log.d("Async completed", "Done");
            // Log.d("database", "product comes" + s);

            String resp=js.display1(s);
            System.out.println("String response" + resp);

            if(resp!= null){
                try {

                    JSONObject  jobj=new JSONObject(resp);
                    JSONArray jarray=jobj.getJSONArray("fund_list");

                    for (int i=0;i<jarray.length();i++){

                        JSONObject jo=jarray.getJSONObject(i);



                        area = jo.getString("amount");
                        type = jo.getString("date");


                        Log.d("object json:", jo.toString());
                        HashMap<String,String> map=new HashMap<String, String>();

                        map.put("area",area);

                        map.put("type",type);


                        data.add(map);

                        Log.d("map value ", String.valueOf(map));
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
            MyFundAdapter an = new MyFundAdapter(MyFund.this,data);
            Log.d("d", "d");

            mRVFishPrice.setAdapter(an);
            mRVFishPrice.setLayoutManager(new LinearLayoutManager(MyFund.this));




        }


    }

}

