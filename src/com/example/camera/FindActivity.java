package com.example.camera;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.camera.ExampleCardScrollAdapter;
import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollView;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;

public class FindActivity extends Activity
{
	private static final int GET_SPEECH=211;
	private List<Card> _mCards;
    private CardScrollView mCardScrollView;
    
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
			//Bitmap loadedBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
			createCards(file);
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
	
	  private void SetCardView()
	  {
       // createCards();
        mCardScrollView = new CardScrollView(this);
        ExampleCardScrollAdapter adapter = new ExampleCardScrollAdapter(_mCards);
        mCardScrollView.setAdapter(adapter);
        mCardScrollView.activate();
        setContentView(mCardScrollView);
    }
	    
    private void createCards(File file) 
    {
        _mCards = new ArrayList<Card>();

        Card card;

        card = new Card(this);
        
        //take the first part of the file name ( the second part is a stupid numbers
        card.setText(file.getName().split(".-.")[0]);
    	Uri uri = Uri.fromFile(file);
    	card.addImage(uri);
       // card.setInfo("I'm the footer!");
        _mCards.add(card);
        
        
        SetCardView();
//        File dir = new File(PicturesDirPath);
//        File[] filelist = dir.listFiles();
//        for (File f : filelist)
//        {
//        	Card tempCard = new Card(this);
//        	tempCard.setText(f.getName());
//        	Uri uri = Uri.fromFile(f);
//        	tempCard.addImage(uri);
//        	
//        	// first card is working
//        	_mCards.add(tempCard);
//        }
    }
}
