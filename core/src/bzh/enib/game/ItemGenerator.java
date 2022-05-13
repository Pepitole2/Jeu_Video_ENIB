package bzh.enib.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;


public class ItemGenerator {

    private Texture texture;
    public Rectangle collision;
    private int width, height;
    private int x,y;

    public ItemGenerator(Texture texture, int width, int height, int x, int y){
        this.texture=texture;
        this.width = width;
        this.height = height;
        this.x =x;
        this.y =y;
        this.collision = new Rectangle(x,y,width,height);
    }



    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Rectangle getCollision() {
        return collision;
    }

    public void setCollision(Rectangle collision) {
        this.collision = collision;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
