package com.example.administrator.battleship;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/12/2015
 *
 * Description of GameOverPopup
 * GUI for popup screen when game is over
 *
 */
public class GameOverPopup extends Activity {
    RelativeLayout layout;//main layout
    Drawable victoryScreen;//backgrounds for popup screens
    Drawable defeatScreen;
    Intent intent;//used to receive data about who won game
    String winner;//who won
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_popup_screen);

        layout = (RelativeLayout) findViewById(R.id.mainLayout);
        victoryScreen = getResources().getDrawable(R.drawable.gameover_victory);
        defeatScreen = getResources().getDrawable(R.drawable.gameover_screen);

        //Adjust size of popup screen
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .6), (int) (height * .6));

        //get who won game
        intent = getIntent();
        winner = (String) intent.getStringExtra("Winner");
        if (winner.equals("Human")){
            layout.setBackground(victoryScreen);
        }
        else {
            layout.setBackground(defeatScreen);
        }


    }
}
