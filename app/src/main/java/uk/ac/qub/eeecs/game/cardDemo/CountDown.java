package uk.ac.qub.eeecs.game.cardDemo;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.R;
import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.ui.Button;
import uk.ac.qub.eeecs.gage.ui.PushButton;

/**
 * Created by LYBKingMao on 2018/1/22.
 */

public class CountDown extends Activity {
    private PushButton count;
    private CountDownTimer timer;
    protected void onCreate(Bundle savedInsttanceState){
        super.onCreate(savedInsttanceState);
        setContentView(R.layout.activity_fragment);
    }
    public void clickButton(View view) {
        timer = new CountDownTimer(30000, 10) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {

            }
        };
        timer.start();
    }
    protected void onDestroy(){
        super.onDestroy();
        if(timer!=null){
            timer.cancel();
    }
    }
}
