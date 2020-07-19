package com.stackandroid.speechtotext;
import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private final int SPEECH_RECOGNITION_CODE = 1;

	private TextView txtOutput;
	private ImageButton btnMicrophone;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		txtOutput = (TextView) findViewById(R.id.txt_output);
		btnMicrophone = (ImageButton) findViewById(R.id.btn_mic);

		btnMicrophone.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				startSpeechToText();
			}
		});
	}

	/**
	 * Start speech to text intent. This opens up Google Speech Recognition API dialog box to listen the speech input.
	 * */
	 private void startSpeechToText() {
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				"Speak something...");
		try {
			startActivityForResult(intent, SPEECH_RECOGNITION_CODE);
		} catch (ActivityNotFoundException a) {
			Toast.makeText(getApplicationContext(),
					"Sorry! Speech recognition is not supported in this device.",
					Toast.LENGTH_SHORT).show();
		}
	 }

	 /**
	  * Callback for speech recognition activity
	  * */
	 @Override
	 protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		 super.onActivityResult(requestCode, resultCode, data);

		 switch (requestCode) {
		 case SPEECH_RECOGNITION_CODE: {
			 if (resultCode == RESULT_OK && null != data) {

				 ArrayList<String> result = data
						 .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
				 String text = result.get(0);
				 txtOutput.setText(text);
			 }
			 break;
		 }

		 }
	 }
}