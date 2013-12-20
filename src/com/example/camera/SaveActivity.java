package com.example.camera;

import java.io.File;
import java.util.List;

import com.google.android.glass.media.CameraManager;

import android.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.speech.RecognizerIntent;


public class SaveActivity extends Activity {

	private static final int TAKE_PICTURE_REQUEST = 112;
	private static final int GET_SPEECH=211;
	private String PicturesDirPath = "/mny/sdcard/dcim/camera/";

    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        takePicture();
    }

    @Override
    public void onResume() 
    {
        super.onResume();       
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == TAKE_PICTURE_REQUEST && resultCode == RESULT_OK) {
	        String picturePath = data.getStringExtra(CameraManager.EXTRA_PICTURE_FILE_PATH);
	        processPictureWhenReady(picturePath);
	        getSpeech();
	    }
	    else if(requestCode == GET_SPEECH && resultCode == RESULT_OK)
	    {
			List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			//_callback.SpeechCallback(results.get(0));
	    	
	    }
	    super.onActivityResult(requestCode, resultCode, data);
	}
	
	
    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the Activity.
        finish();
    }    
    
    private void getSpeech()
    {
		Intent voiceIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    	voiceIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    	voiceIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech Demo");
    	SaveActivity.this.startActivityForResult(voiceIntent, GET_SPEECH);	
    }
    
	private void takePicture() {
	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    startActivityForResult(intent, TAKE_PICTURE_REQUEST);
	}
	

	
	private void processPictureWhenReady(final String picturePath) {
	    final File pictureFile = new File(picturePath);
	    PicturesDirPath = picturePath;
	    
	    if (pictureFile.exists()) {
	        // The picture is ready; process it.
	    	//???
	    	
	    } else {
	        // The file does not exist yet. Before starting the file observer, you
	        // can update your UI to let the user know that the application is
	        // waiting for the picture (for example, by displaying the thumbnail
	        // image and a progress indicator).

	        final File parentDirectory = pictureFile.getParentFile();
	        FileObserver observer = new FileObserver(parentDirectory.getPath()) 
	        {
	            // Protect against additional pending events after CLOSE_WRITE is
	            // handled.
	            private boolean isFileWritten;

	            @Override
	            public void onEvent(int event, String path) {
	                if (!isFileWritten) {
	                    // For safety, make sure that the file that was created in
	                    // the directory is actually the one that we're expecting.
	                    File affectedFile = new File(parentDirectory, path);
	                    isFileWritten = (event == FileObserver.CLOSE_WRITE
	                            && affectedFile.equals(pictureFile));

	                    if (isFileWritten) {
	                        stopWatching();

	                        // Now that the file is ready, recursively call
	                        // processPictureWhenReady again (on the UI thread).
	                        runOnUiThread(new Runnable() {
	                            @Override
	                            public void run() {
	                                processPictureWhenReady(picturePath);
	                            }
	                        });
	                    }
	                }
	            }
	        };
	        
	        
	        //observer.startWatching();
	        
	        //SetCardView();
	    }
	}

}
