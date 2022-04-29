package bzh.enib.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationPerso extends ApplicationAdapter {

    private static final int FRAME_COL =12,FRAME_ROWS=4;

    SpriteBatch batch;
    Animation<TextureRegion> walkAnimationRightDirection;
    Animation<TextureRegion> walkAnimationLeftDirection;
    Animation<TextureRegion> imobileAnimation;
    Texture walkSheet;
    float stateTime;

    @Override
    public void create() {
        walkSheet = new Texture(Gdx.files.internal("sprite_animation.png"));

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth()/FRAME_COL,
                walkSheet.getHeight()/FRAME_ROWS);

        rightDirectionFrames(tmp);
        leftDirectionFrames(tmp);
        imobileFrames(tmp);

        batch = new SpriteBatch();
        stateTime = 0f;
    }



    public TextureRegion walkAnimationRight(float delta)
    {
        stateTime+= delta;
        TextureRegion currentFrame;
        return currentFrame = walkAnimationRightDirection.getKeyFrame(stateTime, true);
    }

    public TextureRegion walkAnimationLeft(float delta)
    {
        stateTime+= delta;
        TextureRegion currentFrame;
        return currentFrame = walkAnimationLeftDirection.getKeyFrame(stateTime, true);
    }

    public TextureRegion imobileAnimation(float delta)
    {
        stateTime+= delta;
        TextureRegion currentFrame;
        return currentFrame = imobileAnimation.getKeyFrame(stateTime, true);
    }

    private void rightDirectionFrames(TextureRegion[][] tmp) {
        TextureRegion[] walkFramesRightDirection = new TextureRegion[1*FRAME_COL];
        int index = 0;
        //for(int i=0;i<FRAME_ROWS-3;i++)
        //{
        for(int j=0;j<FRAME_COL;j++)
        {
            walkFramesRightDirection[index++]= tmp[2][j];
        }
        //}
        walkAnimationRightDirection = new Animation<TextureRegion>(0.04f,walkFramesRightDirection);
    }

    private void leftDirectionFrames(TextureRegion[][] tmp) {
        TextureRegion[] walkFramesLeftDirection = new TextureRegion[1*FRAME_COL];
        int index = 0;
        //for(int i=0;i<FRAME_ROWS-3;i++)
        //{
        for(int j=0;j<FRAME_COL;j++)
        {
            walkFramesLeftDirection[index++]= tmp[1][j];
        }
        //}
        walkAnimationLeftDirection = new Animation<TextureRegion>(0.04f,walkFramesLeftDirection);
    }

    private void imobileFrames(TextureRegion[][] tmp) {
        TextureRegion[] walkFramesLeftDirection = new TextureRegion[1*FRAME_COL];
        int index = 0;
        //for(int i=0;i<FRAME_ROWS-3;i++)
        //{
        for(int j=0;j<FRAME_COL;j++)
        {
            walkFramesLeftDirection[index++]= tmp[0][j];
        }
        //}
        imobileAnimation = new Animation<TextureRegion>(0.08f,walkFramesLeftDirection);
    }



}
