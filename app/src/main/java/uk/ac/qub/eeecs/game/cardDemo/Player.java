package uk.ac.qub.eeecs.game.cardDemo;

import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;

/**
 * Created by LYBKingMao on 2017/12/5.
 */

public class Player extends GameObject {
    public Player(float x, float y, float width, float height, GameScreen gameScreen) {
        super(100.0f, 100.0f, 100.0f, 100.0f,null, gameScreen);
        mBitmap = gameScreen.getGame().getAssetManager()
                .getBitmap("player1.png");
        mBitmap=gameScreen.getGame().getAssetManager().getBitmap("player2.png");
        mBitmap=gameScreen.getGame().getAssetManager().getBitmap("player3.png");
    }
}
