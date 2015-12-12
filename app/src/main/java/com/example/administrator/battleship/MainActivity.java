package com.example.administrator.battleship;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/1/2015
 *
 * Description of MainActivity class:
 * The activity that first pops up when user launches app. Allows user to decide whether they want to play the game
 * or learn the rules.
 *
 */
public class MainActivity extends ActionBarActivity {

    //private SoundPool music = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    private MediaPlayer backgroundMusic5;
    private Button unmute;
    private Button mute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        backgroundMusic5 = new MediaPlayer().create(this, R.raw.background5);
        goToPlay();
        goToHowToPlay();
        playBackgroundMusic();

        unmute = (Button) findViewById(R.id.unmuteButton);
        mute = (Button) findViewById(R.id.mutebutton);
    }

    public void playBackgroundMusic(){
        backgroundMusic5.start();
        backgroundMusic5.setLooping(true);
    }

    //Method to go to set up the ships
    private void goToPlay(){
        Button playNow = (Button) findViewById(R.id.playNowButton);
        playNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundMusic5.stop();
                startActivity(new Intent(MainActivity.this, SetUpShips.class));
            }
        });
    }

    //method to go to see the rules of battleship
    private void goToHowToPlay(){
        Button howToPlay = (Button) findViewById(R.id.howToPlayButton);
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundMusic5.stop();
                startActivity(new Intent(MainActivity.this, How_to_Play_Screen.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
