package apps.mohanad.com.miwok1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    //the list view contain the words
    ListView list;
    //the media player
    MediaPlayer audio;
    //get the playing icon container
    ImageView playing_icon;

    /*create an onCompletion object , rather than create object on ech click*/
    private MediaPlayer.OnCompletionListener mOnCompletionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        displayNumbers();

        //get the lsit
        list = (ListView) findViewById(R.id.list);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                //release the current audio , because we sill play another
                releaseMediaPlayer();
                Word word = (Word)list.getItemAtPosition(position);
                //load the audio
                audio=MediaPlayer.create(getApplicationContext(),word.getAudioResourceId());
                //playing the audio
                audio.start();

                audio.setOnCompletionListener(mOnCompletionListener);
            }
        });

        //add up back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void displayNumbers (){
        ArrayList<Word> word = new ArrayList<>();
        //create a word object
        word.add(new Word("one","lutti" , R.drawable.number_one , R.raw.number_one));
        word.add(new Word("two","otiiko", R.drawable.number_two, R.raw.number_two));
        word.add(new Word("three","tolookosu", R.drawable.number_three, R.raw.number_three));
        word.add(new Word("four","oyyisa", R.drawable.number_four, R.raw.number_four));
        word.add(new Word("five","massokka", R.drawable.number_five, R.raw.number_five));
        word.add(new Word("six","temmokka", R.drawable.number_six, R.raw.number_six));
        word.add(new Word("seven","kenekaku", R.drawable.number_seven, R.raw.number_seven));
        word.add(new Word("eight","kawinta", R.drawable.number_eight, R.raw.number_eight));
        word.add(new Word("nine","wo'e", R.drawable.number_nine, R.raw.number_nine));
        word.add(new Word("ten","na’aacha", R.drawable.number_ten, R.raw.number_ten));

        //create an adapter
        WordAdapter itemsAdapter = new WordAdapter(this,word, R.color.category_numbers);
        //get the list view
        ListView listView = (ListView) findViewById(R.id.list);
        //connect the listView to the adapter
        listView.setAdapter(itemsAdapter);
    }

    /**
     * get a specific view (list item)
     * @param pos
     * @param listView
     * @return
     */
    public View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (audio != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            audio.release();

            Toast.makeText(NumbersActivity.this, "released", Toast.LENGTH_SHORT).show();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            audio = null;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        //release resources on stop
        releaseMediaPlayer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //release resources on destroy
        releaseMediaPlayer();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
