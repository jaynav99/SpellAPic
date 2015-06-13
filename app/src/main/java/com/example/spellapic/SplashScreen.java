package com.example.spellapic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashScreen extends Activity {
	private long ms=0;
	private long splashTime=2000;
	private boolean splashActive = true;
	
	private boolean paused=false;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread mythread = new Thread() {
        	public void run() {
        		try {
        			while (splashActive && ms < splashTime) {
        				if(!paused)
        					ms=ms+100;
        				sleep(100);
        			}
        		} catch(Exception e) {}
        		finally {
        			finish();
        			Intent intent = new Intent(SplashScreen.this, LevelSelector.class);
            		startActivity(intent);
        	       
        		}
            	}
        };
        mythread.start(); 
    	}
   
    }