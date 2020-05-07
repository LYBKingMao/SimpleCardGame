package uk.ac.qub.eeecs.game.cardDemo;


import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;
import uk.ac.qub.eeecs.gage.Game;
import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.graphics.IGraphics2D;
import uk.ac.qub.eeecs.gage.ui.PushButton;
import uk.ac.qub.eeecs.gage.ui.ToggleButton;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

/**
 * Created by LYBKingMao on 2017/11/26.
 */

public class CardOptionsScreen extends GameScreen {
    private int volume_level=100;
    //private int volume_push_times=1;
    private int volume_push_times=0;
    private GameObject volume_0;
    private GameObject volume_50;
    private GameObject volume_100;
    private GameObject SettingBackGround;
    private PushButton back;
    private GameObject silence;
    private ToggleButton silence_control;
    private PushButton volume_decrease;
    private PushButton volume_increase;

    public CardOptionsScreen(Game game) {
        super("CardOptionsScreen", game);
        AssetStore assetManager = mGame.getAssetManager();

        assetManager.loadAndAddBitmap("start", "img/start.png");
        assetManager.loadAndAddBitmap("back","img/back.png");
        assetManager.loadAndAddBitmap("silence","img/silence.png");
        assetManager.loadAndAddBitmap("have_volume","img/have_volume.png");
        assetManager.loadAndAddBitmap("no_volume","img/no_volume.png");
        assetManager.loadAndAddBitmap("volume_0","img/volume_0.png");
        assetManager.loadAndAddBitmap("volume_50","img/volume_50.png");
        assetManager.loadAndAddBitmap("volume_100","img/volume_100.png");
        assetManager.loadAndAddBitmap("volume_increase","img/volume_decrease.png");
        assetManager.loadAndAddBitmap("volume_decrease","img/volume_increase.png");
        assetManager.loadAndAddBitmap("SettingBackGround","img/SettingsBackground.png");
        assetManager.loadAndAddMusic("buttonsound","music/button_sound.mp3");
        // Define the spacing that will be used to position the buttons
        int spacingX = game.getScreenWidth() / 10;
        int spacingY = game.getScreenHeight() / 6;
        // Create the space background
        SettingBackGround=new GameObject(game.getScreenWidth()/2.0f,
                game.getScreenHeight()/2.0f,game.getScreenWidth(),game.getScreenHeight(), getGame()
                .getAssetManager().getBitmap("SettingBackGround"), this);
        back=new PushButton(
                spacingX*0.5f,spacingY*0.5f,spacingX,spacingY,"back",this);
        silence=new GameObject(
                spacingX*3.5f,spacingY*3.0f,spacingX*2.0f,spacingY,mGame.getAssetManager().getBitmap("silence"),this);
        silence_control=new ToggleButton(spacingX*6.5f,spacingY*3.0f,spacingX,spacingY,"have_volume","no_volume",this);;
        volume_decrease=new PushButton(
                spacingX*6.5f,spacingY*2.0f,spacingX,spacingY,"volume_decrease",this);
        volume_increase=new PushButton(
                spacingX*3.5f,spacingY*2.0f,spacingX,spacingY,"volume_increase",this);
        volume_0=new GameObject(spacingX*5.0f,spacingY*2.0f,spacingX*2.0f,spacingY/2.0f,mGame.getAssetManager().getBitmap("volume_0"),this);
        volume_50=new GameObject(spacingX*5.0f,spacingY*2.0f,spacingX*2.0f,spacingY/2.0f,mGame.getAssetManager().getBitmap("volume_50"),this);
        volume_100=new GameObject(spacingX*5.0f,spacingY*2.0f,spacingX*2.0f,spacingY/2.0f,mGame.getAssetManager().getBitmap("volume_100"),this);
    }


    @Override
    public void update(ElapsedTime elapsedTime) {
        SettingBackGround.update(elapsedTime);
        back.update(elapsedTime);
        volume_decrease.update(elapsedTime);
        silence.update(elapsedTime);
        silence_control.update(elapsedTime);
        volume_increase.update(elapsedTime);
        volume_decrease.update(elapsedTime);

        if (back.isPushTriggered()) {
            changeToScreen(new MainMenu(mGame));
        } else if (silence_control.isToggledOn()) {
            this.getGame().getAssetManager().getMusic("buttonsound").play();
            this.getGame().getAssetManager().getMusic("buttonsound").setVolume(0);
        } else if (volume_decrease.isPushTriggered()) {
            this.getGame().getAssetManager().getMusic("buttonsound").play();
            this.getGame().getAssetManager().getMusic("buttonsound").setVolume(volume_level=volume_level-50);
            volume_push_times++;
            while(volume_push_times<0){
                volume_push_times=0;
            }
        } else if (volume_increase.isPushTriggered()) {
            this.getGame().getAssetManager().getMusic("buttonsound").play();
            this.getGame().getAssetManager().getMusic("buttonsound").setVolume(volume_level=volume_level+50);
            volume_push_times--;
            while(volume_push_times>2){
                volume_push_times=2;
            }
        }switch (volume_push_times){
            case 0:volume_0.update(elapsedTime);break;
            case 1:volume_50.update(elapsedTime);break;
            case 2:volume_100.update(elapsedTime);break;
        }
    }
    private void changeToScreen(GameScreen screen) {
        mGame.getScreenManager().removeScreen(this.getName());
        mGame.getScreenManager().addScreen(screen);
    }
@Override
    public void draw(ElapsedTime elapsedTime, IGraphics2D graphics2D) {
    graphics2D.clear(Color.WHITE);
    SettingBackGround.draw(elapsedTime,graphics2D);
    switch (volume_push_times){
        case 0:volume_0.draw(elapsedTime,graphics2D);break;
        case 1:volume_50.draw(elapsedTime,graphics2D);break;
        case 2:volume_100.draw(elapsedTime,graphics2D);break;
    }
    back.draw(elapsedTime,graphics2D);
    silence.draw(elapsedTime,graphics2D);
    silence_control.draw(elapsedTime,graphics2D);
    volume_decrease.draw(elapsedTime,graphics2D);
    volume_increase.draw(elapsedTime, graphics2D);
    }
}