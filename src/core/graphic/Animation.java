package core.graphic;

/**
 * Created by jonathanbrodie on 11/15/15.
 */
public class Animation {
    private int start;
    private int end;

    //static variables for constants
    private static final int NUM_CONSTANT=5;
    public final static String JUMP_CONSTANT="ANIMATION_JUMP";
    public final static String DEFAULT_CONSTANT="ANIMATION_DEFAULT";
    public final static String ATTACK_CONSTANT="ANIMATION_ATTACK";
    public final static String MOVE_CONSTANT="ANIMATION_MOVE";
    public final static String DEAD_CONSTANT="ANIMATION_DEAD";

    public Animation(int startIndex, int endIndex) {
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
