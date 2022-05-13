package bzh.enib.game;

import com.badlogic.gdx.graphics.Texture;


public class BoxeGenerator extends ItemGenerator {

    private static final int BOXE_WITDH = 50;
    private static final int BOXE_HEIGHT =50;

    public BoxeGenerator()
    {
        super(new Texture("Bright/texture-caisse.jpeg"),BOXE_WITDH,BOXE_HEIGHT,300,50);
    }


}
