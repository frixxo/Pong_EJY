package Graphics;

import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import model.Ball;
import model.IGameObject;
import model.Paddle;

import java.util.HashMap;
import java.util.Map;


/*
   Defining all assets used by application
   Common assets and asset handling (for all themes) here
   For specific handling see classes in theme/

   *** Nothing to do here ***

*/

public abstract class Assets {

    private final String IMAGE_DIR = "file:Assets/img/";
    private final String SOUND_DIR = "file:Assets/sound/";

    // A Map to store which image belongs to which object
    private final Map<Object, Image> objectImage = new HashMap<>();

    // ------------ Handling Colors and Images ------------------------
    public final Color colorFgText = Color.WHITE;
    final Image menupic = getImage("pong.png");
    static Image background;


    {
        // bind common, for all themes, objects/classes  (none right now)
    }


    // -------------- Audio handling -----------------------------

    //TODO Ljud     public AudioClip hitsound = getSound("ballhitpaddle.wav");

    // -------------- Methods binding objects/classes to assets -----------------

    public void bind(Object object, String imageFileName) {
        Image i = getImage(imageFileName);
        if (i != null) {
            objectImage.put(object, getImage(imageFileName));
        } else {
            throw new IllegalArgumentException("Missing image: " + IMAGE_DIR + imageFileName);
        }
    }

    // Get image to render
    public Image get(Object object) {
        Image i = objectImage.get(object);  // Try to find bound object
        if (i == null) {
            return get(object.getClass());  // .. if fail, check if class bound
        }
        return i;   // possible null, will throw exception, OK!
    }

    private Image get(Class<?> clazz) {
        return objectImage.get(clazz);
    }


    // ---------- Helpers ------------------------
    // protected means sub classes can access
    protected Image getImage(String fileName) {
        return new Image(IMAGE_DIR + fileName);
    }

    private AudioClip getSound(String fileName) {
        return new AudioClip(SOUND_DIR + fileName);
    }

    public Image GetBackground() {
        return background;
    }

}
