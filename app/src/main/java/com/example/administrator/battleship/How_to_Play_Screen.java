package com.example.administrator.battleship;

import android.media.AudioManager;
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
 * @version  12/12/2015
 *
 * Description of How_to_Play_Screen class:
 * Activity that displays the rules of Battleship.
 *
 */
public class How_to_Play_Screen extends ActionBarActivity {

    private SoundPool buttonSound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
    private int pickupId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to__play__screen);

        this.pickupId = buttonSound.load(this, R.raw.button_sound_effect, 1);
        htpToMainMenu();
    }

    //method to go back to the main menu or back to playing battleship
    private void htpToMainMenu(){
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSound.play(pickupId, 1, 1, 1, 0, 1.0f);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_how_to__play__screen, menu);
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
