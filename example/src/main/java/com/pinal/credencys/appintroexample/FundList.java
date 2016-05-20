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

public class FundList extends AppCompatActivity {

    //  ListView lv;
    RecyclerView mRVFishPrice;
    Json js;
    ArrayList<HashMap<String,String>>  data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fund_list);



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

            pd=new ProgressDialog(FundList.this);
            pd.setMessage("getting data");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... params) {


            String s = "http://pinal3291.site88.net/ViewFund.php";

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


                        name = jo.getString("name");
                        area = jo.getString("amount");
                        type = jo.getString("date");
                        bed = jo.getString("village");
                        bath = jo.getString("mobile");


                        Log.d("object json:", jo.toString());
                        HashMap<String,String> map=new HashMap<String, String>();

                        map.put("name",name);
                        map.put("area",area);
                        map.put("type",type);
                        map.put("bed",bed);
                        map.put("bath",bath);

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
            FundAdapter an = new FundAdapter(FundList.this,data);
            Log.d("d", "d");

            mRVFishPrice.setAdapter(an);
            mRVFishPrice.setLayoutManager(new LinearLayoutManager(FundList.this));




        }


    }

}

