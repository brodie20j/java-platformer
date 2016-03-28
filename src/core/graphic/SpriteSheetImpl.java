package core.graphic;

import core.GameActor;
import core.GameObject;
import core.GamePlayer;

import java.util.Hashtable;
import java.util.Map;
/**
 * Created by jonathanbrodie on 9/23/15.
 */
public class SpriteSheetImpl extends SpriteSheet {

    public String path;
    private int width;
    private int height;
    //hash table to hold animations
    private Map<String, Animation> animationMap;
    private boolean deadFlag;
    //rows and columns are backward for some reason!
    public SpriteSheetImpl(String pathToFile, int owidth, int oheight, int cols, int rows) {
        super(owidth,oheight,cols,rows);
        this.width = owidth;
        this.height = oheight;
        this.path=pathToFile;
        this.animationMap=new Hashtable<>();
        deadFlag=false;
    }

    public static SpriteSheetImpl buildSpriteSheet(Animations[] animationNames, Animation[] animations, String path, int width, int height, int rows, int cols) {
        SpriteSheetImpl ssl=new SpriteSheetImpl(path,width,height,rows,cols);

        if (animationNames.length!=animations.length) {
            throw new RuntimeException();
        }

        for (int i=0; i<animations.length; i++)
            ssl.animationMap.put(animationNames[i].getAnimationConstant(),animations[i]);

        return ssl;
    }
    public void updateGraphics(GameObject object) {
        //game logic here
        String anim=Animation.DEFAULT_CONSTANT;
        //if attacking play attack animation

        if (object instanceof GameActor && (!deadFlag)) {
            GameActor actor=(GameActor) object;
            if (actor.CURRENT_HP <= 0) {
                deadFlag=true;
                anim=Animation.DEAD_CONSTANT;
            }
             else if (actor.ATTACK_FLAG) {

                 anim=Animation.ATTACK_CONSTANT;
                 //end of animation?
             }
            else if (actor.getActorPhysics().inAir()) {
                 anim=Animation.JUMP_CONSTANT;
             }
            else if (actor.getVelocity().getX() != 0) {
                 anim=Animation.MOVE_CONSTANT;
             }


        }
        else if (deadFlag) {
            anim=Animation.DEAD_CONSTANT;
        }
        //System.out.println(anim);
        this.playAnimation(anim,object);

    }

    public void addAnimation(String name, int start, int end) {
        this.animationMap.put(name, new Animation(start,end));
    }

    //move the pointer shit to the instances of the objects not here
    private void playAnimation(String animationName, GameObject object) {

        Animation animation=this.animationMap.get(animationName);
        if ((object.getPointer() >= animation.getStart()) || (object.getPointer() <= animation.getEnd())) {
            object.setPointer(object.getPointer()+1);
        } else {
            object.setPointer(animation.getStart());
        }
        if (object.getPointer() >= animation.getEnd()) {
            object.setPointer(animation.getStart());
            if (animationName.equals(Animation.ATTACK_CONSTANT)) {
                GameActor actor=(GameActor) object;
                actor.ATTACK_FLAG=false;
            }
            if (animationName.equals(Animation.DEAD_CONSTANT)) {
                this.deadFlag=true;
            }
        }

    }
    public Animation animation(String name) {
        return this.animationMap.get(name);
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
    public String getPath() {
        return this.path;
    }
    public boolean getDead() {
        return this.deadFlag;
    }

}
