package bzh.enib.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


public class MainGameScreen implements Screen {

    public static final float CARACTER_SPEED = 160;
    private static final int SCREE_HEIGH =1000;
    private static final int SCREE_WITHD =1600;

    private Texture skySunBackground;
    private Texture skyBackground;
    private Texture road;
    private Texture fence;
    private float stateTime;
    AnimationPerso animationPerso;
    DesertGame game;



    private float x = 600;
    private float y = 40;
    private float backgroundX =0;


    public MainGameScreen(DesertGame game)
    {
        this.game=game;
    }

    @Override
    public void show() {
        skySunBackground = new Texture("Bright/sky_sun.png");
        skyBackground = new Texture("Bright/sky.png");
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


        drawMainBackground();

        game.batch.draw(road,0,0,SCREE_WITHD,350);

        drawCaracterAnimation();//affiche perso méthode importante lol

        game.batch.draw(fence,Gdx.graphics.getWidth()/2,50,300,150);


        echapToucheTouched();
        game.batch.end();


    }

    private void drawMainBackground() {
        if(x>300 && x<1300)
        {
            game.batch.draw(skySunBackground,backgroundX,0,SCREE_WITHD,SCREE_HEIGH);
        }else if(x<300){
            game.batch.draw(skySunBackground,backgroundX,0,SCREE_WITHD,SCREE_HEIGH);
            if(moveTotheLeft()){
                backgroundX += CARACTER_SPEED *Gdx.graphics.getDeltaTime();
            }
        }else if(x>1300){
            if(moveToTheRight()){
                backgroundX -= CARACTER_SPEED *Gdx.graphics.getDeltaTime();
            }
            game.batch.draw(skySunBackground,backgroundX,0,SCREE_WITHD,SCREE_HEIGH);

        }
        game.batch.draw(skyBackground,backgroundX-SCREE_WITHD,0,SCREE_WITHD,SCREE_HEIGH);
        game.batch.draw(skyBackground,backgroundX+SCREE_WITHD,0,SCREE_WITHD,SCREE_HEIGH);
    }

    private void drawCaracterAnimation() {
        if(moveToTheRight()){
            game.batch.draw(animationPerso.walkAnimationRight(Gdx.graphics.getDeltaTime()) ,x,y);
        }
        else if(moveTotheLeft()){
            game.batch.draw(animationPerso.walkAnimationLeft(Gdx.graphics.getDeltaTime()) ,x,y);
        }else{
            game.batch.draw(animationPerso.imobileAnimation(Gdx.graphics.getDeltaTime()) ,x,y);
        }
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
            y+= CARACTER_SPEED *Gdx.graphics.getDeltaTime();
        }
        else
        {
            y=y;
        }
    }

    private void moveToTheTop() {
        if(Gdx.input.isKeyPressed(Input.Keys.S))
        {
            y-= CARACTER_SPEED *Gdx.graphics.getDeltaTime();
        }
        else
        {
            y=y;
        }
    }

    private boolean moveTotheLeft() {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            if(x>300)
            {
                x-= CARACTER_SPEED *Gdx.graphics.getDeltaTime();
            }
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
            if(x<1300)
            {
                x+= CARACTER_SPEED *Gdx.graphics.getDeltaTime();
            }
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
