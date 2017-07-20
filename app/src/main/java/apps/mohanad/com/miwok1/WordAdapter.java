package apps.mohanad.com.miwok1;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by mohanad on 05/05/17.
 */


public class WordAdapter extends ArrayAdapter<Word> {

    //the color  id of the text container
    private int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> words , int mColorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        this.mColorResourceId=mColorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

        }

        // Get the {@link Word} object located at this position in the list
        Word currentWord = getItem(position);

        //find the textview in the list_item xml to display new data
        TextView english = (TextView)listItemView.findViewById(R.id.default_text_view) ;

        //set the english textView to be english word in the Word opject
        english.setText(currentWord.getEnglishTranslation());

        //find the textview in the list_item xml to display new data
        TextView miwok = (TextView)listItemView.findViewById(R.id.miwok_text_view) ;

        //set the english textView to be english word in the Word opject
        miwok.setText(currentWord.getMiwokTranslation());

        //check if the word have an image or not
        String msg=currentWord.getImageResourceId()+" ";

        //find the image view in the list_item xml to display new image
        ImageView img= (ImageView)listItemView.findViewById(R.id.image);

        //check if the word have an image
        if (currentWord.getImageResourceId()!=0){
            //set the image to be the new word image
            img.setImageResource(currentWord.getImageResourceId());

            //make sure that the view is visible
            img.setVisibility(View.VISIBLE);
        }
         else
        //hide the image (gone)
            img.setVisibility(View.GONE);

        //set the word background color and the image background color
        //get the list item word layout
        LinearLayout wordLayout=(LinearLayout)listItemView.findViewById(R.id.text_container);
        //set the color of the background
        wordLayout.setBackgroundResource(mColorResourceId);

        //display the playing icon
        ImageView playing_icon = (ImageView)listItemView.findViewById(R.id.playing_icn);
        playing_icon.setImageResource(R.drawable.ic_play_arrow_white_18dp);

        return  listItemView;
    }
}
