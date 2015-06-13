package com.example.spellapic;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

public class LevelSelector extends Activity {
	Button bLevel1, bLevel2, bLevel3;
	LinearLayout ll;
	Animation level1Animation;
	Animation vibrator;
	ToggleButton tb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.levelselector);
		ll = (LinearLayout) findViewById(R.id.llSelector);
		tb = (ToggleButton) this.findViewById(R.id.toggleButton1);
		level1Animation = AnimationUtils.loadAnimation(this, R.anim.level1);
		vibrator = AnimationUtils.loadAnimation(this, R.anim.vibrator);
		bLevel1 = (Button) findViewById(R.id.bLevel1);
		bLevel2 = (Button) findViewById(R.id.bLevel2);
		bLevel3 = (Button) findViewById(R.id.bLevel3);
//		Handler handler1 = new Handler();
//		handler1.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				ll.setVisibility(View.VISIBLE);
//				ll.startAnimation(level1Animation);
//
//			}
//		}, 30);

		bLevel1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

                Intent i = new Intent(LevelSelector.this,ActivityLevel1.class);
                startActivity(i);
//				ll.startAnimation(vibrator);
//
//				Thread mythread = new Thread() {
//
//					public void run() {
//						try {
//							{
//								bLevel1.setClickable(false);
//								bLevel2.setClickable(false);
//								bLevel3.setClickable(false);
//								System.out
//										.println("hhhhhhhhhhhhhhhhhhhaaaaahahaha");
//								sleep(2000);
//							}
//						} catch (Exception e) {
//						} finally {
//							System.out
//									.println("hhhhhhhhhhhhhhhhhhhaaaaahahahadddddddddd");
//							bLevel1.clearAnimation();
//
//							bLevel2.clearAnimation();
//
//							bLevel3.clearAnimation();
//							bLevel1.setClickable(true);
//							bLevel2.setClickable(true);
//							bLevel3.setClickable(true);
//							Intent i = new Intent(LevelSelector.this,
//									ActivityLevel1.class);
//							startActivity(i);
//						}
//					}
//				};
//				mythread.start();

			}
		});

		bLevel2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub


                Intent i = new Intent(LevelSelector.this,ActivityLevel2.class);
				startActivity(i);
//				ll.startAnimation(vibrator);
//
//				Thread mythread = new Thread() {
//
//					public void run() {
//						try {
//							{
//								bLevel1.setClickable(false);
//								bLevel2.setClickable(false);
//								bLevel3.setClickable(false);
//								System.out
//										.println("hhhhhhhhhhhhhhhhhhhaaaaahahaha");
//								sleep(2000);
//							}
//						} catch (Exception e) {
//						} finally {
//							System.out
//									.println("hhhhhhhhhhhhhhhhhhhaaaaahahahadddddddddd");
//							bLevel1.clearAnimation();
//							bLevel2.clearAnimation();
//							bLevel3.clearAnimation();
//							bLevel1.setClickable(true);
//							bLevel2.setClickable(true);
//							bLevel3.setClickable(true);
//							Intent i = new Intent(LevelSelector.this,
//									ActivityLevel2.class);
//							startActivity(i);
//						}
//					}
//				};
//				mythread.start();
			}
		});

		bLevel3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

                Intent i = new Intent(LevelSelector.this,ActivityLevel3.class);
							startActivity(i);
//				ll.startAnimation(vibrator);
//
//				Thread mythread = new Thread() {
//
//					public void run() {
//						try {
//							{
//								bLevel1.setClickable(false);
//								bLevel2.setClickable(false);
//								bLevel3.setClickable(false);
//								System.out
//										.println("hhhhhhhhhhhhhhhhhhhaaaaahahaha");
//								sleep(2000);
//							}
//						} catch (Exception e) {
//						} finally {
//							System.out
//									.println("hhhhhhhhhhhhhhhhhhhaaaaahahahadddddddddd");
//							bLevel1.clearAnimation();
//							bLevel2.clearAnimation();
//							bLevel3.clearAnimation();
//							bLevel1.setClickable(true);
//							bLevel2.setClickable(true);
//							bLevel3.setClickable(true);
//							Intent i = new Intent(LevelSelector.this,
//									ActivityLevel3.class);
//							startActivity(i);
//						}
//					}
//				};
//				mythread.start();
			}
		});

		// default to being available
		// attach an OnClickListener
		tb.setOnClickListener(new OnClickListener() {

			@SuppressLint("CommitPrefEdits")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// AudioManager amanager;
				// amanager = (AudioManager)getSystemService(AUDIO_SERVICE);
				SharedPreferences app_preferences_audio = getSharedPreferences(
						"apps_prefs_audio", MODE_PRIVATE);
				// Is the toggle on?
				boolean on = ((ToggleButton) v).isChecked();

				if (on) {
					// 0 means off &&1 means on
					Editor sped = app_preferences_audio.edit();
					sped.putInt("audio", 1);
					sped.commit();
					// turn ringer silent
					// amanager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
					// turn off sound, disable notifications
					// amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
					// notifications
					/*
					 * amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION,
					 * true); //alarm
					 * amanager.setStreamMute(AudioManager.STREAM_ALARM, true);
					 * //ringer amanager.setStreamMute(AudioManager.STREAM_RING,
					 * true);
					 */
					// media
					// amanager.setStreamMute(AudioManager.STREAM_MUSIC, true);

				} else {
					Editor sped = app_preferences_audio.edit();
					sped.putInt("audio", 0);
					sped.commit();
					// turn ringer silent
					// amanager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

					// turn on sound, enable notifications
					// amanager.setStreamMute(AudioManager.STREAM_SYSTEM,
					// false);
					// notifications
					/*
					 * amanager.setStreamMute(AudioManager.STREAM_NOTIFICATION,
					 * false); //alarm
					 * amanager.setStreamMute(AudioManager.STREAM_ALARM, false);
					 * //ringer amanager.setStreamMute(AudioManager.STREAM_RING,
					 * false);
					 */
					// media
					// amanager.setStreamMute(AudioManager.STREAM_MUSIC, false);
				}
			}
		});

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		SharedPreferences app_preferences_random_3 = getSharedPreferences(
				"apps_random_3", MODE_PRIVATE);
		int size3 = app_preferences_random_3.getInt("size", 0);

		SharedPreferences app_preferences_random_2 = getSharedPreferences(
				"apps_random_2", MODE_PRIVATE);
		int size2 = app_preferences_random_2.getInt("size", 0);

		SharedPreferences app_preferences_random_1 = getSharedPreferences(
				"apps_random_1", MODE_PRIVATE);
		int size1 = app_preferences_random_1.getInt("size", 0);
		bLevel1.setText((size1 + 1) + "");
		bLevel2.setText((size2 + 1) + "");
		bLevel3.setText((size3 + 1) + "");

		SharedPreferences app_preferences_audio = getSharedPreferences(
				"apps_prefs_audio", MODE_PRIVATE);
		// Is the toggle on?
		int no = app_preferences_audio.getInt("audio", 1);
		if (no == 1) {
			tb.setChecked(true);
		} else {
			tb.setChecked(false);
		}

	}

}
