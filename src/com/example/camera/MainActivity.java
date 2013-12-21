package com.example.camera;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
//import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.os.FileObserver;
//import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
//import com.google.android.glass.media.CameraManager;
import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

public class MainActivity extends Activity {

//	private static final int TAKE_PICTURE_REQUEST = 1;
	private List<Card> mCards;
    private CardScrollView mCardScrollView;
    private String PicturesDirPath = "/mny/sdcard/dcim/camera/";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);


      //takePicture();
      
    }
    
//    private void SetCardView(){
//        createCards();
//        mCardScrollView = new CardScrollView(this);
//        ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter();
//        mCardScrollView.setAdapter(adapter);
//        mCardScrollView.activate();
//        setContentView(mCardScrollView);
//    }
    
    private void createCards() {
        mCards = new ArrayList<Card>();

        Card card;

        card = new Card(this);
        card.setText(PicturesDirPath);
       // card.setInfo("I'm the footer!");
        mCards.add(card);
        
        File dir = new File(PicturesDirPath);
        File[] filelist = dir.listFiles();
        for (File f : filelist)
        {
        	Card tempCard = new Card(this);
        	tempCard.setText(f.getName());
        	Uri uri = Uri.fromFile(f);
        	tempCard.addImage(uri);
        	
        	// first card is working
        	mCards.add(tempCard);
        }
    }
	
//	private void takePicture() {
//	    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//	    startActivityForResult(intent, TAKE_PICTURE_REQUEST);
//	}
//	
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    if (requestCode == TAKE_PICTURE_REQUEST && resultCode == RESULT_OK) {
//	        String picturePath = data.getStringExtra(CameraManager.EXTRA_PICTURE_FILE_PATH);
//	        processPictureWhenReady(picturePath);
//	    }
//
//	    super.onActivityResult(requestCode, resultCode, data);
//	}
//	
//	private void processPictureWhenReady(final String picturePath) {
//	    final File pictureFile = new File(picturePath);
//	    PicturesDirPath = picturePath;
//	    
//	    if (pictureFile.exists()) {
//	        // The picture is ready; process it.
//	    	//???
//	    	
//	    } else {
//	        // The file does not exist yet. Before starting the file observer, you
//	        // can update your UI to let the user know that the application is
//	        // waiting for the picture (for example, by displaying the thumbnail
//	        // image and a progress indicator).
//
//	        final File parentDirectory = pictureFile.getParentFile();
//	        FileObserver observer = new FileObserver(parentDirectory.getPath()) {
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
//	        
//	        
//	        //observer.startWatching();
//	        
//	        SetCardView();
//	    }
//	}



	private class ExampleCardScrollAdapter extends CardScrollAdapter {
	    @Override
	    public int findIdPosition(Object id) {
	        return -1;
	    }
	
	    @Override
	    public int findItemPosition(Object item) {
	        return mCards.indexOf(item);
	    }
	
	    @Override
	    public int getCount() {
	        return mCards.size();
	    }
	
	    @Override
	    public Object getItem(int position) {
	        return mCards.get(position);
	    }
	
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        return mCards.get(position).toView();
	    }
	}
}