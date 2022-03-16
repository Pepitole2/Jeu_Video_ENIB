package bzh.enib.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

public class MainMenuScreen implements Screen {

    private static final int PLAY_BUTTON_WIDTH = 300;
    private static final int PLAY_BUTTON_HEIGHT = 80;
    //private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 80;

    private final int  xButton =Gdx.graphics.getWidth()/2-PLAY_BUTTON_WIDTH/2;
    private final int yPlayButton =Gdx.graphics.getHeight()/2+PLAY_BUTTON_HEIGHT/2;
    private final int yExitButton = Gdx.graphics.getHeight()/2-2*EXIT_BUTTON_HEIGHT;

    private DesertGame game;
    private Texture exitButtonActive;
    private Texture exitButtonDesactive;
    private Texture playButtonActive;
    private Texture playButtonDesactive;

    public MainMenuScreen(DesertGame game)
    {
        //passage du jeu en parametre
        this.game=game;
        playButtonActive = new Texture("playActiveButton.png");
        playButtonDesactive = new Texture("playDesactiveButton.png");
        exitButtonActive = new Texture("exitActiveButton.png");
        exitButtonDesactive = new Texture("exitDesactiveButton.png");
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        //reset de l'image en rendu
        Gdx.gl.glClearColor(0,0,1,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();

        playButtonActivation(xButton, yPlayButton);
        exitButtonActivation(xButton,yExitButton);

        game.batch.end();
    }

    private void exitButtonActivation(int x, int yExitButton) {
        if(isMouseIsOnExitButton(x, yExitButton))
        {
            game.batch.draw(exitButtonDesactive, x,
                    yExitButton,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched())
            {
                Gdx.app.exit();
            }
        }else{
            game.batch.draw(exitButtonActive,x,
                    yExitButton,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }
    }

    private boolean isMouseIsOnExitButton(int x, int y) {
        return Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x &&
                Gdx.input.getY() < y + 4*PLAY_BUTTON_HEIGHT && Gdx.input.getY() > y+ 3*PLAY_BUTTON_HEIGHT ;
    }
    private void playButtonActivation(int x, int y) {
        if(isMouseIsOnPlayButton(x, y))
        {
            game.batch.draw(playButtonDesactive, x,
                    y,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            if(Gdx.input.isTouched())
            {
                this.dispose();
                game.setScreen(new MainGameScreen(game));
            }
        }else{
            game.batch.draw(playButtonActive,Gdx.graphics.getWidth()/2-PLAY_BUTTON_WIDTH/2,
                    Gdx.graphics.getHeight()/2+PLAY_BUTTON_HEIGHT/2,PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }
    }

    private boolean isMouseIsOnPlayButton(int x, int y) {
        return Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x &&
                Gdx.input.getY() < y - PLAY_BUTTON_HEIGHT && Gdx.input.getY() > y - PLAY_BUTTON_HEIGHT * 2;
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
