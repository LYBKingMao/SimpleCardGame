package uk.ac.qub.eeecs.game.Unused;

import android.graphics.Color;

import java.io.PushbackInputStream;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.world.GameScreen;

/**
 * Created by LYBKingMao on 2017/11/27.
 */

public class CardStopScreen extends GameScreen {
    private final float LEVEL_WIDTH = 1000.0f;
    private final float LEVEL_HEIGHT = 1000.0f;
    private PushButton resume;
    private PushButton main_menu;
    private PushButton silence;

    public CardStopScreen(Game game) {
        super("CardStopScreen", game);
        AssetStore assetManager = mGame.getAssetManager();

        assetManager.loadAndAddBitmap("resume", "img/resume_button.png");
        assetManager.loadAndAddBitmap("main_menu","img/menu_button.png");
        assetManager.loadAndAddBitmap("silence","img/backgroundmusic.png");
        // Define the spacing that will be used to position the buttons
        int spacingX = game.getScreenWidth() / 10;
        int spacingY = game.getScreenHeight() / 6;
        // Create the space background
        resume=new PushButton(
                spacingX*5.0f,spacingY*2.0f,spacingX,spacingY,"resume",this);
        main_menu=new PushButton(
                spacingX*5.0f,spacingY*3.0f,spacingX,spacingY,"main_menu",this);
        silence=new PushButton(
                spacingX*5.0f,spacingY*4.0f,spacingX,spacingY,"silence",this);
    }

    @Override
    public void update(ElapsedTime elapsedTime) {
        resume.update(elapsedTime);
        main_menu.update(elapsedTime);
        silence.update(elapsedTime);
    }
    @Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
        graphics2D.clear(Color.WHITE);
        resume.draw(elapsedTime, graphics2D, null, null);
        main_menu.draw(elapsedTime,graphics2D,null,null);
        silence.draw(elapsedTime,graphics2D,null,null);
    }
}
