package uk.ac.qub.eeecs.game.cardDemo;

import android.graphics.Color;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;

import java.io.PushbackInputStream;
import java.util.List;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.MenuScreen;

/**
 * Created by LYBKingMao on 2018/1/31.
 */

public class MainMenu extends GameScreen {
    private GameObject MainMenuBackground;
    private PushButton Start;
    private PushButton Settings;
    private PushButton Back;
    public MainMenu(Game game) {
        super("MainMenu", game);
        AssetStore assetManager = mGame.getAssetManager();
        assetManager.loadAndAddBitmap("CardStart","img/CardStart.png");
        assetManager.loadAndAddBitmap("Settings","img/CardSettings.png");
        assetManager.loadAndAddBitmap("MainMenuBackground","img/ThreeCountry.png");
        assetManager.loadAndAddBitmap("Back","img/CardBack.png");
        assetManager.loadAndAddMusic("MenuBackgroundMusic","music/Menu_bgm.mp3");
        this.getGame().getAssetManager().getMusic("MenuBackgroundMusic").setLopping(true);
        this.getGame().getAssetManager().getMusic("MenuBackgroundMusic").play();
        int spacingX = game.getScreenWidth() / 3;
        int spacingY = game.getScreenHeight() / 10;
        MainMenuBackground=new GameObject(game.getScreenWidth()/2.0f,
                game.getScreenHeight()/2.0f,game.getScreenWidth(),game.getScreenHeight(), getGame()
                .getAssetManager().getBitmap("MainMenuBackground"), this);
        Start=new PushButton(
                spacingX*0.5f,spacingY*2.5f,spacingX,spacingY,"CardStart",this);
        Settings=new PushButton(
                spacingX*0.5f,spacingY*4.0f,spacingX,spacingY,"Settings",this);
        Back=new PushButton(
                spacingX*0.5f,spacingY*5.5f,spacingX,spacingY,"Back",this);
    }
    @Override
    public void update(ElapsedTime elapsedTime) {
        // Process any touch events occurring since the last update

        Input input = mGame.getInput();
        MainMenuBackground.update(elapsedTime);
        Start.update(elapsedTime);
        Settings.update(elapsedTime);
        Back.update(elapsedTime);

        List<TouchEvent> touchEvents = input.getTouchEvents();
        if (touchEvents.size() > 0) {

            // Just check the first touch event that occurred in the frame.
            // It means pressing the screen with several fingers may not
            // trigger a 'button', but, hey, it's an exceedingly basic menu.
            TouchEvent touchEvent = touchEvents.get(0);

            // Update each button and transition if needed

            if (Start.isPushTriggered())
                changeToScreen(new CardDemoScreen(mGame));
            else if(Back.isPushTriggered())
                changeToScreen((new MenuScreen(mGame)));
        }else if(Settings.isPushTriggered())
            changeToScreen((new CardOptionsScreen(mGame)));
    }
    private void changeToScreen(GameScreen screen) {
        mGame.getScreenManager().removeScreen(this.getName());
        mGame.getScreenManager().addScreen(screen);
        this.getGame().getAssetManager().getMusic("MenuBackgroundMusic").stop();
    }

    /**
     * Draw the card demo screen
     *
     * @param elapsedTime Elapsed time information
     * @param graphics2D  Graphics instance
     */
    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        graphics2D.clear(Color.WHITE);
        MainMenuBackground.draw(elapsedTime, graphics2D);
        Start.draw(elapsedTime, graphics2D);
        Settings.draw(elapsedTime,graphics2D);
        Back.draw(elapsedTime,graphics2D);
    }
}


