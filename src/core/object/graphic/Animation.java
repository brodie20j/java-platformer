package core.object.graphic;

import core.object.graphic.SpriteSheet;

/**
 * Created by jonathanbrodie on 11/15/15.
 */
public class Animation {
    SpriteSheet spriteSheet;
    private int start;
    private int end;

    //static variables for constants
    public static String JUMP_CONSTANT="ANIMATION_JUMP";
    public static String DEFAULT_CONSTANT="ANIMATION_DEFAULT";
    public static String ATTACK_CONSTANT="ANIMATION_ATTACK";
    public static String MOVE_CONSTANT="ANIMATION_MOVE";
    public static String DEAD_CONSTANT="ANIMATION_DEAD";

    public Animation(SpriteSheet sheet, int startIndex, int endIndex) {
        this.spriteSheet=sheet;
        this.start=startIndex;
        this.end=endIndex;
    }

    public int getStart() {
        return this.start;
    }
    public int getEnd() {
        return this.end;
    }
}
