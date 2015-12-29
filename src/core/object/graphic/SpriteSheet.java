package core.object.graphic;


import java.io.ByteArrayOutputStream;
import core.object.GameObject;
import core.util.Pair;

import javax.imageio.ImageIO;
/**
 * Created by jonathanbrodie on 8/30/15.
 */
public abstract class SpriteSheet implements GraphicComponent {


    private Pair[] sheet;
    private int currentIndex;


    public SpriteSheet(String pathToFile, int owidth, int oheight, int orows, int ocols) {
        //create sheet of arbitrary size that has a specific number of buffered images
        int width=owidth;
        int height=oheight;
        int rows=orows;
        int cols=ocols;
        String path=pathToFile;

        this.sheet=new Pair[cols*rows];
        int count=0;
        //first, load the image from the path
            for (int i=0; i<rows; i++) {
                count=i*cols;
                for (int j = 0; j <cols; j++) {
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    this.sheet[count] = new Pair(i*width,j*height);
                    count++;
                }
            }
        this.setPointer(0);

    }


    public Pair getCurrentImage() {
        return this.sheet[this.currentIndex];
    }
    public Pair getImageAt(int x) {
        return this.sheet[x];
    }
    public void setPointer(int value) {
        this.currentIndex=value;
    }
    public int getPointer() {
        return this.currentIndex;
    }

    public abstract void updateGraphics(GameObject state);
}
