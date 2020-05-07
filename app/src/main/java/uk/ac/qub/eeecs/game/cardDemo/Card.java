package uk.ac.qub.eeecs.game.cardDemo;


import android.graphics.Bitmap;

import uk.ac.qub.eeecs.gage.ai.SteeringBehaviours;
import uk.ac.qub.eeecs.gage.engine.ElapsedTime;
import uk.ac.qub.eeecs.gage.engine.input.Input;
import uk.ac.qub.eeecs.gage.util.Vector2;
import uk.ac.qub.eeecs.gage.world.GameObject;
import uk.ac.qub.eeecs.gage.world.GameScreen;
import uk.ac.qub.eeecs.gage.world.Sprite;

/**
 * Created by LYBKingMao on 2017/11/17.
 */

public class Card extends Sprite {

    // /////////////////////////////////////////////////////////////////////////
    // Properties
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Centre of the screen (used to determine the offset of touch events)
     */
    private Vector2 destination = new Vector2();

    /**
     * Acceleration vector based on the player's touch input
     */
    private Vector2 playerTouchAcceleration = new Vector2();

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Create a player controlled spaceship
     *
     * @param startX     x location of the player spaceship
     * @param startY     y location of the player spaceship
     * @param gameScreen Gamescreen to which spaceship belongs
     */
    public Card(float startX, float startY, float width, float height, Bitmap bitmap, GameScreen gameScreen) {
        super(startX, startY, width, height, bitmap, gameScreen);

        // Card destination draft
        int spacingX = gameScreen.getGame().getScreenWidth() / 10;
        int spacingY = gameScreen.getGame().getScreenHeight() / 6;
        destination.x=spacingX*5.0f;
        destination.y=spacingY*4.0f;

        // Define the maximum velocities and accelerations of the spaceship
        maxAcceleration = 600.0f;
        maxVelocity = 100.0f;
        maxAngularVelocity = 1440.0f;
        maxAngularAcceleration = 1440.0f;
    }

    // /////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////

    /**
     * Update the player spaceship
     *
     * @param elapsedTime Elapsed time information
     */
    @Override
    public void update(ElapsedTime elapsedTime) {

        // Consider any touch events occurring since the update
        Input input = mGameScreen.getGame().getInput();

        if (input.existsTouch(0)) {
            // Get the primary touch event
            playerTouchAcceleration.x =destination.x;
            playerTouchAcceleration.y =destination.y; // Invert the for y axis

            // Convert into an input acceleration
            acceleration.x = playerTouchAcceleration.x * maxAcceleration;
            acceleration.y = playerTouchAcceleration.y * maxAcceleration;
        }

        // Ensure that the cards points in the direction of movement
        angularAcceleration = SteeringBehaviours.alignWithMovement(this);

        // Dampen the linear and angular acceleration and velocity
        angularAcceleration *= 0.95f;
        angularVelocity *= 0.75f;
        acceleration.multiply(0.75f);
        velocity.multiply(0.95f);

        // Apply the determined accelerations
        super.update(elapsedTime);
    }
}
