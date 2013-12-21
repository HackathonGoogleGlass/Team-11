package com.example.camera;

import java.util.List;

import android.view.View;
import android.view.ViewGroup;

import com.google.android.glass.app.Card;
import com.google.android.glass.widget.CardScrollAdapter;

public class ExampleCardScrollAdapter extends CardScrollAdapter {

	private List<Card> _mCards;
	
	public ExampleCardScrollAdapter(List<Card> mCards)
	{
		_mCards=mCards;
	}
	
    @Override
    public int findIdPosition(Object id) {
        return -1;
    }

    @Override
    public int findItemPosition(Object item) {
        return _mCards.indexOf(item);
    }

    @Override
    public int getCount() {
        return _mCards.size();
    }

    @Override
    public Object getItem(int position) {
        return _mCards.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return _mCards.get(position).toView();
    }

}
