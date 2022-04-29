package bzh.enib.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


public class MainGameScreen implements Screen {

    public static final float SPEED = 60;

    private Texture sky;
    private Texture road;
    private Texture fence;
    private float stateTime;
    AnimationPerso animationPerso;
    DesertGame game;



    private float x = 15;
    private float y = 30;

    public MainGameScreen(DesertGame game)
    {
        this.game=game;
    }

    @Override
    public void show() {
        sky = new Texture("Bright/sky.png");
        road = new Texture("Bright/road.png");
        fence = new Texture("Bright/fence.png");
        animationPerso = new AnimationPerso();
        animationPerso.create();

    }

    @Override
    public void render(float delta) {
        stateTime+=delta;
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(sky,0,0);
        game.batch.draw(road,0,0,1600,350);

        if(moveToTheRight()){
            game.batch.draw(animationPerso.walkAnimationRight(Gdx.graphics.getDeltaTime()) ,x,y);
        }
        else if(moveTotheLeft()){
            game.batch.draw(animationPerso.walkAnimationLeft(Gdx.graphics.getDeltaTime()) ,x,y);
        }else{
            game.batch.draw(animationPerso.imobileAnimation(Gdx.graphics.getDeltaTime()) ,x,y);
        }

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

    private boolean moveTotheLeft() {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            x-= SPEED *Gdx.graphics.getDeltaTime();
            return true;
        }
        else
        {
            x=x;
            return false;
        }
    }

    private boolean moveToTheRight() {
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            x+= SPEED *Gdx.graphics.getDeltaTime();
            return true;
        }
        else
        {
            x=x;
            return false;
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
