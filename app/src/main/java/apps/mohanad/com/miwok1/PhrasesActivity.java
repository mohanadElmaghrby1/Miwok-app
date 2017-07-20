package apps.mohanad.com.miwok1;/**
 * display the phrases on list
 */

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

public class PhrasesActivity extends AppCompatActivity {

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
        displayPhrases();

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

        //add up back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void displayPhrases (){
        ArrayList<Word> word = new ArrayList<>();

        word.add(new Word("Where are you going ?","minto wuksus" , R.raw.phrase_where_are_you_going));
        word.add(new Word("What is your name ?","tinnә oyaase'nә", R.raw.phrase_what_is_your_name));
        word.add(new Word("My name is...","oyaaset...", R.raw.phrase_my_name_is));
        word.add(new Word("How are you feeling?","michәksәs?", R.raw.phrase_how_are_you_feeling));
        word.add(new Word("I’m feeling good.","kuchi achit", R.raw.phrase_im_feeling_good));
        word.add(new Word("Are you coming?","әәnәs'aa?", R.raw.phrase_are_you_coming));
        word.add(new Word("Yes, I’m coming.","hәә’ әәnәm", R.raw.phrase_yes_im_coming));
        word.add(new Word("I’m coming.","әәnәm", R.raw.phrase_im_coming));
        word.add(new Word("Let’s go.","yoowutis", R.raw.phrase_lets_go));
        word.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));

        WordAdapter itemsAdapter = new WordAdapter(this,word, R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
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

            Toast.makeText(PhrasesActivity.this, "released", Toast.LENGTH_SHORT).show();
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
