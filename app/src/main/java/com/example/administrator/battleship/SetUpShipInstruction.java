package com.example.administrator.battleship;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

/**
 * @author Nathan Camacho
 * @author Hashim AlJawad
 * @author Kelson Sipe
 *
 * @version  12/12/2015
 *
 * Description of SetUpShipInstruction: This class is used for displaying a pop up window in the SetUpShips activity that illustrates how
 * to move the ships on the screen.
 *
 */
public class SetUpShipInstruction extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_set_up_ship_instruction);


        DisplayMetrics displayMessage = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMessage);

        int width = displayMessage.widthPixels;//width of the window
        int height = displayMessage.heightPixels;//height of the window

        getWindow().setLayout((int)(width*.6),(int)(height*.6));//sets up the pop up window
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_up_ship_instruction, menu);
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
