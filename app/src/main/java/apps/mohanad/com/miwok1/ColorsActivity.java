package apps.mohanad.com.miwok1;/**
 * display the colors in english and in miwok
 */

import android.media.AudioManager;
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


public class ColorsActivity extends AppCompatActivity {

    //the list view contain the words
    private ListView list;
    //the media player
    private MediaPlayer mMediaPlayer;
    //get the playing icon container
    private ImageView playing_icon;

    /*the list containing the words*/
    private ArrayList<Word> wordList;

    /*get the system service mMediaPlayer to control the device mMediaPlayer */
    AudioManager mMediaPlayerManager;

    /*create an onCompletion object , rather than create object on ech click*/
    private MediaPlayer.OnCompletionListener mOnCompletionListener= new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    /*the OnAudioFocusChangeListener object to control the mMediaPlayer focus*/
    private AudioManager.OnAudioFocusChangeListener mMediaPlayerFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        if (mMediaPlayer!=null){
                            mMediaPlayer.pause();
                            mMediaPlayer.seekTo(0);
                        }
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                       releaseMediaPlayer();
                       mMediaPlayerManager.abandonAudioFocus(mMediaPlayerFocusChangeListener);
                    } else if (focusChange ==
                            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Lower the volume, because something else is also
                        // playing mMediaPlayer over you.s
                        // i.e. for notifications or navigation directions
                        // Depending on your mMediaPlayer playback, you may prefer to
                        // pause playback here instead. You do you.
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        mMediaPlayer.start();
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        displayColors();

        //get tha audio manager
        mMediaPlayerManager =(AudioManager)getSystemService(AUDIO_SERVICE);
        //get the lsit
        list = (ListView) findViewById(R.id.list);
        list.setClickable(true);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //release the current mMediaPlayer , because we sill play another
                releaseMediaPlayer();
                Word word = wordList.get(position);
                //load the mMediaPlayer
                mMediaPlayer=MediaPlayer.create(getApplicationContext(),word.getAudioResourceId());
                //playing the mMediaPlayer
                mMediaPlayer.start();

                //async call back
                //creating and calling object , scve resources rather than create object on ech
                //click
                mMediaPlayer.setOnCompletionListener(mOnCompletionListener);
            }
        });

        //Add Up back Action button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*request mMediaPlayer focus to the app*/
        int result =mMediaPlayerManager.requestAudioFocus(mMediaPlayerFocusChangeListener ,
                //use the music stream
                AudioManager.STREAM_MUSIC ,
                //gain tha mMediaPlayer focus for short time
                AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE);
    }

    /**
     * add the colors to listView
     */
    private void displayColors (){
        wordList = new ArrayList<>();
        wordList.add(new Word("red"," weṭeṭṭi" , R.drawable.color_red , R.raw.color_red));
        wordList.add(new Word("green","chokokki", R.drawable.color_green, R.raw.color_green));
        wordList.add(new Word("brown","ṭakaakki", R.drawable.color_brown, R.raw.color_brown));
        wordList.add(new Word("gray","ṭopoppi", R.drawable.color_gray, R.raw.color_gray));
        wordList.add(new Word("black","kululli", R.drawable.color_black, R.raw.color_black));
        wordList.add(new Word("white","kelelli", R.drawable.color_white, R.raw.color_white));
        wordList.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        wordList.add(new Word("mustard yellow","chiwiiṭә", R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter itemsAdapter = new WordAdapter(this,wordList , R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(itemsAdapter);
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();

            Toast.makeText(ColorsActivity.this, "released", Toast.LENGTH_SHORT).show();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an mMediaPlayer file at the moment.
            mMediaPlayer = null;
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        //release resources on stop
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