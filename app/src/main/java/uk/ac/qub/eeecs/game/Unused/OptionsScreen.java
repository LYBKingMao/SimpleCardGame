package uk.ac.qub.eeecs.game.Unused;


import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import java.util.List;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.w3c.dom.Text;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.MainActivity;
import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.CanvasGraphics2D;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.MenuScreen;
/**
 * Created by LYBKingMao on 2017/11/13.
 */

/**
 * Starter class for Card game stories in the 2nd sprint
 *
 * @version 1.0
 */
public class OptionsScreen extends GameScreen {
    // /////////////////////////////////////////////////////////////////////////
    // Properties
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Width and height of the level
     */
    private final float LEVEL_WIDTH = 1000.0f;
    private final float LEVEL_HEIGHT = 1000.0f;
    /**
     * Define the background star scape
     */
    private GameObject mOptionsBackground;

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    private PushButton back;
    private PushButton change;
    /**
     * Create the Card game screen
     *
     * @param game Game to which this screen belongs
     */
    public OptionsScreen(Game game) {
        super("OptionsScreen", game);

        // Load in the assets used by the steering demo
        AssetStore assetManager = mGame.getAssetManager();

        assetManager.loadAndAddBitmap("OptionsBackground", "img/background.png");
        assetManager.loadAndAddBitmap("Back","img/LeftArrow.png");
        assetManager.loadAndAddBitmap("change","img/change.png");
        // Define the spacing that will be used to position the buttons
        int spacingX = game.getScreenWidth() / 10;
        int spacingY = game.getScreenHeight() / 6;
        // Create the space background
        mOptionsBackground= new GameObject(LEVEL_WIDTH / 2.0f,
                LEVEL_HEIGHT / 2.0f, LEVEL_WIDTH, LEVEL_HEIGHT, getGame()
                .getAssetManager().getBitmap("OptionsBackground"), this);
        back=new PushButton(
                spacingX*0.5f,spacingY*5.5f,spacingX,spacingY,"Back",this);
        change=new PushButton(
                spacingX*1.5f,spacingY*5.5f,spacingX,spacingY,"change",this);
    }

    // /////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Update the card demo screen
     *
     * @param elapsedTime Elapsed time information
     */
    @Override
    public void update(ElapsedTime elapsedTime) {
        // Process any touch events occurring since the last update
        Input input = mGame.getInput();

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {

            // Just check the first touch event that occurred in the frame.
            // It means pressing the screen with several fingers may not
            // trigger a 'button', but, hey, it's an exceedingly basic menu.
            TouchEvent touchEvent = touchEvents.get(0);

            // Update each button and transition if needed

            back.update(elapsedTime);
            change.update(elapsedTime);

            if (back.isPushTriggered())
                changeToScreen(new MenuScreen(mGame));
        }
    }
    private void changeToScreen(GameScreen screen) {
        mGame.getScreenManager().removeScreen(this.getName());
        mGame.getScreenManager().addScreen(screen);
    }

    /**
     * Draw the card demo screen
     *
     * @param elapsedTime Elapsed time information
     * @param graphics2D  Graphics instance
     */
    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        graphics2D.clear(Color.rgb(100,100,150));
        Paint mPaint=new Paint();
        mPaint.setColorFilter(new LightingColorFilter(Color.rgb(100,200,150),0));
        mOptionsBackground.draw(elapsedTime, graphics2D);
        back.draw(elapsedTime, graphics2D, null, null);
        change.draw(elapsedTime,graphics2D,null,null);
    }
}
