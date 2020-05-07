package uk.ac.qub.eeecs.game.cardDemo;
import android.graphics.Color;
import android.graphics.Rect;

import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.engine.input.TouchEvent;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.util.BoundingBox;
import uk.ac.qub.eeecs.gage.util.Vector2;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.game.cardDemo.AI.AI_Card;

/**
 * Starter class for Card game stories in the 2nd sprint.
 *
 * @version 1.0
 */
public class CardDemoScreen extends GameScreen {
    Random random=new Random();
    int rnum=random.nextInt(5)+1;
    private GameObject CardBackGround;
    private GameObject Bcard1,Bcard2,Bcard3;
    private GameObject Fcard1,Fcard2,Fcard3;
    private GameObject Player1,Player2,Player3,Player4,Player5;
    private GameObject bg_card;
    private GameObject Hcard;
    private Card mcard;
    private AI_Card ai_card;
    //private PushButton TestWinScreen;
   // private PushButton TestLoseScreen;
    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////
    /**
     * Create the Card game screen
     *
     * @param game Game to which this screen belongs
     */
    public CardDemoScreen(Game game) {
        super("CardScreen", game);
        AssetStore assetManager = mGame.getAssetManager();
        assetManager.loadAndAddBitmap("CardBackGround","img/CardBackGround.png");
        assetManager.loadAndAddBitmap("Bcard1", "img/Bcard1.png");
        assetManager.loadAndAddBitmap("Bcard2", "img/Bcard2.png");
        assetManager.loadAndAddBitmap("Bcard3", "img/Bcard3.png");
        assetManager.loadAndAddBitmap("Fcard1", "img/Fcard1.png");
        assetManager.loadAndAddBitmap("Fcard2", "img/Fcard2.png");
        assetManager.loadAndAddBitmap("Fcard3", "img/Fcard3.png");
        assetManager.loadAndAddBitmap("Hcard", "img/Hcard.png");
        assetManager.loadAndAddBitmap("Player1","img/player1.png");
        assetManager.loadAndAddBitmap("Player2","img/player2.png");
        assetManager.loadAndAddBitmap("Player3","img/player3.png");
        assetManager.loadAndAddBitmap("Player4","img/player4.png");
        assetManager.loadAndAddBitmap("Player5","img/player5.png");
        assetManager.loadAndAddBitmap("TestWinScreen","img/win_button.png");
        assetManager.loadAndAddBitmap("TestLoseScreen","img/lose_button.png");
        assetManager.loadAndAddBitmap("bg_card","img/bg_card.png");
        int spacingX = game.getScreenWidth() / 10;
        int spacingY = game.getScreenHeight() / 6;
        CardBackGround=new GameObject(game.getScreenWidth()/2.0f,
                game.getScreenHeight()/2.0f,game.getScreenWidth(),game.getScreenHeight(), getGame()
                .getAssetManager().getBitmap("CardBackGround"), this);
        mcard=new Card(spacingX*2.0f,spacingY*0.5f,spacingX,spacingY,getGame().getAssetManager().getBitmap("Fcard3"),this);
        ai_card=new AI_Card(spacingX*6.0f,spacingY*0.5f,this);
        bg_card= new GameObject(spacingX*0.5f,
                spacingY*3.0f, spacingX,spacingY, getGame()
                .getAssetManager().getBitmap("bg_card"), this);
        Hcard= new GameObject(spacingX*1.5f,
                spacingY*4.5f, spacingX,spacingY, getGame()
                .getAssetManager().getBitmap("Hcard"), this);
        Bcard1= new GameObject(spacingX*3.0f,
                spacingY*5.5f, spacingX,spacingY, getGame()
                .getAssetManager().getBitmap("Bcard1"), this);
        Bcard2= new GameObject(spacingX*4.0f,
                spacingY*5.5f, spacingX,spacingY, getGame()
                .getAssetManager().getBitmap("Bcard2"), this);
        Bcard3= new GameObject(spacingX*5.0f,
                spacingY*5.5f, spacingX,spacingY, getGame()
                .getAssetManager().getBitmap("Bcard3"), this);
        Fcard1= new GameObject(spacingX*6.0f,
                spacingY*5.5f, spacingX,spacingY, getGame()
                .getAssetManager().getBitmap("Fcard1"), this);
        Fcard2= new GameObject(spacingX*7.0f,
                spacingY*5.5f, spacingX,spacingY, getGame()
                .getAssetManager().getBitmap("Fcard2"), this);
        Fcard3= new GameObject(spacingX*8.0f,
                spacingY*5.5f, spacingX,spacingY, getGame()
                .getAssetManager().getBitmap("Fcard3"), this);
        Player1=new GameObject(spacingX*1.5f,spacingY*5.5f,spacingX,spacingY,getGame().getAssetManager().getBitmap("Player1"),this);
        Player2=new GameObject(spacingX*1.5f,spacingY*5.5f,spacingX,spacingY,getGame().getAssetManager().getBitmap("Player2"),this);
        Player3=new GameObject(spacingX*1.5f,spacingY*5.5f,spacingX,spacingY,getGame().getAssetManager().getBitmap("Player3"),this);
        Player4=new GameObject(spacingX*1.5f,spacingY*5.5f,spacingX,spacingY,getGame().getAssetManager().getBitmap("Player4"),this);
        Player5=new GameObject(spacingX*1.5f,spacingY*5.5f,spacingX,spacingY,getGame().getAssetManager().getBitmap("Player5"),this);
       // TestWinScreen=new PushButton(
                //spacingX*9.5f,spacingY*0.5f,spacingX,spacingY,"TestWinScreen",this);
        //TestLoseScreen=new PushButton(
               // spacingX*0.5f,spacingY*0.5f,spacingX,spacingY,"TestLoseScreen",this);
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
        mcard.update(elapsedTime);
        //ensure test card will not get out of screen
            BoundingBox playerBound = mcard.getBound();
            if (playerBound.getLeft() < 0)
                mcard.position.x -= playerBound.getLeft();
            else if (playerBound.getRight() > mGame.getScreenWidth())
                mcard.position.x -= (playerBound.getRight() - mGame.getScreenWidth());

            if (playerBound.getBottom() < 0)
                mcard.position.y -= playerBound.getBottom();
            else if (playerBound.getTop() > mGame.getScreenHeight())
                mcard.position.y -= (playerBound.getTop() - mGame.getScreenHeight());
            // Just check the first touch event that occurred in the frame.
            // It means pressing the screen with several fingers may not
            // trigger a 'button', but, hey, it's an exceedingly basic menu.
            CardBackGround.update(elapsedTime);
            ai_card.update(elapsedTime);
            bg_card.update(elapsedTime);
            Hcard.update(elapsedTime);
            Bcard1.update(elapsedTime);
            Bcard2.update(elapsedTime);
            Bcard3.update(elapsedTime);
            Fcard1.update(elapsedTime);
            Fcard2.update(elapsedTime);
            Fcard3.update(elapsedTime);
        switch (rnum) {
            case 1:
                Player1.update(elapsedTime);
                break;
            case 2:
                Player2.update(elapsedTime);
                break;
            case 3:
                Player3.update(elapsedTime);
                break;
            case 4:
                Player4.update(elapsedTime);
                break;
            case 5:
                Player5.update(elapsedTime);
                break;
        }
       // TestWinScreen.update(elapsedTime);
       // TestLoseScreen.update(elapsedTime);
           // if (TestWinScreen.isPushTriggered())
               // changeToScreen(new CardWinScreen(mGame));
           // else if(TestLoseScreen.isPushTriggered())
                //changeToScreen(new CardLoseScreen(mGame));
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
        graphics2D.clear(Color.WHITE);
        CardBackGround.draw(elapsedTime,graphics2D);
        bg_card.draw(elapsedTime,graphics2D);
        ai_card.draw(elapsedTime,graphics2D);
        Hcard.draw(elapsedTime,graphics2D);
        Bcard1.draw(elapsedTime,graphics2D);
        Bcard2.draw(elapsedTime,graphics2D);
        Bcard3.draw(elapsedTime,graphics2D);
        Fcard1.draw(elapsedTime,graphics2D);
        Fcard2.draw(elapsedTime,graphics2D);
        Fcard3.draw(elapsedTime,graphics2D);
        mcard.draw(elapsedTime,graphics2D);
        switch (rnum){
            case 1:Player1.draw(elapsedTime,graphics2D);break;
            case 2:Player2.draw(elapsedTime,graphics2D);break;
            case 3:Player3.draw(elapsedTime,graphics2D);break;
            case 4:Player4.draw(elapsedTime,graphics2D);break;
            case 5:Player5.draw(elapsedTime,graphics2D);break;
        }
       // TestWinScreen.draw(elapsedTime,graphics2D);
       // TestLoseScreen.draw(elapsedTime,graphics2D);
    }
}