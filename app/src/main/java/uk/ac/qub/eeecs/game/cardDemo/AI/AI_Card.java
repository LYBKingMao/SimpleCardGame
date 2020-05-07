package uk.ac.qub.eeecs.game.cardDemo.AI;

import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.Sprite;
import java.util.Random;

/**
 * Created by LYBKingMao on 2018/2/11.
 */

public class AI_Card extends Sprite {
    private static Random random = new Random();
    public AI_Card(float startX, float startY,GameScreen gameScreen) {
        super(startX, startY,200.0f,200.0f, null, gameScreen);

        mBitmap = gameScreen.getGame().getAssetManager().getBitmap("bg_card");

        angularVelocity = random.nextFloat() * 240.0f - 20.0f;
    }
}
