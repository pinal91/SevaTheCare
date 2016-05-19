package com.pinal.credencys.appintroexample;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class Splash extends AppCompatActivity {
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        img=(ImageView) findViewById(R.id.imageView1);

        final Animation animation= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_anim);
        img.setAnimation(animation);
        animation.start();
// Toast.makeText(getApplicationContext(),"Please wait for 5 sec",Toast.LENGTH_LONG).show();
        Thread t=new Thread(){

            public void run(){
                try {
                    sleep(5000);
// Toast.makeText(getApplicationContext(),"Please wait for 5 sec",Toast.LENGTH_LONG).show();
                } catch (InterruptedException e) {
                    animation.cancel();
                    e.printStackTrace();
                }
                finally {
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(Splash.this);
                    if(!prefs.getBoolean("first_time", true))
                    {
                        SharedPreferences.Editor editor = prefs.edit();

                        editor.commit();
                        Intent i = new Intent(Splash.this, Login.class);
                        startActivity(i);
                        finish();
                    }
                    else
                    {
                        Intent i=new Intent(Splash.this,DefaultIntro.class);
                        startActivity(i);
                        finish();

                    }
                }
            }
        };
        t.start();

/* SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
if(!prefs.getBoolean("first_time", true))
{
SharedPreferences.Editor editor = prefs.edit();

editor.commit();
Intent i = new Intent(MainActivity.this, Login.class);
this.startActivity(i);
this.finish();
}
else
{
Intent i=new Intent(MainActivity.this,UserManual.class);
startActivity(i);

}*/
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }




}

