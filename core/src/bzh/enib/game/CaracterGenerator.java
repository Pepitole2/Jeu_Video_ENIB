package bzh.enib.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class CaracterGenerator {

    private AnimationPerso animationPerso;
    private int x,y;
    private int width,height;
    private int health,thirst,radiation;
    public RectangleGenetator healthBare;

    public CaracterGenerator(){
        this.animationPerso = new AnimationPerso();
        this.animationPerso.create();
        this.x=500;
        this.y=50;
        this.width=95;
        this.height=158;
        this.health = 200;
        this.thirst=100;
        this.radiation=100;
        this.healthBare =  new RectangleGenetator(100,850,health,20, Color.GREEN);
    }


    public Boolean ifDied(){
        if(health<=0)
        {
            return true;
        }else{
            return false;
        }
    }

    public  void takeCare(int care){
        if(health<200)
        {
            health = health + care;
        }
    }

    public void takeDamage(int damage){
        health = health - damage;
        ifDied();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getThirst() {
        return thirst;
    }

    public void setThirst(int thirst) {
        this.thirst = thirst;
    }

    public int getRadiation() {
        return radiation;
    }

    public void setRadiation(int radiation) {
        this.radiation = radiation;
    }

    public AnimationPerso getAnimationPerso() {
        return animationPerso;
    }

    public void setAnimationPerso(AnimationPerso animationPerso) {
        this.animationPerso = animationPerso;
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
}
