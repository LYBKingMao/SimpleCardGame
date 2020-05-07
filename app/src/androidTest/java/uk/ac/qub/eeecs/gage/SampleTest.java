package uk.ac.qub.eeecs.gage;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import uk.ac.qub.eeecs.gage.engine.AssetStore;
import uk.ac.qub.eeecs.gage.engine.io.FileIO;
import uk.ac.qub.eeecs.gage.world.GameScreen;

import static org.junit.Assert.assertTrue;

/**
 * Created by LYBKingMao on 2018/1/26.
 */

@RunWith(AndroidJUnit4.class)
public class SampleTest {
    private GameScreen gameScreen;
    private Context context;
    @Before
    public void setUp(){
        context= InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void loadAsteroid_test(){
        AssetStore assetStore=new AssetStore((new FileIO(context)));
        assertTrue(assetStore.loadAndAddBitmap("Asteroid1","img/Asteroid1.png"));
    }
}
