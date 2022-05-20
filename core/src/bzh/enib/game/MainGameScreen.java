package bzh.enib.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class MainGameScreen implements Screen {

    public static final float CARACTER_SPEED = 160;
    public static final float BACKGROND_SPEED1 = CARACTER_SPEED/4;
    private static final int SCREE_HEIGH =1000;
    private static final int SCREE_WITHD =1600;
    private static final int FIRST_PLAN_HEIGHT =500;
    private static final int FIRST_WIDHT =600;
    private static final int RIGHT_BORDER = 1300;
    private static final int LEFT_BORDER = 300;

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
    private ShapeRenderer shapeRenderer;


    private CaracterGenerator mainCaracter;
    private int xMainCaracter;

    private BoxeGenerator boxetest;


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
        shapeRenderer = new ShapeRenderer();
        skySunBackground = new Texture("Bright/sky_sun.png");
        skyBackground = new Texture("Bright/sky.png");
        road = new Texture("Bright/road.png");
        fence = new Texture("Bright/fence.png");
        trees = new Texture("Bright/trees.png");
        houseFountain = new Texture("Bright/house&fountain.png");
        house = new Texture("Bright/houses2.png");
        boxetest = new BoxeGenerator();
        mainCaracter = new CaracterGenerator();
        xMainCaracter = mainCaracter.getX();
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
        drawBoxes();
        drawMainCaracter();
        echapToucheTouched();

        game.batch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        mainCaracter.healthBare.draw(shapeRenderer);
        shapeRenderer.end();

        testTakeCareTakeDamage();

        //Parti a compléter
        if(mainCaracter.ifDied())
        {
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }

        if(mainCaracter.collisionRectangle.overlaps(boxetest.collision)){
            mainCaracter.takeDamage(1);
            mainCaracter.healthBare.setWidth(mainCaracter.getHealth());
        }

        if(Gdx.input.isKeyPressed(Input.Keys.P)){
            System.out.println(boxetest.collision);
            System.out.println(mainCaracter.collisionRectangle);
        }


    }

    private void testTakeCareTakeDamage() {
        if(Gdx.input.isKeyPressed(Input.Keys.T))
        {
            mainCaracter.takeDamage(1);
            mainCaracter.healthBare.setWidth(mainCaracter.getHealth());
        }
        if(Gdx.input.isKeyPressed(Input.Keys.Y))
        {
            mainCaracter.takeCare(1);
            mainCaracter.healthBare.setWidth(mainCaracter.getHealth());
        }
    }


    private void drawMainCaracter() {
        if(moveToTheRight()){
            game.batch.draw(mainCaracter.getAnimationPerso().walkAnimationRight(Gdx.graphics.getDeltaTime()), xMainCaracter,y);
        }
        else if(moveTotheLeft()){
            game.batch.draw(mainCaracter.getAnimationPerso().walkAnimationLeft(Gdx.graphics.getDeltaTime()), xMainCaracter,y);
        }else{
            game.batch.draw(mainCaracter.getAnimationPerso().imobileAnimation(Gdx.graphics.getDeltaTime()), xMainCaracter,y);
        }
    }

    private void drawBoxes()
    {
        if(isOntheMap())
        {
            game.batch.draw(boxetest.getTexture(),FirstPlanX+boxetest.getX(),boxetest.getY(),boxetest.getWidth(),boxetest.getHeight());
        }else if(isOnTheLeftBorder()) {
            boxetest.collision.setX(FirstPlanX+boxetest.getX());
            game.batch.draw(boxetest.getTexture(),FirstPlanX+boxetest.getX()+CARACTER_SPEED*Gdx.graphics.getDeltaTime(),boxetest.getY(),boxetest.getWidth(),boxetest.getHeight());
        }else if(isOnTheRightBorder()){
            boxetest.collision.setX(FirstPlanX+boxetest.getX());
            game.batch.draw(boxetest.getTexture(),FirstPlanX+boxetest.getX()-CARACTER_SPEED*Gdx.graphics.getDeltaTime(),boxetest.getY(),boxetest.getWidth(),boxetest.getHeight());
        }
    }



    private void drawSecondPlan() {
        if(isOntheMap()){
            game.batch.draw(house, ThirdPlanX+ 500,0,SCREE_WITHD/3,400);
            game.batch.draw(houseFountain, SecondPlanX,0,SCREE_WITHD/3,400);
        }else if(isOnTheLeftBorder()){
            game.batch.draw(house, ThirdPlanX+500,0,SCREE_WITHD/3,400);
            game.batch.draw(houseFountain, SecondPlanX,0,SCREE_WITHD/3,400);
            if(moveTotheLeft()){
                SecondPlanX += CARACTER_SPEED/4 *Gdx.graphics.getDeltaTime();
                ThirdPlanX += CARACTER_SPEED/3 *Gdx.graphics.getDeltaTime();
            }
        }else if(isOnTheRightBorder()){
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
        return mainCaracter.getX() > 300 && mainCaracter.getX() < 1300;
    }

    private void drawFirstPlan() {
        if(isOntheMap())
        {
            game.batch.draw(road, FirstPlanX,0,SCREE_WITHD,350);
            drawTreesFirstPlan();
            game.batch.draw(fence, FirstPlanX,50,300,FIRST_PLAN_HEIGHT/2);
            game.batch.draw(fence, FirstPlanX + FIRST_PLAN_HEIGHT,50,300,FIRST_PLAN_HEIGHT/2);
        }else if(isOnTheLeftBorder()){
            game.batch.draw(road, FirstPlanX,0,SCREE_WITHD,350);
            drawTreesFirstPlan();
            game.batch.draw(fence, FirstPlanX,50,300,FIRST_PLAN_HEIGHT/2);
            game.batch.draw(fence, FirstPlanX + FIRST_PLAN_HEIGHT,50,300,FIRST_PLAN_HEIGHT/2);
            if(moveTotheLeft()){
                FirstPlanX += CARACTER_SPEED *Gdx.graphics.getDeltaTime();
            }
        }else if(isOnTheRightBorder()){
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
        }else if(isOnTheLeftBorder()){
            game.batch.draw(skySunBackground,backgroundX,0,SCREE_WITHD,SCREE_HEIGH);
            if(moveTotheLeft()){
                backgroundX += BACKGROND_SPEED1 *Gdx.graphics.getDeltaTime();
            }
        }else if(isOnTheRightBorder()){
            if(moveToTheRight()){
                backgroundX -= BACKGROND_SPEED1 *Gdx.graphics.getDeltaTime();
            }
            game.batch.draw(skySunBackground,backgroundX,0,SCREE_WITHD,SCREE_HEIGH);

        }
        game.batch.draw(skyBackground,backgroundX-SCREE_WITHD,0,SCREE_WITHD,SCREE_HEIGH);
        game.batch.draw(skyBackground,backgroundX+SCREE_WITHD,0,SCREE_WITHD,SCREE_HEIGH);
    }

    private boolean isOnTheLeftBorder() {
        return mainCaracter.getX() <= LEFT_BORDER;
    }

    private boolean isOnTheRightBorder() {
        return  mainCaracter.getX() >= RIGHT_BORDER;
    }


    private void echapToucheTouched() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
        {
            this.dispose();
            game.setScreen(new MainMenuScreen(game));
        }
    }


    private boolean moveTotheLeft() {
        if(Gdx.input.isKeyPressed(Input.Keys.A))
        {
            if(mainCaracter.getX()>300)
            {
                xMainCaracter -=CARACTER_SPEED *Gdx.graphics.getDeltaTime();
                mainCaracter.setX(xMainCaracter);
                mainCaracter.collisionRectangle.setX(xMainCaracter);
            }
            return true;
        }
        else
        {
            return false;
        }
    }

    private boolean moveToTheRight() {
        if(Gdx.input.isKeyPressed(Input.Keys.D))
        {
            if(mainCaracter.getX()<1300)
            {
                xMainCaracter +=CARACTER_SPEED *Gdx.graphics.getDeltaTime();
                mainCaracter.setX(xMainCaracter);
                mainCaracter.collisionRectangle.setX(xMainCaracter);
            }
            return true;
        }
        else
        {
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
