package com.pinal.credencys.appintroexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import com.pinal.credencys.appintro.AppIntro;
import com.pinal.credencys.appintroexample.slides.InputDemoSlide;

public final class DefaultIntro extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("first_time", false);
        editor.commit();
        addSlide(SampleSlide.newInstance(R.layout.intro));
        addSlide(SampleSlide.newInstance(R.layout.intro2));
        addSlide(SampleSlide.newInstance(R.layout.intro3));

        addSlide(new InputDemoSlide());
    }

    private void loadMainActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        loadMainActivity();
        Toast.makeText(getApplicationContext(), "Y do you skip ?", Toast.LENGTH_SHORT).show();
    }

    public void getStarted(View v){
        loadMainActivity();
    }


    @Override
    public void onDonePressed(Fragment currentFragment) {

        loadLoginActivity();
        super.onDonePressed(currentFragment);
    }

    private void loadLoginActivity() {

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        finish();
    }
}
