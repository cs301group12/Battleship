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
 * Created by camachon18 on 12/12/2015.
 */
public class GameOverPopup extends Activity {
    RelativeLayout layout;
    Drawable victoryScreen;
    Drawable defeatScreen;
    Intent intent;
    String winner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over_popup_screen);

        layout = (RelativeLayout) findViewById(R.id.mainLayout);
        victoryScreen = getResources().getDrawable(R.drawable.gameover_victory);
        defeatScreen = getResources().getDrawable(R.drawable.gameover_screen);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .8));

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
