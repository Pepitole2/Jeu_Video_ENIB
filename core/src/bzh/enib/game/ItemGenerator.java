package bzh.enib.game;

import com.badlogic.gdx.graphics.Texture;

import java.awt.*;

public class ItemGenerator {

    private Texture itemTexture;
    private Rectangle itemSurface;
    private int x,y;
    private int sizeX,sizeY;

    public ItemGenerator(Texture itemTexture,int sizeX,int sizeY)
    {
        this.itemTexture =itemTexture;
        this.sizeX=sizeX;
        this.sizeY=sizeY;
        this.x=x;
        this.y=y;
    }

    private Rectangle spawnItem()
    {
        itemSurface = new Rectangle();
        itemSurface.width = sizeX;
        itemSurface.height = sizeY;
        return itemSurface;
    }

    public Texture getItemTexture() {
        return itemTexture;
    }

    public void setItemTexture(Texture itemTexture) {
        this.itemTexture = itemTexture;
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
