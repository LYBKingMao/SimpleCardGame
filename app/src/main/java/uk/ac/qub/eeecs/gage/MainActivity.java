package uk.ac.qub.eeecs.gage;

import uk.ac.qub.eeecs.game.DemoGame;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Main game activity
 *
 * @version 1.0
 */
public class MainActivity extends Activity {
private SeekBar volumeSeekBar;
private AudioManager audioManager;
    /**
     * Game fragment instance
     */
    private Game mGame;

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Setup the window as suitable for a game, namely: full screen
        // with no title and a request to keep the screen on. The changes
        // are made before any content is inflated.

        Window window = getWindow();
        window.requestFeature(Window.FEATURE_NO_TITLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // Set the content view to use a simple frame layout
        setContentView(R.layout.activity_fragment);
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        initControls();



        // Add in the main game fragment
        FragmentManager fm = getFragmentManager();
        mGame = (Game) fm.findFragmentById(R.id.activity_fragment_id);

        if (mGame == null) {
            mGame = new DemoGame();

            fm.beginTransaction().add(R.id.activity_fragment_id, mGame)
                    .commit();
        }
    }
    private void initControls(){
        try{
            volumeSeekBar=(SeekBar)findViewById(R.id.seekBar);
            audioManager=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
            volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }catch (Exception e){

        }
    }

    /*
     * (non-Javadoc)
     * @see android.app.Activity#onBackPressed()
     */
    @Override
    public void onBackPressed() {
        // If the fragment does not consume the back event then
        // trigger the default behaviour
        if (!mGame.onBackPressed())
            super.onBackPressed();
    }
}