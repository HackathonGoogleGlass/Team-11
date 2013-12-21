package com.example.camera;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.speech.RecognizerIntent;


public class SaveActivity extends Activity {

	private static final int TAKE_PICTURE_REQUEST = 112;
	private static final int GET_SPEECH=211;
	
	private URI  _fileUri;
	//private String _imageFileName;

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
	    if (requestCode == TAKE_PICTURE_REQUEST && resultCode == RESULT_OK) 
	    {
	    	
	     //   Bundle bundle = data.ge getIntent().getExtras();
			Uri a = data.getData();
			Uri b = a;
//	        if(data.getda(MediaStore.EXTRA_OUTPUT)!= null)
//	        {
//	            //TODO here get the string stored in the string variable and do 
//	                    // setText() on userName 
//	        	Uri uri  = (Uri)data.get(MediaStore.EXTRA_OUTPUT);
//	        }
	    	
	       // processPictureWhenReady(picturePath);
	    	//Uri uri = (Uri)data.getExtras().get(MediaStore.EXTRA_OUTPUT);
	    	//_fileUri =  uri;
	    	 getSpeech();


	    }
	    else if(requestCode == GET_SPEECH && resultCode == RESULT_OK)
	    {
			List<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			//_imageFileName = results.get(0);	 
			    	 
			File from = new File(_fileUri);
			//File to = new File(sdcard,"to.txt");
			//from.renameTo(results.get(0));
	
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
		String  a = Environment.getExternalStorageDirectory().getAbsolutePath() + "\\StuffFinder";
		Uri uri = Uri.parse(a);
	    Intent photoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	    photoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
	    startActivityForResult(photoIntent, TAKE_PICTURE_REQUEST);
	}
	
//
//	private File createImageFile() throws IOException {
//	    // Create an image file name
//
//	    //String imageFileName = "JPEG_" + timeStamp + "_";
//	    File storageDir = getExternalFilesDir(null);
//	    File image = File.createTempFile(
//	    	_imageFileName,  /* prefix */
//	        ".jpg",         /* suffix */
//	        storageDir      /* directory */
//	    ); 
//	    //image.createNewFile();
//	    // Save a file: path for use with ACTION_VIEW intents
//	    return image;
//	}
	
//	private void processPictureWhenReady(final String picturePath) {
//	    final File pictureFile = new File(picturePath);
//	    
//	    
////	    if (pictureFile.exists()) {
////	        // The picture is ready; process it.
////	    	//???
////	    	
////	    } else {
//	        // The file does not exist yet. Before starting the file observer, you
//	        // can update your UI to let the user know that the application is
//	        // waiting for the picture (for example, by displaying the thumbnail
//	        // image and a progress indicator).
//
//	        final File parentDirectory = pictureFile.getParentFile();
//	        FileObserver observer = new FileObserver(parentDirectory.getPath()) 
//	        {
//	            // Protect against additional pending events after CLOSE_WRITE is
//	            // handled.
//	            private boolean isFileWritten;
//
//	            @Override
//	            public void onEvent(int event, String path) {
//	                if (!isFileWritten) {
//	                    // For safety, make sure that the file that was created in
//	                    // the directory is actually the one that we're expecting.
//	                    File affectedFile = new File(parentDirectory, path);
//	                    isFileWritten = (event == FileObserver.CLOSE_WRITE
//	                            && affectedFile.equals(pictureFile));
//
//	                    if (isFileWritten) {
//	                        stopWatching();
//
//	                        // Now that the file is ready, recursively call
//	                        // processPictureWhenReady again (on the UI thread).
//	                        runOnUiThread(new Runnable() {
//	                            @Override
//	                            public void run() {
//	                                processPictureWhenReady(picturePath);
//	                            }
//	                        });
//	                    }
//	                }
//	            }
//	        };
	        
	        
	        //observer.startWatching();
	        
	        //SetCardView();
//	    }
//	}

}
