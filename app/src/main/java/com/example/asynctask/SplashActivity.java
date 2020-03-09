package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getName();
    private ProgressBar progressBar;
    private TextView textView;
    private static int SPLASH_TIME = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
        new LoadingProgress().execute();
    }

    private class LoadingProgress extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            int progressStatus = 0;
            while (progressStatus < SPLASH_TIME) {
                try {
                    Thread.sleep(1);
                    publishProgress(progressStatus);
                    progressStatus++;
                } catch (InterruptedException e) {
                    Log.i(TAG, "Thread is interupted!");
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.d(TAG, String.valueOf(values[0]));
            int percentage = (values[0] / SPLASH_TIME) * 100;
            progressBar.setProgress(values[0]);
            textView.setText(percentage + " %");
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textView.setText("Finished!");
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}
