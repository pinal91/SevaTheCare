package com.pinal.credencys.appintroexample;

import android.app.ProgressDialog;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ChildList extends AppCompatActivity {

    //  ListView lv;
    public EditText edtSearch;
    RecyclerView mRVFishPrice;
    public SearchView search;
    Adapter an;
    Json js;
    String search_name;
    ArrayList<HashMap<String,String>>  data;
    ArrayList<HashMap<String,String>>  data1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_list);

        mRVFishPrice = (RecyclerView)findViewById(R.id.fishPriceList);
        edtSearch=(EditText)findViewById(R.id.edtSearch);

      //  new get().execute();

        data=new ArrayList<HashMap<String, String>>();
        data1=new ArrayList<HashMap<String, String>>();



        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


                if (edtSearch.getText().toString().length() == 0)
                {

                    new get().execute();
                }

                else {
                    search_name=edtSearch.getText().toString();

                    new search().execute();
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }



    class get extends AsyncTask<Void,Void,Void>{


        ProgressDialog pd;
        String name,area,type,bed,bath,rooms,country,u_id;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
data.clear();
            pd=new ProgressDialog(ChildList.this);
            pd.setMessage("getting data");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();



        }

        @Override
        protected Void doInBackground(Void... params) {


            String s = "http://pinal3291.site88.net/childlist.php";

            Log.d("dataaaaaaa",s.toString());

            Json js=new Json();

            // Log.d("Async completed", "Done");
            // Log.d("database", "product comes" + s);

            String resp=js.display1(s);
            System.out.println("String response" + resp);

            if(resp!= null){
                try {

                    JSONObject  jobj=new JSONObject(resp);
                    JSONArray jarray=jobj.getJSONArray("child_list");

                    for (int i=0;i<jarray.length();i++){

                        JSONObject jo=jarray.getJSONObject(i);

                        u_id=jo.getString("u_id");
                        name = jo.getString("name");
                        area = jo.getString("fathername");
                        type = jo.getString("std");
                        bed = jo.getString("income");
                        bath = jo.getString("village");
                        rooms = jo.getString("school");
                        country = jo.getString("mobile");

                        Log.d("object json:", jo.toString());
                        HashMap<String,String> map=new HashMap<String, String>();

                        map.put("u_id",u_id);
                        map.put("name",name);
                        map.put("area",area);
                        map.put("type",type);
                        map.put("bed",bed);
                        map.put("bath",bath);
                        map.put("rooms",rooms);
                        map.put("country",country);

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

            if (an != null) // it works second time and later
                an.notifyDataSetChanged();
            else { // it works first time

                an = new Adapter(ChildList.this, data);
                Log.d("d", "d");
                mRVFishPrice.setAdapter(an);
                mRVFishPrice.setLayoutManager(new LinearLayoutManager(ChildList.this));
            }





        }


    }

    class search extends AsyncTask<Void,Void,Void> {


        ProgressDialog pd;
        String name, area, type, bed, bath, rooms, country, u_id;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            data1.clear();
            pd = new ProgressDialog(ChildList.this);
            pd.setMessage("getting data");
            pd.setIndeterminate(false);
            pd.setCancelable(false);
            pd.show();


        }

        @Override
        protected Void doInBackground(Void... params) {


            String s = "http://pinal3291.site88.net/search.php?search_name="+search_name;

            Log.d("dataaaaaaa", s.toString());

            Json js = new Json();

            // Log.d("Async completed", "Done");
            // Log.d("database", "product comes" + s);

            String resp = js.display1(s);
            System.out.println("String response" + resp);

            if (resp != null) {
                try {

                    JSONObject jobj = new JSONObject(resp);
                    JSONArray jarray = jobj.getJSONArray("child_list");

                    for (int i = 0; i < jarray.length(); i++) {

                        JSONObject jo = jarray.getJSONObject(i);

                        u_id = jo.getString("u_id");
                        name = jo.getString("name");
                        area = jo.getString("fathername");
                        type = jo.getString("std");
                        bed = jo.getString("income");
                        bath = jo.getString("village");
                        rooms = jo.getString("school");
                        country = jo.getString("mobile");

                        Log.d("object json:", jo.toString());
                        HashMap<String, String> map1 = new HashMap<String, String>();

                        map1.put("u_id", u_id);
                        map1.put("name", name);
                        map1.put("area", area);
                        map1.put("type", type);
                        map1.put("bed", bed);
                        map1.put("bath", bath);
                        map1.put("rooms", rooms);
                        map1.put("country", country);

                        data1.add(map1);

                        Log.d("map value ", String.valueOf(map1));
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

            if (pd.isShowing()) {
                pd.dismiss();

            }
             Adapter2 an1 = new Adapter2(ChildList.this, data1);
            Log.d("d", "d");

            mRVFishPrice.setAdapter(an1);
            mRVFishPrice.setHasFixedSize(true);
            mRVFishPrice.setLayoutManager(new LinearLayoutManager(ChildList.this));
            an1.notifyDataSetChanged();

        }
    }

}

