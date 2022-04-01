package bzh.enib.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MainGameScreen implements Screen {

    public static final float SPEED = 60;
    public static final float ANIMATION_SPEED=0.5f;

    private Texture sky;
    private Texture road;
    private Texture fence;
    private float stateTime;

    Animation[] rolls;

    private Texture imgCaracter1;


    private float x = 15;
    private float y = 30;

    DesertGame game;

    public MainGameScreen(DesertGame game)
    {
        this.game=game;

        rolls = new Animation[7];
    }

    @Override
    public void show() {
        imgCaracter1 = new Texture("AnimationPerso/cour_0.png");
        sky = new Texture("Bright/sky.png");
        road = new Texture("Bright/road.png");
        fence = new Texture("Bright/fence.png");
    }

    @Override
    public void render(float delta) {
        stateTime+=delta;
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(sky,0,0);
        game.batch.draw(road,0,0,1600,350);

       //game.batch.draw(rolls[2].getKeyFrame(stateTime,false),x,y,CARCATER_WIDHT_PIXEL,CARCATER_HEIGHT_PIXEL);
        game.batch.draw(imgCaracter1,x,y);
        game.batch.draw(fence,Gdx.graphics.getWidth()/2,50,300,150);
        moveTest();
        echapToucheTouched();
        game.batch.end();


    }

    private void echapToucheTouched() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }
    }

    private void moveTest() {
        moveToTheRight();
        moveTotheLeft();
        moveToTheTop();
        moveTotheDown();
    }

    private void moveTotheDown() {
        if(Gdx.input.isKeyPressed(Input.Keys.W))
        {
            y+= SPEED *Gdx.graphics.getDeltaTime();
        }
        else
        {
            y=y;
        }
    }

    private void moveToTheTop() {
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            y-= SPEED *Gdx.graphics.getDeltaTime();
        }
        else
        {
            y=y;
        }
    }

    private void moveTotheLeft() {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            x-= SPEED *Gdx.graphics.getDeltaTime();
        }
        else
        {
            x=x;
        }
    }

    private void moveToTheRight() {
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            x+= SPEED *Gdx.graphics.getDeltaTime();
        }
        else
        {
            x=x;
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
