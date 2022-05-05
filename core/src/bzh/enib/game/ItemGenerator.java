package bzh.enib.game;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class ItemGenerator {

    private Texture texture;
    private Rectangle itemSurface;
    private int x,y;
    private int sizeX,sizeY;

    public ItemGenerator(Texture texture,int sizeX,int sizeY)
    {
        this.texture=texture;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        itemSurface = new Rectangle();
    }


    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }
}
