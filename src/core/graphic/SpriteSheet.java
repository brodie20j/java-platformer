package core.graphic;


import java.io.ByteArrayOutputStream;
import core.GameObject;
import core.util.Pair;

/**
 * Created by jonathanbrodie on 8/30/15.
 */
public abstract class SpriteSheet implements GraphicComponent {


    private Pair[] sheet;


    public SpriteSheet( int owidth, int oheight, int orows, int ocols) {
        //create sheet of arbitrary size that has a specific number of buffered images
        int width=owidth;
        int height=oheight;
        int rows=orows;
        int cols=ocols;
        this.sheet=new Pair[cols*rows];
        //first, load the image from the path
            for (int i=0; i<rows; i++) {
                int count=i*cols;
                for (int j = 0; j <cols; j++) {
                    this.sheet[count] = new Pair(i*width,j*height);
                    count++;
                }
            }
    }
    public Pair getImageAt(int x) {
        return this.sheet[x];
    }

    public abstract void updateGraphics(GameObject state);
}
