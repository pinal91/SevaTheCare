package com.pinal.credencys.appintroexample;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Pinal on 2/22/2016.
 */
public class Json {


    static int exp;
    static String stream = null;

    Json(){


    }

    public String getdata(String urlnew){


        Log.d("url", urlnew);
        try{

            URL url = new URL(urlnew);

            HttpURLConnection httpsURLConnection = (HttpURLConnection)url.openConnection();
            Log.d("Connection","done");
            httpsURLConnection.setRequestMethod("GET");
            Log.d("method", "Connection method done");
            exp=httpsURLConnection.getResponseCode();
            System.out.print(exp);
            if (httpsURLConnection.getResponseCode()==200){
                Log.d("code","connection code done");

                InputStream in = new BufferedInputStream(httpsURLConnection.getInputStream());
                Log.d("in","input stream done");
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                Log.d("bufferreader","buffer stream done");
                StringBuilder sb = new StringBuilder();

                String line;

                while ((line=br.readLine())!=null){
                    sb.append(line);
                    Log.d("sb",sb.toString());

                }

                stream = sb.toString();

                httpsURLConnection.disconnect();


            }

            else{
                Log.d("else", "cant connect ");
                httpsURLConnection.getResponseCode();

            }



        }
        catch (Exception e){


        }

        finally {

        }


        return  stream;
    }
    public String display1(String urlnew){


        Log.d("url", urlnew);
        try{

            URL url = new URL(urlnew);

            HttpURLConnection httpsURLConnection = (HttpURLConnection)url.openConnection();
            Log.d("Connection","done");
            httpsURLConnection.setRequestMethod("GET");
            Log.d("method", "Connection method done");
            Log.d("url",httpsURLConnection.toString());

            if (httpsURLConnection.getResponseCode()==200){
                Log.d("code","connection code done");

                InputStream in = new BufferedInputStream(httpsURLConnection.getInputStream());
                Log.d("in","input stream done");
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                Log.d("bufferreader","buffer stream done");
                StringBuilder sb = new StringBuilder();

                String line;

                while ((line=br.readLine())!=null){
                    sb.append(line);
                    Log.d("sb",sb.toString());

                }

                stream = sb.toString();

                httpsURLConnection.disconnect();


            }

            else{
                Log.d("else", "cant connect ");


            }



        }
        catch (Exception e){


        }

        finally {

        }


        return  stream;
    }
}
