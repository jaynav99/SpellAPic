package com.example.spellapic;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewStub;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ActivityLevel1 extends FragmentActivity implements OnTouchListener {
	// STUBVIEW
	View inflatedImage, inflatedKeyboard, inflatedEditor, arg0;

	// image view for the image clues...DYNAMIC
	ImageView iv1, iv2, iv3, iv4, iv5, iv6;
	// ZOOMED VIEW ON IMAGE CLICK EVENT..
	ImageView ivZoom;
	// TABLE ROW FOR THE DYNAMIC IMAGES....
	TableRow tr1, tr2;
	// button for keyboard interface and answer line.....
	Button b_a, b_b, b_c, b_d, b_e, b_f, b_g, b_h, b_i, b_j, b_k, b_l, b_m,
			b_n, b_o, b_p, b_q, b_r, b_s, b_t, b_u, b_v, b_w, b_x, b_y, b_z,
			b_KB_abc, b_KB_cat, editor1[], editor2[];
	// String and integers......
	// ALPHA USED IN DOIT() METHOD TO SEND THE STRING/ANSWER1 FOR LINE1
	// ANSWER/ANSWER2 FOR LINE2 ANSWER
	String alpha, answer1 = "", answer2 = "";
	// ITS VINTY...HACKED
	// SPACEANSWER FOR THE SPACING RELATED PROBLEM OF THE ANSWERLINE
	String spaceAnswer1 = "", spaceAnswer2 = "";
	// WIDTH IS FOR THE SCREEN WIDTH AND HEIGHT FOR THE HEIGHT HEIGHT OF THE
	// SCREEN.
	// LENGTH1 FOR LINE1 ANSWER LENGTH && LENGTH2 FOR LINE2 ANSWER LENGTH..
	// CTR IS USED IN JSON PARSING INPUTSTREAMREADER IN PARSE() METHOD..
	int width, height, ctr, length1, length2;
	// FOR PARSED DATA NEEDED GLOBALLY IN MULTIPLE METHODS.....
	// PUZZLE_ID FOR THE GAME ID, WHICH IS UNIQUE FOR EVERY PUZZLE...
	// NO_OF_PITURES_CLUES FOR THE NO OF PICTURE CLUE PRSED FORM THE JSON FILE..
	int puzzle_id, no_ofPicture_clues;
	// Layouts....
	// EDITORLINEARDISPLAY1,LINEAR LAYOUT FOR THE EDITOR1
	// EDITORLINEARDISPLAY2,LINEAR LAYOUT FOR THE EDITOR2
	// KEYBOARD,LINEAR LAYOUT FOR THE LINE1 KEYBOARD
	LinearLayout EditorLinearDisplay1, EditorLinearDisplay2, keyboardLine1,
			keyboardLine2, keyboardLine3;
	// MYFRAMEKEYBOARD, TABLELAYOUT FOR THE THREE LINEARLAYOUTS KEYBOARDLINE1,
	// KEYBOARDLINE2,KEYBOARDLINE3
	// TLIMAGESDYNAMIC FOR THE TWO ROWS OF IMAGES,TR1 AND TR2..
	TableLayout myFrameKeyboard, tlImagesDynamic;
	// RL FOR THE MAIN LAYOUT,ACTIVITY_MAIN TO FIND THE WIDTH AND HEIGHT OF THE
	// DEVICE....
	RelativeLayout rl;
	// Arraylist for puzzle GETTING IT FROM THE METHOD GETPUZZLE IN JPARSERIN
	// CLASS/USED IN PARSE() METHOD...
	ArrayList<puzzle1> game1;
	// ARRAY OF ANSWER,LINE1 AND LINE2 OF A SINGLE PUZZLE DAT IS SINGLE OBJECT
	// OF PUZZLE1...USED IN MULTIPLE METHODS.
	ArrayList<String> answersArrayWord;
	// NOT USED RITE NOW INITIALLY USED FOR THE GETTING THE SPACE
	// ArrayList<String> arraySpaceAnswer;
	// ARRAYLIST OF LENGTHS OF ANSWERLINE1 AND LINE2 FOR SINGLE PUZZLE..
	ArrayList<Integer> answersArrayLength;
	// ISCLICKED LOGIC USED IN ZOOMIMAGE---LOGIC CRACKED@VINTY....
	boolean isclicked = true;
	// MP FOR SOUND FILE....
	// MediaPlayer mp;
	// object of puzzle1 class
	puzzle1 pp;
	// int trickHintCat = 1;

	/*
	 * private ArrayList<String> preWordArray1; private ArrayList<Integer>
	 * preLengthsArray1; private ArrayList<String> preWordArray2; private
	 * ArrayList<Integer> preLengthsArray2;
	 */
	// int values[] = new int[3];
	// ARRAYLIST OF VALUES
	ArrayList<Integer> values, noOfHintLine1, noOfHintLine2;
	// final int count = 3;
	final String KEY_COUNT = "COUNT";
	final String KEY_VAL_PREFIX = "VAL_";
	final String KEY_VAL_PREFIX_HINT_1_2_ = "VAL_HINT_1_2_";
	final String KEY_VAL_PREFIX_HINT_1_1_ = "VAL_HINT_1_1_";
	int random;
	int randomEntered;
	SharedPreferences app_preferences_random, app_preferences_coins,
			app_preferences_trick_cat_level1;
	ArrayList<Integer> myAList;
	int randomRescued, help, vinty1, vinty2;

	ArrayList<Integer> numbersHintLine1 = new ArrayList<Integer>();
	ArrayList<Integer> numbersHintLine2 = new ArrayList<Integer>();

	private String category = "";
	int textColor = Color.rgb(228, 174, 107);
	int startupNoOfCoins;
	int noOfCoins = 500;
	TextView tvCoins;
	MediaPlayer mpCoin1, mpCoin2, mpCoin3, mpCoin4, mpCoin5;
	int audio;
	Custom_Dialog dialog;
	Button btnStciker;
	Custom_Dialog dialog1;
	Dialog dialogAlert;
	TextView LevelText;
	TextView ivSuccessAnswerBox;
	ImageView myView1, myView2, myView3, myView4, myView5;
	View arg1;
	Animation rotation, points;
	Button bpointSuperpb;
	String str;

	// onCreate method.....//MAIN
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// for No title on the screen....Game effect...
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_main);
		// to set the display width and height....
		SetDisplayWidhtHeight();
		LevelText = (TextView) findViewById(R.id.headerTitle);
		// LevelText.setText("Level2");
		btnStciker = (Button) findViewById(R.id.bSticker);

		Button BackButton = (Button) findViewById(R.id.bHeaderBack);
		BackButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		// to set all the view stubs....
		stubbing();

		new parsing().execute();
		// LevelText.setText("Level1"+values.size());
		// parse();
		/*
		 * numbersHintLine1.clear(); numbersHintLine2.clear();
		 */
		// new parsing().execute();

		/*
		 * // to set the display width and height.... SetDisplayWidhtHeight();
		 * // to set all the view stubs.... stubbing();
		 */

		// to parse the data from the json file stored locally inside the res
		// folder...

		// to start the game randomly...
	}

	private void parse() {
		// TODO Auto-generated method stub
		game1 = new ArrayList<puzzle1>();
		game1.clear();
		InputStream inputStream = getResources().openRawResource(
				R.raw.puzzlespellapic_original);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			ctr = inputStream.read();
			while (ctr != -1) {
				byteArrayOutputStream.write(ctr);
				ctr = inputStream.read();
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JParserIn parsedData = new JParserIn();
		game1 = parsedData.getPuzzle(byteArrayOutputStream);
	}

	class parsing extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			parse();
			// numbersHintLine1.clear();
			// numbersHintLine2.clear();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			values = new ArrayList<Integer>();
			values.clear();
			app_preferences_random = getSharedPreferences("apps_random_1",
					MODE_PRIVATE);
			randomRescued = app_preferences_random
					.getInt("randomentered", 1500);
			myAList = new ArrayList<Integer>();
			int size = app_preferences_random.getInt("size", 0);
			if (size > 0) {
				for (int j = 0; j < size; j++) {
					myAList.add(app_preferences_random.getInt(KEY_VAL_PREFIX
							+ j, 0));
				}
				values = myAList;
			}
			if (values.contains(randomRescued)) {
				randomRescued = 1500;
			}
			// LevelText.setText("Level1");
			LevelText.setText("Level1 ");

			btnStciker.setText("" + (values.size() + 1));
			initialize();
			initialRandomPuzzle();
		}
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		System.out.println("boldit,,,,,,,,////1111");
		app_preferences_trick_cat_level1 = getSharedPreferences(
				"appsPreferenceTrickHint", MODE_PRIVATE);
		Editor sped = app_preferences_trick_cat_level1.edit();
		for (int i = 0; i < numbersHintLine1.size(); i++) {
			System.out.println("boldit,,,,,,,,////22222");
			sped.putInt(KEY_VAL_PREFIX_HINT_1_1_ + i, numbersHintLine1.get(i));
		}
		sped.putInt("sizeHint1_1", numbersHintLine1.size());
		for (int i = 0; i < numbersHintLine2.size(); i++) {
			System.out.println("boldit,,,,,,,,////22222");
			sped.putInt(KEY_VAL_PREFIX_HINT_1_2_ + i, numbersHintLine2.get(i));
		}
		sped.putInt("sizeHint1_2", numbersHintLine2.size());
		sped.commit();
		numbersHintLine1.clear();
		numbersHintLine2.clear();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		noOfHintLine1 = new ArrayList<Integer>();
		noOfHintLine2 = new ArrayList<Integer>();
		// noOfHintLine1.clear();
		System.out.println("boldit,,,,,,,,////444444");
		app_preferences_trick_cat_level1 = getSharedPreferences(
				"appsPreferenceTrickHint", MODE_PRIVATE);
		int size1 = app_preferences_trick_cat_level1.getInt("sizeHint1_1", 0);
		if (size1 > 0) {
			System.out.println("boldit,,,,,,,,////5555");
			for (int j = 0; j < size1; j++) {
				// System.out.println("boldit///....vinty"+app_preferences_trick_cat.getInt(KEY_VAL_PREFIX_HINT_1_
				// + j, 0));
				noOfHintLine1.add(app_preferences_trick_cat_level1.getInt(
						KEY_VAL_PREFIX_HINT_1_1_ + j, 0));
				// System.out.println("boldit///....vinty"+app_preferences_trick_cat.getInt(KEY_VAL_PREFIX_HINT
				// + j, 0));
			}
			numbersHintLine1 = noOfHintLine1;
		}
		int size2 = app_preferences_trick_cat_level1.getInt("sizeHint1_2", 0);
		if (size2 > 0) {
			System.out.println("boldit,,,,,,,,////5555");
			for (int j = 0; j < size2; j++) {
				noOfHintLine2.add(app_preferences_trick_cat_level1.getInt(
						KEY_VAL_PREFIX_HINT_1_2_ + j, 0));
			}
			numbersHintLine2 = noOfHintLine2;
		}
	}

	private void trickHint(int i) {
		app_preferences_trick_cat_level1 = getSharedPreferences(
				"appsPreferenceTrickHint", MODE_PRIVATE);
		SharedPreferences.Editor editorSharing = app_preferences_trick_cat_level1
				.edit();
		editorSharing.putInt("KEY_HINT_CAT_1_", i);

		// editor.putInt("KEY_COINS", noOfCoins);
		editorSharing.commit();
	}

	private void initialRandomPuzzle() {
		// TODO Auto-generated method stub
		// pp = new puzzle1();
		if (values.size() < game1.size()) {
			randomizeBegin();
		} else if (values.size() == game1.size()) {

			keyboardLine1.removeAllViews();
			keyboardLine2.removeAllViews();
			keyboardLine3.removeAllViews();
			EditorLinearDisplay1.removeAllViews();
			EditorLinearDisplay2.removeAllViews();

			showDialog();
		}

	}

	private void sharing() {
		app_preferences_coins = getSharedPreferences("appsPreferenceCoins",
				MODE_PRIVATE);
		SharedPreferences.Editor editorSharing = app_preferences_coins.edit();
		editorSharing.putInt("KEY_STARTUP_COINS", 500);
		editorSharing.putInt("KEY_COINS_RIGHT", 5);
		editorSharing.putInt("KEY_COINS_BUY_ABC", 50);
		editorSharing.putInt("KEY_COINS_BUY_CAT", 100);
		editorSharing.putInt("KEY_LEVELS_1", 60);
		// editor.putInt("KEY_COINS", noOfCoins);
		editorSharing.commit();
	}

	private void initialize() {
		// TODO Auto-generated method stub

		/*
		 * tvCoins = (TextView) findViewById(R.id.tvCoins);
		 * 
		 * sharing(); app_preferences1 = getSharedPreferences("apps1",
		 * MODE_PRIVATE); noOfCoins = app_preferences1.getInt("KEY_COINS", 500);
		 * tvCoins.setText("" + noOfCoins);
		 */

		int textsizeKey = (int) width / 12;
		LinearLayout.LayoutParams paramslinear = new LinearLayout.LayoutParams(
				(int) width / 11, (int) width / 11);
		paramslinear.setMargins(2, 2, 2, 2);
		paramslinear.gravity = Gravity.CENTER;

		LinearLayout.LayoutParams paramslinearABC = new LinearLayout.LayoutParams(
				(int) width / 7, (int) width / 11);
		paramslinearABC.setMargins(2, 2, 2, 2);
		paramslinearABC.gravity = Gravity.CENTER;
		// DisplayMetrics metrics;
		// metrics = getApplicationContext().getResources().getDisplayMetrics();
		/*
		 * float Textsize =myTextView.getTextSize()/metrics.density;
		 * myTextView.setTextSize(Textsize+1);
		 */

		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/vintyBold.ttf");

		// Typeface face = Typeface.SANS_SERIF;
		// Line1
		b_q = new Button(this);
		b_q.setBackgroundResource(R.drawable.btn);
		b_q.setText("Q");
		b_q.setTypeface(face, 1);
		b_q.setPadding(-8, -8, -8, 0);
		b_q.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_q.setTextColor(Color.rgb(54, 30, 0));
		b_q.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_q);
		b_w = new Button(this);
		b_w.setBackgroundResource(R.drawable.btn);
		b_w.setText("W");
		b_w.setTypeface(face, 1);
		b_w.setPadding(-8, -8, -8, 0);
		b_w.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_w.setTextColor(Color.rgb(54, 30, 0));
		b_w.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_w);
		b_e = new Button(this);
		b_e.setBackgroundResource(R.drawable.btn);
		b_e.setText("E");
		b_e.setTypeface(face, 1);
		b_e.setPadding(-8, -8, -8, 0);
		b_e.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_e.setTextColor(Color.rgb(54, 30, 0));
		b_e.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_e);
		b_r = new Button(this);
		b_r.setBackgroundResource(R.drawable.btn);
		b_r.setText("R");
		b_r.setTypeface(face, 1);
		b_r.setPadding(-8, -8, -8, 0);
		b_r.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_r.setTextColor(Color.rgb(54, 30, 0));
		b_r.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_r);
		b_t = new Button(this);
		b_t.setBackgroundResource(R.drawable.btn);
		b_t.setText("T");
		b_t.setTypeface(face, 1);
		b_t.setPadding(-8, -8, -8, 0);
		b_t.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_t.setTextColor(Color.rgb(54, 30, 0));
		b_t.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_t);
		b_y = new Button(this);
		b_y.setBackgroundResource(R.drawable.btn);
		b_y.setTextColor(Color.rgb(54, 30, 0));
		b_y.setText("Y");
		b_y.setTypeface(face, 1);
		b_y.setPadding(-8, -8, -8, 0);
		b_y.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_y.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_y);
		b_u = new Button(this);
		b_u.setBackgroundResource(R.drawable.btn);
		b_u.setTextColor(Color.rgb(54, 30, 0));
		b_u.setText("U");
		b_u.setTypeface(face, 1);
		b_u.setPadding(-8, -8, -8, 0);
		b_u.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_u.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_u);
		b_i = new Button(this);
		b_i.setBackgroundResource(R.drawable.btn);
		b_i.setTextColor(Color.rgb(54, 30, 0));
		b_i.setText("I");
		b_i.setTypeface(face, 1);
		b_i.setPadding(-8, -8, -8, 0);
		b_i.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_i.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_i);
		b_o = new Button(this);
		b_o.setBackgroundResource(R.drawable.btn);
		b_o.setTextColor(Color.rgb(54, 30, 0));
		b_o.setText("O");
		b_o.setTypeface(face, 1);
		b_o.setPadding(-8, -8, -8, 0);
		b_o.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_o.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_o);
		b_p = new Button(this);
		b_p.setBackgroundResource(R.drawable.btn);
		b_p.setTextColor(Color.rgb(54, 30, 0));
		b_p.setText("P");
		b_p.setTypeface(face, 1);
		b_p.setPadding(-8, -8, -8, 0);
		b_p.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_p.setLayoutParams(paramslinear);
		keyboardLine1.addView(b_p);

		// line2
		b_a = new Button(this);
		b_a.setClickable(true);
		b_a.setBackgroundResource(R.drawable.btn);
		b_a.setTextColor(Color.rgb(54, 30, 0));
		b_a.setText("A");
		b_a.setTypeface(face, 1);
		b_a.setPadding(-8, -8, -8, 0);
		b_a.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_a.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_a);
		b_s = new Button(this);
		b_s.setBackgroundResource(R.drawable.btn);
		b_s.setTextColor(Color.rgb(54, 30, 0));
		b_s.setText("S");
		b_s.setTypeface(face, 1);
		b_s.setPadding(-8, -8, -8, 0);
		b_s.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_s.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_s);
		b_d = new Button(this);
		b_d.setBackgroundResource(R.drawable.btn);
		b_d.setTextColor(Color.rgb(54, 30, 0));
		b_d.setText("D");
		b_d.setTypeface(face, 1);
		b_d.setPadding(-8, -8, -8, 0);
		b_d.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_d.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_d);
		b_f = new Button(this);
		b_f.setBackgroundResource(R.drawable.btn);
		b_f.setTextColor(Color.rgb(54, 30, 0));
		b_f.setText("F");
		b_f.setTypeface(face, 1);
		b_f.setPadding(-8, -8, -8, 0);
		b_f.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_f.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_f);
		b_g = new Button(this);
		b_g.setBackgroundResource(R.drawable.btn);
		b_g.setTextColor(Color.rgb(54, 30, 0));
		b_g.setText("G");
		b_g.setTypeface(face, 1);
		b_g.setPadding(-8, -8, -8, 0);
		b_g.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_g.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_g);
		b_h = new Button(this);
		b_h.setBackgroundResource(R.drawable.btn);
		b_h.setTextColor(Color.rgb(54, 30, 0));
		b_h.setText("H");
		b_h.setTypeface(face, 1);
		b_h.setPadding(-8, -8, -8, 0);
		b_h.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_h.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_h);
		b_j = new Button(this);
		b_j.setBackgroundResource(R.drawable.btn);
		b_j.setTextColor(Color.rgb(54, 30, 0));
		b_j.setText("J");
		b_j.setTypeface(face, 1);
		b_j.setPadding(-8, -8, -8, 0);
		b_j.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_j.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_j);
		b_k = new Button(this);
		b_k.setBackgroundResource(R.drawable.btn);
		b_k.setTextColor(Color.rgb(54, 30, 0));
		b_k.setText("K");
		b_k.setTypeface(face, 1);
		b_k.setPadding(-8, -8, -8, 0);
		b_k.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_k.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_k);
		b_l = new Button(this);
		b_l.setBackgroundResource(R.drawable.btn);
		b_l.setTextColor(Color.rgb(54, 30, 0));
		b_l.setText("L");
		b_l.setTypeface(face, 1);
		b_l.setPadding(-8, -8, -8, 0);
		b_l.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_l.setLayoutParams(paramslinear);
		keyboardLine2.addView(b_l);

		// line3
		b_KB_abc = new Button(this);
		b_KB_abc.setBackgroundResource(R.drawable.b_abc);
		b_KB_abc.setLayoutParams(paramslinearABC);
		keyboardLine3.addView(b_KB_abc);
		b_z = new Button(this);
		b_z.setBackgroundResource(R.drawable.btn);
		b_z.setTextColor(Color.rgb(54, 30, 0));
		b_z.setText("Z");
		b_z.setTypeface(face, 1);
		b_z.setPadding(-8, -8, -8, 0);
		b_z.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_z.setLayoutParams(paramslinear);
		keyboardLine3.addView(b_z);
		b_x = new Button(this);
		b_x.setBackgroundResource(R.drawable.btn);
		b_x.setTextColor(Color.rgb(54, 30, 0));
		b_x.setText("X");
		b_x.setTypeface(face, 1);
		b_x.setPadding(-8, -8, -8, 0);
		b_x.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_x.setLayoutParams(paramslinear);
		keyboardLine3.addView(b_x);
		b_c = new Button(this);
		b_c.setBackgroundResource(R.drawable.btn);
		b_c.setTextColor(Color.rgb(54, 30, 0));
		b_c.setText("C");
		b_c.setTypeface(face, 1);
		b_c.setPadding(-8, -8, -8, 0);
		b_c.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_c.setLayoutParams(paramslinear);
		keyboardLine3.addView(b_c);
		b_v = new Button(this);
		b_v.setBackgroundResource(R.drawable.btn);
		b_v.setTextColor(Color.rgb(54, 30, 0));
		b_v.setText("V");
		b_v.setTypeface(face, 1);
		b_v.setPadding(-8, -8, -8, 0);
		b_v.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_v.setLayoutParams(paramslinear);
		keyboardLine3.addView(b_v);
		b_b = new Button(this);
		b_b.setBackgroundResource(R.drawable.btn);
		b_b.setTextColor(Color.rgb(54, 30, 0));
		b_b.setText("B");
		b_b.setTypeface(face, 1);
		b_b.setPadding(-8, -8, -8, 0);
		b_b.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_b.setLayoutParams(paramslinear);
		keyboardLine3.addView(b_b);
		b_n = new Button(this);
		b_n.setBackgroundResource(R.drawable.btn);
		b_n.setTextColor(Color.rgb(54, 30, 0));
		b_n.setText("N");
		b_n.setTypeface(face, 1);
		b_n.setPadding(-8, -8, -8, 0);
		b_n.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_n.setLayoutParams(paramslinear);
		keyboardLine3.addView(b_n);
		b_m = new Button(this);
		b_m.setBackgroundResource(R.drawable.btn);
		b_m.setTextColor(Color.rgb(54, 30, 0));
		b_m.setText("M");
		b_m.setTypeface(face, 1);
		b_m.setPadding(-8, -8, -8, 0);
		b_m.setTextSize(TypedValue.COMPLEX_UNIT_PX, textsizeKey);
		b_m.setLayoutParams(paramslinear);
		keyboardLine3.addView(b_m);
		// b_KB_abc = new Button(this);
		// b_KB_abc.setBackgroundResource(R.drawable.b_abc);
		// b_KB_abc.setLayoutParams(paramslinearABC);
		// keyboardLine3.addView(b_KB_abc);
		b_KB_cat = new Button(this);
		b_KB_cat.setBackgroundResource(R.drawable.b_category);
		b_KB_cat.setLayoutParams(paramslinearABC);
		keyboardLine3.addView(b_KB_cat);

		// b_KB_abc.setOnClickListener(this);
		// b_KB_cat.setOnClickListener(this);
		b_a.setOnTouchListener(this);
		b_b.setOnTouchListener(this);
		b_c.setOnTouchListener(this);
		b_d.setOnTouchListener(this);
		b_e.setOnTouchListener(this);
		b_f.setOnTouchListener(this);
		b_g.setOnTouchListener(this);
		b_h.setOnTouchListener(this);
		b_i.setOnTouchListener(this);
		b_j.setOnTouchListener(this);
		b_k.setOnTouchListener(this);
		b_l.setOnTouchListener(this);
		b_m.setOnTouchListener(this);
		b_n.setOnTouchListener(this);
		b_o.setOnTouchListener(this);
		b_p.setOnTouchListener(this);
		b_q.setOnTouchListener(this);
		b_r.setOnTouchListener(this);
		b_s.setOnTouchListener(this);
		b_t.setOnTouchListener(this);
		b_u.setOnTouchListener(this);
		b_v.setOnTouchListener(this);
		b_w.setOnTouchListener(this);
		b_x.setOnTouchListener(this);
		b_y.setOnTouchListener(this);
		b_z.setOnTouchListener(this);

		b_KB_abc.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {
				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					// arg0.set
					// InfoAlert("Coming Soon....", "Letter Hint");
					// return true;
					b_KB_abc.setEnabled(false);
					buyEnter(arg0);
					return true;
				}
				return false;
			}
		});
		b_KB_cat.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View arg0, MotionEvent event) {

				app_preferences_trick_cat_level1 = getSharedPreferences(
						"appsPreferenceTrickHint", MODE_PRIVATE);
				int no = app_preferences_trick_cat_level1.getInt(
						"KEY_HINT_CAT_1_", 1);

				if (event.getAction() == MotionEvent.ACTION_DOWN) {
					b_KB_cat.setEnabled(false);
					// arg0.set
					if (noOfCoins < 100 && no == 1) {
						int vinitHint = 100 - noOfCoins;
						InfoThemeAlert("buy", "You need " + vinitHint
								+ " more coins");
						// return true;
					} else if (no == 1) {
						InfoThemeAlert("hint_cat",
								"Reveal Category\n (100 coins)");
						// if(no==1){
						// HintEarnings(100);
						// trickHintCat=0;
						// trickHint(trickHintCat);
						// }
						// b_KB_cat.setEnabled(false);
						// return true;
					} else if (no == 0) {
						InfoThemeAlert("cat", "" + category);
					}
					return true;
				}
				return false;
			}
		});
	}

	private void InfoThemeAlert(String string, String string2) {
		// TODO Auto-generated method stub

		dialogAlert = new Dialog(ActivityLevel1.this, R.style.CustomDialogTheme);
		// dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialogAlert.requestWindowFeature(Window.FEATURE_NO_TITLE);
		System.out.println("iiiiiaminiiiiiiii");
		dialogAlert.setContentView(R.layout.dialog_hint);
		System.out.println("iiiiiaminiiiiiiii222222222");
		dialogAlert.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		Button image1 = (Button) dialogAlert.findViewById(R.id.image1);
		Button image2 = (Button) dialogAlert.findViewById(R.id.image2);
		Button imageCancel = (Button) dialogAlert
				.findViewById(R.id.imageCancel);

		if (string.equals("buy")) {
			RelativeLayout rl = (RelativeLayout) dialogAlert
					.findViewById(R.id.rlAlertHint);
			rl.setBackgroundResource(R.drawable.alert_buy);
			imageCancel.setVisibility(View.GONE);
			image1.setVisibility(View.VISIBLE);
			image2.setVisibility(View.VISIBLE);
		} else if (string.equals("hint_cat")) {
			RelativeLayout rl = (RelativeLayout) dialogAlert
					.findViewById(R.id.rlAlertHint);
			rl.setBackgroundResource(R.drawable.alert_hint);
			imageCancel.setVisibility(View.GONE);
			image1.setVisibility(View.VISIBLE);
			image2.setVisibility(View.VISIBLE);
		} else if (string.equals("cat")) {
			RelativeLayout rl = (RelativeLayout) dialogAlert
					.findViewById(R.id.rlAlertHint);
			rl.setBackgroundResource(R.drawable.alert_puzzle_category);
			image1.setVisibility(View.GONE);
			image2.setVisibility(View.GONE);
			imageCancel.setVisibility(View.VISIBLE);
			dialogAlert.setCanceledOnTouchOutside(true);
		} else if (string.equals("hint_letter")) {
			RelativeLayout rl = (RelativeLayout) dialogAlert
					.findViewById(R.id.rlAlertHint);
			rl.setBackgroundResource(R.drawable.alert_hint);
			imageCancel.setVisibility(View.GONE);
			image1.setVisibility(View.VISIBLE);
			image2.setVisibility(View.VISIBLE);
		}
		// set the custom dialog components - text, image and button
		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/dotted.ttf");

		TextView text = (TextView) dialogAlert.findViewById(R.id.text1);
		text.setText(string2);
		text.setTypeface(face);
		// text.setMovementMethod(ScrollingMovementMethod.getInstance());
		dialogAlert.show();
		dialogAlert.setCancelable(false);
		str = string;
		image1.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				dialogAlert.cancel();
				if (str.equals("hint_cat")) {
					app_preferences_trick_cat_level1 = getSharedPreferences(
							"appsPreferenceTrickHint", MODE_PRIVATE);
					int no = app_preferences_trick_cat_level1.getInt(
							"KEY_HINT_CAT_1_", 1);
					if (no == 1) {
						HintEarnings(100);
						// trickHintCat=0;
						trickHint(0);
					}
					InfoThemeAlert("cat", "" + category);
					// b_KB_cat.setEnabled(false);
				} else if (str.equals("hint_letter")) {
					HintEarnings(50);
					repeat1();
				}else if(str.equals("buy")){
					InfoAlert("Buy Coins...", "Coming Soon");
				}
				b_KB_abc.setEnabled(true);
				b_KB_cat.setEnabled(true);
			}
		});
		image2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				b_KB_abc.setEnabled(true);
				b_KB_cat.setEnabled(true);
				dialogAlert.cancel();
				// Intent profile = new Intent(Emergencytab.this,Profile.class);
				// startActivity(profile);

			}
		});
		imageCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				b_KB_abc.setEnabled(true);
				b_KB_cat.setEnabled(true);
				dialogAlert.cancel();
				// Intent profile = new Intent(Emergencytab.this,Profile.class);
				// startActivity(profile);

			}
		});
	}

	private int countLengthAnswerEditor1() {
		// TODO Auto-generated method stub
		int j = 0;
		for (int i = 0; i < length1; i++) {

			if (!editor1[i].getText().toString().equals(""))
				j = j + 1;
			vinty1 = j;

		}
		System.out.println("444444444444444444444" + vinty1);
		return vinty1;

	}

	private int countLengthAnswerEditor2() {
		// TODO Auto-generated method stub
		int j = 0;
		for (int i = 0; i < length2; i++) {

			if (!editor2[i].getText().toString().equals(""))
				j = j + 1;

		}
		vinty2 = j;
		return vinty2;

	}

	private void buyEnter(View arg02) {
		// TODO Auto-generated method stub
		if (arg02 == b_KB_abc) {
			if (countLengthAnswerEditor1() < length1
					|| countLengthAnswerEditor2() < length2) {
				System.out.println("hint11111111111111111111111"
						+ countLengthAnswerEditor1());
				if (noOfCoins < 50) {
					int vinitHint = 50 - noOfCoins;
					InfoThemeAlert("buy", "You need " + vinitHint
							+ " more coins");
				} else {
					InfoThemeAlert("hint_letter", "Reveal Letter\n (50 coins)");
					// repeat1();
					// HintEarnings(50);
				}
			}
		}
	}

	private void repeat1() {
		// TODO Auto-generated method stub
		// length-1 bcoz the editor array alwys start wid 0....
		// int select = 0;
		if (length1 != 0 && length2 == 0) {
			if (countLengthAnswerEditor1() < length1 - 1) {
				System.out.println("ccccccccccccccccchhhhhcase1");
				guess1();
			} else if (countLengthAnswerEditor1() == length1 - 1) {
				System.out.println("mmmmmm<<<<<<");
				if (!editor1[0].getText().toString().equals("")) {
					System.out.println("ccccccccccccccccchhhhhcase2");
					guess1();
				} else if (editor1[0].getText().toString().equals("")) {
					System.out.println("ccccccccccccccccchhhhhcase3");
					char a = spaceAnswer1.charAt(0);
					System.out.println("gghgggggggggggg" + a);
					editor1[0].setText("" + a);
					editor1[0].setTextColor(Color.GREEN);
					editor1[0].setClickable(false);
					editor1[0].setEnabled(false);
					editor1[0].setTag("edited");
				}
			}
			checker();
		} else if (length1 != 0 && length2 != 0) {
			System.out.println("hint444444444444444444444444444444");
			System.out.println("ccccccccccccccccchhhhhcase4");

			if (countLengthAnswerEditor2() == length2) {
				if (countLengthAnswerEditor1() < length1 - 1) {
					System.out.println("ccccccccccccccccchhhhhcase5");
					System.out.println("hint555555555555555555555555555");
					guess1();
				} else if (countLengthAnswerEditor1() == length1 - 1) {
					if (!editor1[0].getText().toString().equals("")) {
						System.out.println("ccccccccccccccccchhhhhcase6");
						guess1();
					} else if (editor1[0].getText().toString().equals("")) {
						System.out.println("ccccccccccccccccchhhhhcase7");
						char a = spaceAnswer1.charAt(0);
						System.out.println("gghgggggggggggg" + a);
						editor1[0].setText("" + a);
						editor1[0].setTextColor(Color.GREEN);
						editor1[0].setClickable(false);
						editor1[0].setEnabled(false);
						editor1[0].setTag("edited");
					}
				}
			} else if (countLengthAnswerEditor1() == length1 - 1
					&& countLengthAnswerEditor2() < length2) {
				System.out.println("ccccccccccccccccchhhhhcase7");
				System.out.println("hint666666666666666666666666");
				guess2();
			} else if (countLengthAnswerEditor1() < length1
					&& countLengthAnswerEditor2() < length2) {
				System.out.println("ccccccccccccccccchhhhhcase7");
				int randSelectLine = (int) (Math.random() * (2) + 1);
				System.out.println("hint7777777777777777777777777");
				int select = randSelectLine;
				System.out.println("hint888888888888888888888888" + select);
				if (select == 1) {
					if (countLengthAnswerEditor1() < length1 - 1) {
						guess1();
					}
				} else if (select == 2) {
					guess2();
				}
				System.out.println("ccccccccccccccccchhhhhcase71");
			} else if (countLengthAnswerEditor1() == length1
					&& countLengthAnswerEditor2() < length2) {
				System.out.println("ccccccccccccccccchhhhhcase8");
				guess2();
			}
			checker();
		}
	}

	private void HintEarnings(int i) {
		// TODO Auto-generated method stub
		noOfCoins = noOfCoins - i;
		// if(noOfCoins<0){
		app_preferences_coins = getSharedPreferences("appsPreferenceCoins",
				MODE_PRIVATE);
		SharedPreferences.Editor editorShare = app_preferences_coins.edit();
		editorShare.putInt("KEY_COINS", noOfCoins);
		editorShare.commit();
		tvCoins.setText("" + noOfCoins);
		// }else{
		// InfoAlert("Buy Coins....", "You have insufficient coins."+noOfCoins);
		// }
	}

	private void guess2() {
		// TODO Auto-generated method stub
		int randAnswerHintLine2 = (int) (Math.random() * (length2));
		if (pp.getflag() == 0) {
			System.out.println("nunnnnnn222222" + numbersHintLine2.toString()
					+ "bbbbbbb" + randAnswerHintLine2 + ">>>>>"
					+ numbersHintLine2.toString());
			if (!numbersHintLine2.contains(randAnswerHintLine2) && length2 != 0) {

				if (editor2[randAnswerHintLine2].getText().toString()
						.equals("")) {
					char a = spaceAnswer2.charAt(randAnswerHintLine2);
					editor2[randAnswerHintLine2].setText("" + a);
					// HintEarnings(50);
					numbersHintLine2.add(randAnswerHintLine2);
					editor2[randAnswerHintLine2].setTextColor(Color.GREEN);
					editor2[randAnswerHintLine2].setEnabled(false);
					editor2[randAnswerHintLine2].setTag("edited");
				} else
					guess2();

			} else {
				guess2();
			}
		}
	}

	private void guess1() {
		// TODO Auto-generated method stub
		// length-1 bcoz the editor array alwys start wid 0....
		int randAnswer = (int) (Math.random() * (length1 - 1) + 1);
		System.out.println("randomtest..." + randAnswer);
		if (pp.getflag() == 0) {
			if (!numbersHintLine1.contains(randAnswer)) {

				if (editor1[randAnswer].getText().toString().equals("")) {
					char a = spaceAnswer1.charAt(randAnswer);
					numbersHintLine1.add(randAnswer);
					// HintEarnings(50);
					editor1[randAnswer].setText("" + a);
					System.out.println("22222hhhhhhhhhhhhhhhjjjjjjjjjjjjjjj"
							+ a);
					// HintEarnings(50);
					editor1[randAnswer].setTextColor(Color.GREEN);
					editor1[randAnswer].setClickable(false);
					editor1[randAnswer].setEnabled(false);
					editor1[randAnswer].setTag("edited");
				} else
					guess1();
			} else
				guess1();
		}
	}

	public void InfoAlert(String title, String Msg) {
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle(title);
		alert.setMessage(Msg);
		alert.setCancelable(false);
		alert.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface arg0, int arg1) {

			}
		});
		alert.show();
	}

	private void showDialog() {
		dialog = new Custom_Dialog(this);
		dialog.setContentView(R.layout.custom_dialog);
		// ImageView image = (ImageView) dialog.findViewById(R.id.imageDialog);
		// LinearLayout.LayoutParams imageFinishParams = new
		// LinearLayout.LayoutParams(
		// width, width);
		// image.setLayoutParams(imageFinishParams);
		// image.setBackgroundResource(R.drawable.finish);
		dialog.show();
		dialog.setCancelable(false);
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				dialog.dismiss();
				finish();
			}
		}, 4500);

	}

	private void randomizeBegin() {
		// initialize();
		tvCoins = (TextView) findViewById(R.id.tvCoins);
		sharing();
		app_preferences_coins = getSharedPreferences("appsPreferenceCoins",
				MODE_PRIVATE);
		noOfCoins = app_preferences_coins.getInt("KEY_COINS", 500);
		tvCoins.setText("" + noOfCoins);
		if (randomRescued == 1500) {
			random = (int) (Math.random() * game1.size());
		} else if (!values.contains(randomRescued)) {
			random = randomRescued;
			randomRescued = 1500;
		}
		// size must be less than 2 bcoz on success we are adding it
		if (values.size() < game1.size() && !values.contains(random)) {
			beginthegame(random);
		} else if (values.contains(random)) {
			randomizeBegin();
		}
	}

	private void beginthegame(int random2) {
		isclicked = true;
		startKeyboardClick();
		ArrayList<String> preWordArray1;

		ArrayList<Integer> preLengthsArray1;

		ArrayList<Integer> preLengthSizeArray1;
		ArrayList<Integer> preLengthSizeArray2;
		ArrayList<String> preWordArray2;

		ArrayList<Integer> preLengthsArray2;
		randomEntered = random2;
		help = random2;

		app_preferences_random = getSharedPreferences("apps_random_1",
				MODE_PRIVATE);
		Editor sped = app_preferences_random.edit();
		sped.putInt("randomentered", help);
		sped.commit();
		pp = game1.get(randomEntered);
		int image = pp.getPiclength();
		LinearLayout.LayoutParams paramslinear = null, paramslinearpre2 = null;
		int textSizeEditor, textSizeSpecial;
		puzzle_id = pp.getId();
		tr1 = (TableRow) inflatedImage.findViewById(R.id.tlImagesRow1);
		tr2 = (TableRow) inflatedImage.findViewById(R.id.tlImagesRow2);
		tr1.removeAllViews();
		tr2.removeAllViews();
		imaging(image);
		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/vintyBold.ttf");

		// Typeface face = Typeface.SERIF;
		category = pp.getCategory();
		answersArrayWord = pp.getAnswer();
		answersArrayLength = pp.getAnswerLengths();
		EditorLinearDisplay2.removeAllViews();
		EditorLinearDisplay1.removeAllViews();
		for (int ansline = 0; ansline < answersArrayWord.size(); ansline++) {
			if (ansline == 0) {
				length1 = answersArrayLength.get(0);
				if (length1 > 10) {
					paramslinear = new LinearLayout.LayoutParams(
							(int) width / 16, (int) width / 12);
					textSizeEditor = (int) width / 15;
					textSizeSpecial = (int) width / 15;
				} else {
					paramslinear = new LinearLayout.LayoutParams(
							(int) width / 11, (int) width / 11);
					textSizeEditor = (int) width / 12;
					textSizeSpecial = (int) width / 15;
				}
				answer1 = answersArrayWord.get(0);
				spaceAnswer1 = answer1;
				editor1 = new Button[length1];
				try {
					for (int i = 0; i < length1; i++) {
						editor1[i] = new Button(this);
						// editor1[i].setT
						paramslinear.setMargins(1, 0, 0, 0);
						editor1[i].setLayoutParams(paramslinear);
						editor1[i].setPadding(-8, -7, -8, -1);
						EditorLinearDisplay1.addView(editor1[i]);
						editor1[i].setBackgroundResource(R.drawable.btneditor);
						editor1[i].setTypeface(face, 1);
						// editor1[i].setTextColor(textColor);
						editor1[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,
								textSizeEditor);
						editor1[i].getBackground().setAlpha(150);
						editor1[i].setOnTouchListener(this);
						editor1[i].setText("");
					}
					preWordArray1 = pp.getPreWordsArray1();
					if (preWordArray1.size() != 0) {
						preLengthsArray1 = pp.getPreLengthsArray1();
						preLengthSizeArray1 = pp.getPreLengthSizeArray1();
						for (int prewordarray1 = 0; prewordarray1 < preWordArray1
								.size(); prewordarray1++) {

							int lenStart = preLengthsArray1.get(prewordarray1);
							String word1 = preWordArray1.get(prewordarray1);
							spaceAnswer1 = spaceAnswer1.replace(word1, "x");
							int wordSize = preLengthSizeArray1
									.get(prewordarray1);
							if (wordSize == 1) {
								paramslinearpre2 = paramslinear;
							} else if (wordSize == 2) {
								paramslinearpre2 = new LinearLayout.LayoutParams(
										((int) width / 9) + 5, (int) width / 12);
							} else {
								paramslinearpre2 = new LinearLayout.LayoutParams(
										(int) width / 6, (int) width / 12);
							}
							editor1[lenStart].setTypeface(face, 2);
							editor1[lenStart].setText(word1);
							editor1[lenStart].setEnabled(false);
							editor1[lenStart]
									.setBackgroundResource(R.drawable.btneditor);
							editor1[lenStart].setTextColor(Color.GREEN);
							editor1[lenStart]
									.setTextSize(TypedValue.COMPLEX_UNIT_PX,
											textSizeSpecial);
							editor1[lenStart].setLayoutParams(paramslinearpre2);
							editor1[lenStart].setTag("edited");
							editor1[lenStart].setGravity(Gravity.CENTER);
							editor1[lenStart].setPadding(-8, -7, -6, -2);

						}
					}
					for (int i = -1; (i = spaceAnswer1.indexOf(" ", i + 1)) != -1;) {
						editor1[i].setText(" ");
						editor1[i].setVisibility(View.INVISIBLE);
						editor1[i].setEnabled(false);
					}

					if (numbersHintLine1.size() > 0) {
						System.out.println("bolditititit");
						for (int k = 0; k < numbersHintLine1.size(); k++) {
							editor1[numbersHintLine1.get(k)].setEnabled(false);
							editor1[numbersHintLine1.get(k)]
									.setTextColor(Color.GREEN);
							editor1[numbersHintLine1.get(k)]
									.setText(spaceAnswer1.charAt(noOfHintLine1
											.get(k)) + "");
							editor1[numbersHintLine1.get(k)].setTag("edited");
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}

			if (ansline == 1) {
				length2 = answersArrayLength.get(1);
				if (length1 > 10 || length2 > 10) {
					paramslinear = new LinearLayout.LayoutParams(
							(int) width / 16, (int) width / 12);
					textSizeEditor = (int) width / 15;
					textSizeSpecial = (int) width / 15;
				} else {
					paramslinear = new LinearLayout.LayoutParams(
							(int) width / 11, (int) width / 11);
					textSizeEditor = (int) width / 12;
					textSizeSpecial = (int) width / 15;
				}
				answer2 = answersArrayWord.get(1);
				spaceAnswer2 = answer2;
				editor2 = new Button[length2];
				try {
					for (int i = 0; i < length2; i++) {
						editor2[i] = new Button(this);
						paramslinear.setMargins(1, 0, 0, 0);
						editor2[i].setLayoutParams(paramslinear);
						editor2[i].setPadding(-8, -7, -8, -1);
						EditorLinearDisplay2.addView(editor2[i]);
						editor2[i].setBackgroundResource(R.drawable.btneditor);
						editor2[i].setTypeface(face, 1);
						editor2[i].setTextColor(textColor);
						editor2[i].setTextSize(TypedValue.COMPLEX_UNIT_PX,
								textSizeEditor);
						editor2[i].getBackground().setAlpha(150);
						editor2[i].setOnTouchListener(this);
						editor2[i].setText("");
					}
					preWordArray2 = pp.getPreWordsArray2();
					if (preWordArray2.size() != 0) {
						preLengthsArray2 = pp.getPreLengthsArray2();
						preLengthSizeArray2 = pp.getPreLengthSizeArray2();
						for (int prewordarray2 = 0; prewordarray2 < preWordArray2
								.size(); prewordarray2++) {

							int lenStart = preLengthsArray2.get(prewordarray2);
							String word2 = preWordArray2.get(prewordarray2);
							spaceAnswer2 = spaceAnswer2.replace(word2, "x");
							int wordSize = preLengthSizeArray2
									.get(prewordarray2);
							if (wordSize == 2) {
								paramslinearpre2 = new LinearLayout.LayoutParams(
										(int) width / 8, (int) width / 12);
							} else {
								paramslinearpre2 = new LinearLayout.LayoutParams(
										(int) width / 6, (int) width / 12);
							}
							editor2[lenStart].setTypeface(face, 2);
							editor2[lenStart].setText(word2);
							editor2[lenStart].setEnabled(false);
							editor2[lenStart]
									.setBackgroundResource(R.drawable.btneditor);
							editor2[lenStart].setTextColor(Color.GREEN);
							editor2[lenStart]
									.setTextSize(TypedValue.COMPLEX_UNIT_PX,
											textSizeSpecial);
							editor2[lenStart].setLayoutParams(paramslinearpre2);
							editor2[lenStart].setTag("edited");
							editor2[lenStart].setGravity(Gravity.CENTER);
							editor2[lenStart].setPadding(-8, -7, -6, -2);
						}
					}

					for (int i = -1; (i = spaceAnswer2.indexOf(" ", i + 1)) != -1;) {
						editor2[i].setText(" ");

						editor2[i].setVisibility(View.INVISIBLE);
						editor2[i].setEnabled(false);
					}

					if (numbersHintLine2.size() > 0) {
						System.out.println("bolditititit");
						for (int k = 0; k < numbersHintLine2.size(); k++) {
							editor2[numbersHintLine2.get(k)].setEnabled(false);
							editor2[numbersHintLine2.get(k)]
									.setTextColor(Color.GREEN);
							editor2[numbersHintLine2.get(k)]
									.setText(spaceAnswer2.charAt(noOfHintLine2
											.get(k)) + "");
							editor2[numbersHintLine2.get(k)].setTag("edited");
						}
					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
	}

	private String builder1() {

		// TODO Auto-generated method stub
		StringBuilder sb1 = new StringBuilder();
		for (int i = 0; i < length1; i++) {
			sb1.append(editor1[i].getText().toString());
		}
		return sb1.toString();
	}

	private String builder2() {

		// TODO Auto-generated method stub
		StringBuilder sb2 = new StringBuilder();
		String returnValue;
		if (!answer2.equals("")) {
			for (int i = 0; i < length2; i++) {
				sb2.append(editor2[i].getText().toString());
			}
			returnValue = sb2.toString();
		} else {
			returnValue = "";
		}
		return returnValue;
	}

	@SuppressLint("NewApi")
	private void checker() {
		System.out.println("ccccccccccccccccccccccccchhhhecker11111");
		Animation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(50); // You can manage the time of the blink with
								// this parameter
		anim.setRepeatMode(Animation.REVERSE);
		anim.setRepeatCount(10);
		String anss2 = builder2();
		String anss1 = builder1();
		System.out.println("test111111111111.........builder1" + anss1);
		System.out.println("test1..................builder2" + anss2);
		boolean line1Boolean = false, line2Boolean = false, line2AnswerWrong = false, line1AnswerWrong = false;
		// checks whether the user's guess is of equal length with answer.
		if (answer1 != null) {
			if (anss1.length() == answer1.length()) {
				if (anss1.equals(answer1)) {
					line1Boolean = true;
				} else {
					line1AnswerWrong = true;
				}
			}
			if (answer2 == null) {
				line2Boolean = true;
				line2AnswerWrong = true;
			}
		}
		if (answer2 != null) {
			if (anss2.length() == answer2.length()) {
				if (anss2.equals(answer2)) {
					line2Boolean = true;
				} else {
					line2AnswerWrong = true;
				}
			}
		}

		if (line1Boolean && line2Boolean) {

			// stopImageClickEventAfterSuccess
			stopImageClick();
			// stopKeyBoarClickEventAfterSuccess
			stopKeyboardClick();
			pp.setFlag(1);

			// numbersHintLine1.clear();
			// numbersHintLine2.clear();
			values.add(random);
			app_preferences_random = getSharedPreferences("apps_random_1",
					MODE_PRIVATE);
			Editor sped = app_preferences_random.edit();
			sped.putInt(KEY_VAL_PREFIX + (values.size() - 1), random);
			sped.putInt("randomentered", 1500);
			sped.putInt("size", values.size());
			sped.commit();
			btnStciker.setText((values.size() + 1) + "");
			if (answer1 != null) {
				for (int i = 0; i < length1; i++) {
					editor1[i].setEnabled(false);
					editor1[i].setTextColor(Color.GREEN);
				}
			}
			if (answer1 != null) {
				for (int i = 0; i < length2; i++) {
					editor2[i].setEnabled(false);
					editor2[i].setTextColor(Color.GREEN);
				}
				// LinearLayout llll2 = (LinearLayout)
				// findViewById(R.id.btnlayout2);
				// llll2.startAnimation(anim);
			}
			System.out.println("test1..................show");
			showBaby();
			System.out.println("test1..................handler");
			// hhandler();
		}
		if (anss1.length() == answer1.length()
				&& anss2.length() == answer2.length()) {
			if (line1AnswerWrong || line2AnswerWrong) {
				// Toast.makeText(ActivityLevel1.this, "Wrong Answer",
				// Toast.LENGTH_SHORT).show();
				if (answer1 != null) {
					for (int i = 0; i < length1; i++) {
						if (editor1[i].getTag() != "edited") {
							editor1[i].setTextColor(Color.RED);
						} else
							continue;
					}
				}
				LinearLayout llll = (LinearLayout) findViewById(R.id.btnlayout1);
				llll.startAnimation(anim);
				if (answer2 != null) {
					for (int i = 0; i < length2; i++) {
						if (editor2[i].getTag() != "edited") {
							editor2[i].setTextColor(Color.RED);
						} else
							continue;
					}
					LinearLayout llll2 = (LinearLayout) findViewById(R.id.btnlayout2);
					llll2.startAnimation(anim);
				}
			}
		}
	}

	private void hhandler() {
		// TODO Auto-generated method stub
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			@Override
			public void run() {
				System.out.println("test1..................handler run method");

				removeImageResource();

				tr1.removeAllViews();
				tr2.removeAllViews();
				EditorLinearDisplay2.removeAllViews();
				EditorLinearDisplay1.removeAllViews();
				answer1 = "";
				answer2 = "";
				length1 = 0;
				length2 = 0;
				trickHint(1);
				spaceAnswer1 = "";
				spaceAnswer2 = "";
				ivZoom.setBackgroundResource(0);
				numbersHintLine1.clear();
				numbersHintLine2.clear();
				// showBaby();
				pp = null;
				HintEarnings(-5);
				initialRandomPuzzle();
			}

			private void removeImageResource() {
				// TODO Auto-generated method stub
				int stopImageClick1 = tr1.getChildCount();
				int stopImageClick2 = tr2.getChildCount();
				if (stopImageClick1 != 1) {
					for (int i = 1; i < stopImageClick1; i++) {
						tr1.getChildAt(i).setBackgroundResource(0);
					}
				}
				if (stopImageClick2 != 0) {
					for (int i = 0; i < stopImageClick2; i++) {
						tr2.getChildAt(i).setBackgroundResource(0);
					}
				}
			}
		}, 200);
	}

	private void showBaby() {
		// TODO Auto-generated method stub
		// final Dialog dialog1 = new Dialog(getParent());
		// final Custom_Dialog dialog1 = new Custom_Dialog(this,
		// R.style.myCoolDialog);
		dialog1 = new Custom_Dialog(this);
		dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// dialog1.requestWindowFeature(Window.FEATURE_OPTIONS_PANEL);
		// not able to add the size dynamically in the dialogbox.

		// android.widget.RelativeLayout.LayoutParams rlparams=new
		// RelativeLayout.LayoutParams(width, width);
		// succ.setLayoutParams(rlparams);

		// dialog1.setCanceledOnTouchOutside(true);
		dialog1.setCancelable(false);
		Animation anim = new AlphaAnimation(0.0f, 1.0f);
		anim.setDuration(30); // You can manage the time of the blink with
								// this parameter
		anim.setRepeatMode(Animation.REVERSE);
		anim.setRepeatCount(20);
		dialog1.setContentView(R.layout.superpb);
		// ImageView tvSuccess=(ImageView)dialog1.findViewById(R.id.tvSuccess);
		ivSuccessAnswerBox = (TextView) dialog1
				.findViewById(R.id.ivSuccessAnswerBox);
		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/dotted.ttf");
		ivSuccessAnswerBox.setTypeface(face);
		if (!answer2.equals("")) {
			ivSuccessAnswerBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 45);
			ivSuccessAnswerBox.setText(answer1 + "\n" + answer2);
		} else {
			ivSuccessAnswerBox.setTextSize(TypedValue.COMPLEX_UNIT_SP, 55);
			ivSuccessAnswerBox.setText(answer1);
		}

		ivSuccessAnswerBox.startAnimation(anim);

		SharedPreferences app_preferences_audio = getSharedPreferences(
				"apps_prefs_audio", MODE_PRIVATE);
		audio = app_preferences_audio.getInt("audio", 1);
		if (audio == 1) {
			mpCoin1 = MediaPlayer.create(ActivityLevel1.this, R.raw.coin_sound);
			mpCoin2 = MediaPlayer.create(ActivityLevel1.this, R.raw.coin_sound);
			mpCoin3 = MediaPlayer.create(ActivityLevel1.this, R.raw.coin_sound);
			mpCoin4 = MediaPlayer.create(ActivityLevel1.this, R.raw.coin_sound);
			mpCoin5 = MediaPlayer.create(ActivityLevel1.this, R.raw.coin_sound);
		}
		myView1 = (ImageView) dialog1.findViewById(R.id.button1);
		myView2 = (ImageView) dialog1.findViewById(R.id.button2);
		myView3 = (ImageView) dialog1.findViewById(R.id.button3);
		myView4 = (ImageView) dialog1.findViewById(R.id.button4);
		myView5 = (ImageView) dialog1.findViewById(R.id.button5);
		bpointSuperpb = (Button) dialog1.findViewById(R.id.bpointSuperpb);
		bpointSuperpb.setBackgroundResource(R.drawable.points5);
		rotation = AnimationUtils.loadAnimation(this, R.anim.cook);
		// rotation.setRepeatCount(5);
		points = AnimationUtils.loadAnimation(this, R.anim.points);
		Handler handler1 = new Handler();
		handler1.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (audio == 1) {
					mpCoin1.start();
				}
				myView1.setVisibility(View.VISIBLE);
				myView1.startAnimation(rotation);

			}
		}, 50);

		Handler handler2 = new Handler();
		handler2.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (audio == 1) {
					mpCoin2.start();
					mpCoin1.stop();
				}
				myView1.clearAnimation();
				myView2.startAnimation(rotation);
				myView2.setVisibility(View.VISIBLE);

			}
		}, 800);
		// myView1.startAnimation(rotation);

		Handler handler3 = new Handler();
		handler3.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (audio == 1) {
					mpCoin3.start();
					mpCoin2.stop();
				}
				myView2.clearAnimation();
				myView3.startAnimation(rotation);
				myView3.setVisibility(View.VISIBLE);

			}
		}, 1600);

		Handler handler4 = new Handler();
		handler4.postDelayed(new Runnable() {
			@Override
			public void run() {
				if (audio == 1) {
					mpCoin4.start();
					mpCoin3.stop();
				}
				myView3.clearAnimation();
				myView4.setVisibility(View.VISIBLE);
				myView4.startAnimation(rotation);
			}
		}, 2400);

		Handler handler5 = new Handler();
		handler5.postDelayed(new Runnable() {

			@Override
			public void run() {
				if (audio == 1) {
					mpCoin5.start();
					mpCoin4.stop();
				}

				myView4.clearAnimation();
				myView5.setVisibility(View.VISIBLE);
				myView5.startAnimation(rotation);
			}
		}, 3200);

		dialog1.show();
		Handler handler6 = new Handler();
		handler6.postDelayed(new Runnable() {

			@Override
			public void run() {
				myView5.clearAnimation();
				ivSuccessAnswerBox.clearAnimation();
				if (audio == 1) {
					mpCoin5.stop();
				}
				bpointSuperpb.startAnimation(points);
			}
		}, 4000);

		Handler handler7 = new Handler();
		handler7.postDelayed(new Runnable() {

			@Override
			public void run() {
				bpointSuperpb.clearAnimation();
				dialog1.dismiss();
				hhandler();
			}
		}, 4800);
	}

	private void startKeyboardClick() {
		// TODO Auto-generated method stub
		int stopKeyboardClick1 = keyboardLine1.getChildCount();
		int stopKeyboardClick2 = keyboardLine2.getChildCount();
		int stopKeyboardClick3 = keyboardLine3.getChildCount();
		if (stopKeyboardClick1 != 0) {
			for (int i = 0; i < stopKeyboardClick1; i++) {
				keyboardLine1.getChildAt(i).setEnabled(true);
			}
		}
		if (stopKeyboardClick2 != 0) {
			for (int i = 0; i < stopKeyboardClick2; i++) {
				keyboardLine2.getChildAt(i).setEnabled(true);
			}
		}
		if (stopKeyboardClick3 != 0) {
			for (int i = 0; i < stopKeyboardClick3; i++) {
				keyboardLine3.getChildAt(i).setEnabled(true);
			}
		}
	}

	private void stopKeyboardClick() {
		// TODO Auto-generated method stub
		int stopKeyboardClick1 = keyboardLine1.getChildCount();
		int stopKeyboardClick2 = keyboardLine2.getChildCount();
		int stopKeyboardClick3 = keyboardLine3.getChildCount();
		if (stopKeyboardClick1 != 0) {
			for (int i = 0; i < stopKeyboardClick1; i++) {
				keyboardLine1.getChildAt(i).setEnabled(false);
			}
		}
		if (stopKeyboardClick2 != 0) {
			for (int i = 0; i < stopKeyboardClick2; i++) {
				keyboardLine2.getChildAt(i).setEnabled(false);
			}
		}
		if (stopKeyboardClick3 != 0) {
			for (int i = 0; i < stopKeyboardClick3; i++) {
				keyboardLine3.getChildAt(i).setEnabled(false);
			}
		}
	}

	private void stopImageClick() {
		// TODO Auto-generated method stub

		int stopImageClick1 = tr1.getChildCount();
		int stopImageClick2 = tr2.getChildCount();
		if (stopImageClick1 != 1) {
			for (int i = 1; i < stopImageClick1; i++) {
				tr1.getChildAt(i).setEnabled(false);
			}
		}
		if (stopImageClick2 != 0) {
			for (int i = 0; i < stopImageClick2; i++) {
				tr2.getChildAt(i).setEnabled(false);
			}
		}
	}

	private void SetDisplayWidhtHeight() {
		// TODO Auto-generated method stub
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		height = displaymetrics.heightPixels;
		width = displaymetrics.widthPixels;
		rl = (RelativeLayout) findViewById(R.id.rlMain);
		rl.getLayoutParams().height = height;
		rl.getLayoutParams().width = width;
	}

	private void stubbing() {

		ViewStub stubKeyboard = (ViewStub) findViewById(R.id.stubKeyboard);
		inflatedKeyboard = stubKeyboard.inflate();

		ViewStub stubImages = (ViewStub) findViewById(R.id.stubImages);
		inflatedImage = stubImages.inflate();

		ViewStub stubEditor = (ViewStub) findViewById(R.id.stubEditor);
		inflatedEditor = stubEditor.inflate();

		myFrameKeyboard = (TableLayout) inflatedKeyboard
				.findViewById(R.id.myFrameKeyboard);

		EditorLinearDisplay1 = (LinearLayout) inflatedEditor
				.findViewById(R.id.btnlayout1);
		EditorLinearDisplay2 = (LinearLayout) inflatedEditor
				.findViewById(R.id.btnlayout2);
		keyboardLine1 = (LinearLayout) inflatedKeyboard
				.findViewById(R.id.keyboardline1);
		keyboardLine2 = (LinearLayout) inflatedKeyboard
				.findViewById(R.id.keyboardline2);
		keyboardLine3 = (LinearLayout) inflatedKeyboard
				.findViewById(R.id.keyboardline3);
		ivZoom = (ImageView) inflatedImage.findViewById(R.id.expanded_image);
	}

	private void imaging(int i) {
		// TODO Auto-generated method stub
		if (i == 1) {

			ImageView iv1 = new ImageView(this);
			iv1.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_1", "drawable", getPackageName()));
			TableRow.LayoutParams imageParams = new TableRow.LayoutParams();
			imageParams.height = width - 150;
			imageParams.width = width - 100;
			imageParams.topMargin = 10;
			iv1.setTag("img_" + puzzle_id + "_1");
			iv1.setLayoutParams(imageParams);
			tr1.addView(iv1);
		} else if (i == 2) {
			iv1 = new ImageView(this);
			iv1.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_1", "drawable", getPackageName()));
			iv2 = new ImageView(this);
			iv2.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_2", "drawable", getPackageName()));
			TableRow.LayoutParams imageParams = new TableRow.LayoutParams();
			imageParams.height = width / 2 + 10;
			imageParams.width = width / 2 - 17;
			imageParams.topMargin = 7;
			imageParams.leftMargin = 5;
			imageParams.gravity = Gravity.CENTER;
			iv1.setTag("img_" + puzzle_id + "_1");
			iv2.setTag("img_" + puzzle_id + "_2");
			iv1.setLayoutParams(imageParams);
			iv2.setLayoutParams(imageParams);
			tr1.addView(iv1);
			tr1.addView(iv2);
			iv1.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv1);
				}
			});
			iv2.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv2);
				}
			});

		} else if (i == 3) {
			iv1 = new ImageView(this);
			iv1.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_1", "drawable", getPackageName()));
			iv2 = new ImageView(this);
			iv2.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_2", "drawable", getPackageName()));
			iv3 = new ImageView(this);
			iv3.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_3", "drawable", getPackageName()));
			TableRow.LayoutParams imageParams = new TableRow.LayoutParams();
			imageParams.height = width / 3 + 5;
			imageParams.width = width / 3 - 15;
			imageParams.topMargin = 7;
			imageParams.leftMargin = 5;
			imageParams.gravity = Gravity.CENTER;
			iv1.setTag("img_" + puzzle_id + "_1");
			iv2.setTag("img_" + puzzle_id + "_2");
			iv3.setTag("img_" + puzzle_id + "_3");
			iv1.setLayoutParams(imageParams);
			iv2.setLayoutParams(imageParams);
			iv3.setLayoutParams(imageParams);
			tr1.addView(iv1);
			tr1.addView(iv2);
			tr1.addView(iv3);
			iv1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv1);
				}
			});
			iv2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv2);
				}
			});
			iv3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(arg0);
				}
			});
		} else if (i == 4) {
			iv1 = new ImageView(this);
			iv1.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_1", "drawable", getPackageName()));
			iv2 = new ImageView(this);
			iv2.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_2", "drawable", getPackageName()));
			iv3 = new ImageView(this);
			iv3.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_3", "drawable", getPackageName()));
			iv4 = new ImageView(this);
			iv4.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_4", "drawable", getPackageName()));
			TableRow.LayoutParams imageParams = new TableRow.LayoutParams();
			imageParams.height = width / 3;
			imageParams.width = width / 3 - 15;
			imageParams.topMargin = 5;
			imageParams.leftMargin = 5;
			imageParams.gravity = Gravity.CENTER;
			iv1.setTag("img_" + puzzle_id + "_1");
			iv2.setTag("img_" + puzzle_id + "_2");
			iv3.setTag("img_" + puzzle_id + "_3");
			iv4.setTag("img_" + puzzle_id + "_4");
			iv1.setLayoutParams(imageParams);
			iv2.setLayoutParams(imageParams);
			iv3.setLayoutParams(imageParams);
			iv4.setLayoutParams(imageParams);
			tr1.addView(iv1);
			tr1.addView(iv2);
			tr1.addView(iv3);
			tr2.addView(iv4);
			iv1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv1);
				}
			});
			iv2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv2);
				}
			});
			iv3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(arg0);
				}
			});
			iv4.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(arg0);
				}
			});
		} else if (i == 5) {
			iv1 = new ImageView(this);
			iv1.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_1", "drawable", getPackageName()));
			iv2 = new ImageView(this);
			iv2.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_2", "drawable", getPackageName()));
			iv3 = new ImageView(this);
			iv3.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_3", "drawable", getPackageName()));
			iv4 = new ImageView(this);
			iv4.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_4", "drawable", getPackageName()));
			iv5 = new ImageView(this);
			iv5.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_5", "drawable", getPackageName()));
			TableRow.LayoutParams imageParams = new TableRow.LayoutParams();
			imageParams.height = width / 3;
			imageParams.width = width / 3 - 15;
			imageParams.topMargin = 5;
			imageParams.leftMargin = 5;
			imageParams.gravity = Gravity.CENTER;
			iv1.setTag("img_" + puzzle_id + "_1");
			iv2.setTag("img_" + puzzle_id + "_2");
			iv3.setTag("img_" + puzzle_id + "_3");
			iv4.setTag("img_" + puzzle_id + "_4");
			iv5.setTag("img_" + puzzle_id + "_5");
			iv1.setLayoutParams(imageParams);
			iv2.setLayoutParams(imageParams);
			iv3.setLayoutParams(imageParams);
			iv4.setLayoutParams(imageParams);
			iv5.setLayoutParams(imageParams);
			tr1.addView(iv1);
			tr1.addView(iv2);
			tr1.addView(iv3);
			tr2.addView(iv4);
			tr2.addView(iv5);
			iv1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv1);
				}
			});
			iv2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv2);
				}
			});
			iv3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(arg0);
				}
			});
			iv4.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(arg0);
				}
			});
			iv5.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv5);
				}
			});
		} else if (i == 6) {
			iv1 = new ImageView(this);
			iv1.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_1", "drawable", getPackageName()));
			iv2 = new ImageView(this);
			iv2.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_2", "drawable", getPackageName()));
			iv3 = new ImageView(this);
			iv3.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_3", "drawable", getPackageName()));
			iv4 = new ImageView(this);
			iv4.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_4", "drawable", getPackageName()));
			iv5 = new ImageView(this);
			iv5.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_5", "drawable", getPackageName()));
			iv6 = new ImageView(this);
			iv6.setBackgroundResource(this.getResources().getIdentifier(
					"img_" + puzzle_id + "_6", "drawable", getPackageName()));
			TableRow.LayoutParams imageParams = new TableRow.LayoutParams();
			imageParams.height = width / 3;
			imageParams.width = width / 3 - 15;
			imageParams.topMargin = 5;
			imageParams.leftMargin = 5;
			// imageParams.gravity = Gravity.CENTER;
			iv1.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv2.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv3.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv4.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv5.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv6.setScaleType(ImageView.ScaleType.CENTER_CROP);
			iv1.setLayoutParams(imageParams);
			iv2.setLayoutParams(imageParams);
			iv3.setLayoutParams(imageParams);
			iv4.setLayoutParams(imageParams);
			iv5.setLayoutParams(imageParams);
			iv6.setLayoutParams(imageParams);
			iv1.setTag("img_" + puzzle_id + "_1");
			iv2.setTag("img_" + puzzle_id + "_2");
			iv3.setTag("img_" + puzzle_id + "_3");
			iv4.setTag("img_" + puzzle_id + "_4");
			iv5.setTag("img_" + puzzle_id + "_5");
			iv6.setTag("img_" + puzzle_id + "_6");
			tr1.addView(iv1);
			tr1.addView(iv2);
			tr1.addView(iv3);
			tr2.addView(iv4);
			tr2.addView(iv5);
			tr2.addView(iv6);
			iv1.setVisibility(View.VISIBLE);
			iv2.setVisibility(View.VISIBLE);
			iv3.setVisibility(View.VISIBLE);
			iv4.setVisibility(View.VISIBLE);
			iv5.setVisibility(View.VISIBLE);
			iv6.setVisibility(View.VISIBLE);

			iv1.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv1);
				}
			});
			iv2.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv2);
				}
			});
			iv3.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(arg0);
				}
			});
			iv4.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(arg0);
				}
			});
			iv5.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(iv5);
				}
			});
			iv6.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					ImageEvent(arg0);
				}
			});
		}
	}

	// images click events...

	private void editorEnter(View arg02) {
		// TODO Auto-generated method stub
		for (int i = 0; i < length1; i++) {
			if (arg02 == editor1[i]) {
				editor1[i].setText("");
			}
			if (editor1[i].getTag() != "edited") {
				editor1[i].setTextColor(textColor);
			} else
				continue;
		}
		for (int i = 0; i < length2; i++) {
			if (arg02 == editor2[i]) {
				editor2[i].setText("");
			}
			if (editor2[i].getTag() != "edited") {
				editor2[i].setTextColor(textColor);
			} else
				continue;

		}
	}

	private void keyboardEnter(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == b_a) {
			alpha = "A";
			doit(alpha);
		}
		if (arg0 == b_b) {
			alpha = "B";
			doit(alpha);
		}
		if (arg0 == b_c) {
			alpha = "C";
			doit(alpha);
		}
		if (arg0 == b_d) {
			alpha = "D";
			doit(alpha);
		}
		if (arg0 == b_e) {
			alpha = "E";
			doit(alpha);
		}
		if (arg0 == b_f) {
			alpha = "F";
			doit(alpha);
		}
		if (arg0 == b_g) {
			alpha = "G";
			doit(alpha);
		}
		if (arg0 == b_h) {
			alpha = "H";
			doit(alpha);
		}
		if (arg0 == b_i) {
			alpha = "I";
			doit(alpha);
		}
		if (arg0 == b_j) {
			alpha = "J";
			doit(alpha);
		}
		if (arg0 == b_k) {
			alpha = "K";
			doit(alpha);
		}
		if (arg0 == b_l) {
			alpha = "L";
			doit(alpha);
		}
		if (arg0 == b_m) {
			alpha = "M";
			doit(alpha);
		}
		if (arg0 == b_n) {
			alpha = "N";
			doit(alpha);
		}
		if (arg0 == b_o) {
			alpha = "O";
			doit(alpha);
		}
		if (arg0 == b_p) {
			alpha = "P";
			doit(alpha);
		}
		if (arg0 == b_q) {
			alpha = "Q";
			doit(alpha);
		}
		if (arg0 == b_r) {
			alpha = "R";
			doit(alpha);
		}
		if (arg0 == b_s) {
			alpha = "S";
			doit(alpha);
		}
		if (arg0 == b_t) {
			alpha = "T";
			doit(alpha);
		}
		if (arg0 == b_u) {
			alpha = "U";
			doit(alpha);
		}
		if (arg0 == b_v) {
			alpha = "V";
			doit(alpha);
		}
		if (arg0 == b_w) {
			alpha = "W";
			doit(alpha);
		}
		if (arg0 == b_x) {
			alpha = "X";
			doit(alpha);
		}
		if (arg0 == b_y) {
			alpha = "Y";
			doit(alpha);
		}
		if (arg0 == b_z) {
			alpha = "Z";
			doit(alpha);
		}
	}

	private void doit(String alpha) {
		// TODO Auto-generated method stub
		String ans1 = builder1();
		// for bringing back white color...

		if (ans1.length() < answer1.length()) {
			for (int i = 0; i < length1; i++) {
				// editor1[i].setTextColor(Color.WHITE);
				if (editor1[i].getText().toString().equals("")) {
					editor1[i].setTextColor(textColor);
				}
			}

			for (int i = 0; i < length1; i++) {
				// editor1[i].setTextColor(Color.WHITE);
				if (editor1[i].getText().toString().equals("")) {
					editor1[i].setText(alpha);
					break;
				}
			}
		}

		if (ans1.length() == answer1.length()) {
			for (int j = 0; j < length2; j++) {
				if (editor2[j].getText().toString().equals("")) {
					editor2[j].setText(alpha);
					break;
				}
			}
		}
		checker();
	}

	private void ImageEvent(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == iv1 || arg0 == iv2 || arg0 == iv3 || arg0 == iv4
				|| arg0 == iv5 || arg0 == iv6) {
			String name = (String) arg0.getTag();
			// mp = MediaPlayer.create(this, R.raw.beep);
			if (isclicked) {
				// mp.start();
				zoomImage(arg0, name);
			}
		}
	}

	private void zoomImage(View arg0, String name) {
		// TODO Auto-generated method stub
		isclicked = false;
		arg1 = arg0;

		ivZoom.setBackgroundResource(this.getResources().getIdentifier(name,
				"drawable", getPackageName()));

		arg1.setVisibility(View.INVISIBLE);

		/*
		 * AnimationSet rootSet = new AnimationSet(true); TranslateAnimation
		 * transX = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0,
		 * Animation.RELATIVE_TO_SELF, 2, 0,0,0,0); transX.setStartOffset(0);
		 * transX.setDuration(3000); rootSet.addAnimation(transX);
		 */

		// ivZoom.startAnimation(new Animation);
		ivZoom.setVisibility(View.VISIBLE);

		FrameLayout.LayoutParams imageParams = new LayoutParams(width - 25,
				(height / 2), Gravity.CENTER);
		// imageParams.bottomMargin = 50;
		// imageParams.leftMargin = 30;
		// imageParams.rightMargin = 27;
		// imageParams.topMargin = 30;
		ivZoom.setLayoutParams(imageParams);
		ivZoom.setVisibility(View.VISIBLE);
		/*
		 * ScaleAnimation scale=new ScaleAnimation(0,(width-30)/2, 0,
		 * (width-45)/2); scale.setInterpolator(new DecelerateInterpolator());
		 * scale.setDuration(1000); rootSet.addAnimation(transX);
		 * ivZoom.startAnimation(rootSet);
		 */
		// mp = MediaPlayer.create(this, R.raw.beep);
		ivZoom.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ivZoom.setVisibility(View.INVISIBLE);
				// ivZoom.setBackgroundResource(0);
				arg1.setVisibility(View.VISIBLE);

				// mp.start();
				isclicked = true;
			}
		});
	}

	public class Custom_Dialog extends Dialog {

		protected Custom_Dialog(Context context) {
			super(context);
			// TODO Auto-generated constructor stub
		}

	}

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		// TODO Auto-generated method stub
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			editorEnter(arg0);
			keyboardEnter(arg0);
			return true;
		}
		return false;
	}
}
