package com.teknesya.delisya.delisya;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    ActionBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Thread thread=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(   2000);
                    Intent intent=new Intent(SplashScreen.this,Login.class);
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        };
        thread.start();



    }
}