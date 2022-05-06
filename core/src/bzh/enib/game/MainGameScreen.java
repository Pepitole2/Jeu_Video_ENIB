package bzh.enib.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;


public class MainGameScreen implements Screen {

    public static final float CARACTER_SPEED = 160;
    public static final float BACKGROND_SPEED1 = CARACTER_SPEED/4;
    private static final int SCREE_HEIGH =1000;
    private static final int SCREE_WITHD =1600;
    private static final int FIRST_PLAN_HEIGHT =500;
    private static final int FIRST_WIDHT =600;

    private Texture skySunBackground;
    private Texture skyBackground;
    private Texture road;
    private Texture fence;
    private Texture trees;
    private Texture houseFountain;
    private Texture house;
    private float stateTime;
    AnimationPerso animationPerso;
    DesertGame game;

    private BoxeGenerator boxetest;

    private float x = 600;
    private float y = 40;
    private float backgroundX =0;
    private float FirstPlanX =0;
    private float SecondPlanX =0;
    private float ThirdPlanX =0;

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
        trees = new Texture("Bright/trees.png");
        houseFountain = new Texture("Bright/house&fountain.png");
        house = new Texture("Bright/houses2.png");
        animationPerso = new AnimationPerso();
        animationPerso.create();
        boxetest = new BoxeGenerator();

    }

    @Override

    public void render(float delta) {
        stateTime+=delta;
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();


        drawMainBackground();
        drawSecondPlan();
        drawFirstPlan();
        drawCaracterAnimation();//affiche perso méthode importante lol
        drawBoxes();

        echapToucheTouched();
        game.batch.end();
    }

    private void drawBoxes()
    {
        if(isOntheMap())
        {
            game.batch.draw(boxetest.getTexture(),FirstPlanX+boxetest.getX(),boxetest.getY(),boxetest.getWidth(),boxetest.getHeight());
        }else if(x<300) {
            game.batch.draw(boxetest.getTexture(),FirstPlanX+boxetest.getX()+CARACTER_SPEED*Gdx.graphics.getDeltaTime(),boxetest.getY(),boxetest.getWidth(),boxetest.getHeight());
        }else if(x>1300){
            game.batch.draw(boxetest.getTexture(),FirstPlanX+boxetest.getX()-CARACTER_SPEED*Gdx.graphics.getDeltaTime(),boxetest.getY(),boxetest.getWidth(),boxetest.getHeight());
        }
    }



    private void drawSecondPlan() {
        if(isOntheMap()){
            game.batch.draw(house, ThirdPlanX+ 500,0,SCREE_WITHD/3,400);
            game.batch.draw(houseFountain, SecondPlanX,0,SCREE_WITHD/3,400);
        }else if(x<300){
            game.batch.draw(house, ThirdPlanX+500,0,SCREE_WITHD/3,400);
            game.batch.draw(houseFountain, SecondPlanX,0,SCREE_WITHD/3,400);
            if(moveTotheLeft()){
                SecondPlanX += CARACTER_SPEED/4 *Gdx.graphics.getDeltaTime();
                ThirdPlanX += CARACTER_SPEED/3 *Gdx.graphics.getDeltaTime();
            }
        }else if(x>1300){
            if(moveToTheRight()){
                SecondPlanX -= CARACTER_SPEED/2 *Gdx.graphics.getDeltaTime();
                ThirdPlanX -= CARACTER_SPEED/3 *Gdx.graphics.getDeltaTime();
            }
            game.batch.draw(house, ThirdPlanX+500,0,SCREE_WITHD/3,400);
            game.batch.draw(houseFountain, SecondPlanX,0,SCREE_WITHD/3,400);

        }
        game.batch.draw(house, ThirdPlanX -SCREE_WITHD,0,SCREE_WITHD/3,400);
        game.batch.draw(house, ThirdPlanX +SCREE_WITHD,0,SCREE_WITHD/3,400);
        game.batch.draw(houseFountain, SecondPlanX -SCREE_WITHD,0,SCREE_WITHD/3,400);
        game.batch.draw(houseFountain, SecondPlanX +SCREE_WITHD,0,SCREE_WITHD/3,400);


    }

    private boolean isOntheMap() {
        return x > 300 && x < 1300;
    }

    private void drawFirstPlan() {
        if(isOntheMap())
        {
            game.batch.draw(road, FirstPlanX,0,SCREE_WITHD,350);
            drawTreesFirstPlan();
            game.batch.draw(fence, FirstPlanX,50,300,FIRST_PLAN_HEIGHT/2);
            game.batch.draw(fence, FirstPlanX + FIRST_PLAN_HEIGHT,50,300,FIRST_PLAN_HEIGHT/2);
        }else if(x<300){
            game.batch.draw(road, FirstPlanX,0,SCREE_WITHD,350);
            drawTreesFirstPlan();
            game.batch.draw(fence, FirstPlanX,50,300,FIRST_PLAN_HEIGHT/2);
            game.batch.draw(fence, FirstPlanX + FIRST_PLAN_HEIGHT,50,300,FIRST_PLAN_HEIGHT/2);
            if(moveTotheLeft()){
                FirstPlanX += CARACTER_SPEED *Gdx.graphics.getDeltaTime();
            }
        }else if(x>1300){
            if(moveToTheRight()){
                FirstPlanX -= CARACTER_SPEED *Gdx.graphics.getDeltaTime();
            }
            game.batch.draw(road, FirstPlanX,0,SCREE_WITHD,350);
            drawTreesFirstPlan();
            game.batch.draw(fence, FirstPlanX,50,300,FIRST_PLAN_HEIGHT/2);
            game.batch.draw(fence, FirstPlanX + FIRST_PLAN_HEIGHT,50,300,FIRST_PLAN_HEIGHT/2);

        }
        game.batch.draw(road, FirstPlanX -SCREE_WITHD,0,SCREE_WITHD,350);
        game.batch.draw(road, FirstPlanX +SCREE_WITHD,0,SCREE_WITHD,350);
    }

    private void drawTreesFirstPlan() {
        game.batch.draw(trees, FirstPlanX,50,FIRST_WIDHT,FIRST_PLAN_HEIGHT);
        game.batch.draw(trees, FirstPlanX +FIRST_WIDHT,50,FIRST_WIDHT,FIRST_PLAN_HEIGHT);
        game.batch.draw(trees, FirstPlanX + 2*FIRST_WIDHT,50,FIRST_WIDHT,FIRST_PLAN_HEIGHT);
        game.batch.draw(trees, FirstPlanX + 3*FIRST_WIDHT,50,FIRST_WIDHT,FIRST_PLAN_HEIGHT);
        game.batch.draw(trees, FirstPlanX + 4*FIRST_WIDHT,50,FIRST_WIDHT,FIRST_PLAN_HEIGHT);
        game.batch.draw(trees, FirstPlanX - FIRST_WIDHT,50,600,FIRST_PLAN_HEIGHT);
        game.batch.draw(trees, FirstPlanX - 2*FIRST_WIDHT,50,600,FIRST_PLAN_HEIGHT);
        game.batch.draw(trees, FirstPlanX - 3*FIRST_WIDHT,50,600,FIRST_PLAN_HEIGHT);
    }

    private void drawMainBackground() {
        if(isOntheMap())
        {
            game.batch.draw(skySunBackground,backgroundX,0,SCREE_WITHD,SCREE_HEIGH);
        }else if(x<300){
            game.batch.draw(skySunBackground,backgroundX,0,SCREE_WITHD,SCREE_HEIGH);
            if(moveTotheLeft()){
                backgroundX += BACKGROND_SPEED1 *Gdx.graphics.getDeltaTime();
            }
        }else if(x>1300){
            if(moveToTheRight()){
                backgroundX -= BACKGROND_SPEED1 *Gdx.graphics.getDeltaTime();
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
