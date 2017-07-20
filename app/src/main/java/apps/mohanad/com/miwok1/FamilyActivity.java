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

public class FamilyActivity extends AppCompatActivity {

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
        //display family on the list
        displayNumbers();

        //get the lsit
        list = (ListView) findViewById(R.id.list);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //release the current audio , because we sill play another
                releaseMediaPlayer();
                Word word = (Word)list.getItemAtPosition(position);
                //load the audio
                audio=MediaPlayer.create(getApplicationContext(),word.getAudioResourceId());
                //playing the audio
                audio.start();

                //async call back
                audio.setOnCompletionListener(mOnCompletionListener);
            }
        });


    }


    private void displayNumbers() {
        ArrayList<Word> word = new ArrayList<>();

        word.add(new Word("father", "әpә", R.drawable.family_father , R.raw.family_father));
        word.add(new Word("mother", "әṭa", R.drawable.family_mother, R.raw.family_mother));
        word.add(new Word("son", "angsi", R.drawable.family_son, R.raw.family_son));
        word.add(new Word("daughter", "tune", R.drawable.family_daughter, R.raw.family_daughter));
        word.add(new Word("older brother", "taachi", R.drawable.family_older_brother, R.raw.family_older_brother));
        word.add(new Word("younger brother", "chalitti", R.drawable.family_younger_brother, R.raw.family_younger_brother));
        word.add(new Word("older sister", " teṭe", R.drawable.family_older_sister, R.raw.family_older_sister));
        word.add(new Word("younger sister", " kolliti", R.drawable.family_younger_sister, R.raw.family_younger_sister));
        word.add(new Word("grandmother", " ama", R.drawable.family_grandmother, R.raw.family_grandmother));
        word.add(new Word("grandfather", " paapa", R.drawable.family_grandfather, R.raw.family_grandfather));
        WordAdapter itemsAdapter = new WordAdapter(this, word, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }

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

            Toast.makeText(FamilyActivity.this, "released", Toast.LENGTH_SHORT).show();
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
