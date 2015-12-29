package core.object.graphic;

import core.object.GameActor;
import core.object.GameObject;

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

    public SpriteSheetImpl(String pathToFile, int owidth, int oheight, int rows, int cols) {
        super(pathToFile,owidth,oheight,rows,cols);
        this.width = owidth;
        this.height = oheight;
        this.path=pathToFile;
        this.animationMap=new Hashtable<>();
        deadFlag=false;
    }

    public void updateGraphics(GameObject object) {
        //game logic here
        String anim=Animation.DEFAULT_CONSTANT;
        //if attacking play attack animation
        if (object instanceof GameActor) {
            GameActor actor=(GameActor) object;
            if (actor.CURRENT_HP <= 0) {
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
        this.playAnimation(anim,object);
    }

    public void addAnimation(String name, int start, int end) {
        this.animationMap.put(name, new Animation(this,start,end));
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
