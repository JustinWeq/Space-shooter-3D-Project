package jeremyred.spaceshooter3dproject.Activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import jeremyred.spaceshooter3dproject.Data.Level;
import jeremyred.spaceshooter3dproject.MainMenuActivity;
import jeremyred.spaceshooter3dproject.Managers.DataManager;
import jeremyred.spaceshooter3dproject.Managers.GameManager;
import jeremyred.spaceshooter3dproject.R;

/**
 * A level finish activity that displays a message to the user and allows the user to return to the main menu or
 * to continue to the next one if one exists(if one does not exist it returns back to the main menu by default)
 * @author jeremy red
 * @version 3/1/2016
 */
public class LevelFinishActivity extends Activity{

    /**
     *
     */
    public static final String GAME_OVER = "jeremyred.spaceshooter3dproject.Activitys.GAME_OVER";

    /**
     * a boolean indicating there is a next level
     */
    private boolean m_hasNextLevel;

    /**
     * a boolean indicating whether the game ended as a result of game over
     */
    private boolean m_gameOver;

    /**
     * called upon activity creation
     * @param savedInstanceState the saved instance state
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_finish_layout);

        //set the current level to be the next level if next level exists

        //update the text based on passed in parameters
        if((m_gameOver =getIntent().getBooleanExtra(GAME_OVER,false)))
        {
            TextView lbl = (TextView)findViewById(R.id.lblFinish);
            lbl.setText("Game over");
            Button btn = (Button)findViewById(R.id.btnContinue);
            btn.setText("Retry");
        }

    }

    /**
     * called upon the return button being clicked
     * @param view the button view
     */
    public void onClickReturn(View view)
    {
        startActivity(new Intent(this,MainMenuActivity.class));
    }

    /**
     * called upon the continue button being clicked
     * @param view the button view
     */
    public void onClickContinue(View view) {

        if(!m_gameOver) {
            Level level;
            if((level = DataManager.getDataManager().getNextLevel()) != null)
            Level.CurrentLevel = level ;

        }
        Intent intent = new Intent(this, GameActivity.class);

        startActivity(intent);
        finish();
    }
}
