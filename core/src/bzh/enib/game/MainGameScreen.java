package bzh.enib.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainGameScreen implements Screen {

    public static final float SPEED = 50;

    private Texture img;
    private float x = 0;
    private float y = 0;

    DesertGame game;

    public MainGameScreen(DesertGame game)
    {
        this.game=game;
    }

    @Override
    public void show() {
        img = new Texture("badlogic.png");

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(img,x,y);
        moveTest();
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }
        game.batch.end();


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
