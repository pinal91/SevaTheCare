package com.pinal.credencys.appintroexample;

/**
 * Created by pinal on 16/5/16.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class MyFundAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String id;
    private Context context;
    private LayoutInflater inflater;
    ArrayList<HashMap<String,String>> data;
    HashMap<String,String> result = new HashMap<String,String>();

    public MyFundAdapter(Context context, ArrayList<HashMap<String,String>> list){
        this.context=context;
        inflater= LayoutInflater.from(context);
        this.data=list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.my_fund_list, parent,false);
        final MyHolder holder=new MyHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final MyHolder myHolder= (MyHolder) holder;
        result=data.get(position);
        myHolder.tv1.setText(result.get("area"));
        myHolder.tv2.setText(result.get("type"));

      /*  holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id=myHolder.tv9.getText().toString();
                Intent i=new Intent(context,Detail.class);
                i.putExtra("u_id",id);
                context.startActivity(i);
                Toast.makeText(context,id ,Toast.LENGTH_SHORT).show();
            }
        });*/

        /*if(result.get("bath").isEmpty()){
            myHolder.tv5.setVisibility(View.GONE);

        }
        else{
            myHolder.tv6.setVisibility(View.VISIBLE);
            myHolder.tv6.setText(result.get("rooms"));

        }

        if(result.get("country").isEmpty()){
            myHolder.tv7.setVisibility(View.GONE);

        }
        else{
            myHolder.tv7.setVisibility(View.VISIBLE);
            myHolder.tv7.setText(result.get("country"));

        }*/



    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder
    {

        RecyclerView recyclerView;
        TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv9;

        public MyHolder(View itemView) {
            super(itemView);
            tv1=(TextView) itemView.findViewById(R.id.tv1);
            tv2=(TextView) itemView.findViewById(R.id.tv2);
            tv3=(TextView)itemView.findViewById(R.id.tv3);
            tv4=(TextView)itemView.findViewById(R.id.tv4);
            tv5=(TextView)itemView.findViewById(R.id.tv5);
            tv6=(TextView)itemView.findViewById(R.id.tv6);
            tv7=(TextView)itemView.findViewById(R.id.tv7);
            tv9=(TextView)itemView.findViewById(R.id.tv9);


        }

    }

}