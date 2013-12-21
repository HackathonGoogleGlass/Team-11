package com.example.camera;

import java.io.File;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.speech.RecognizerIntent;

public class FindActivity extends Activity
{
        private static final int GET_SPEECH=211;
        
        @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        getSpeech();
    }

        @Override
    public void onResume() 
    {
        super.onResume();       
    }
        
        protected void onActivityResult(int requestCode, int resultCode, Intent data) 
        {
                if(requestCode != GET_SPEECH || resultCode != RESULT_OK) return;
                
                List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                
                File picturesDir = new File(getExternalFilesDir(null).getAbsolutePath());
                File[] files = picturesDir.listFiles(new PictureFileNameFilter(results.get(0)));
                File file = files[0];
                
                if(file.exists())
                {
                        Bitmap loadedBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                }
                
                return;
        }
        
         private void getSpeech()
            {
                        Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech Demo");
                    FindActivity.this.startActivityForResult(voiceIntent, GET_SPEECH);        
            }
}