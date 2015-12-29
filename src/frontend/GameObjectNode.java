package frontend;
import core.object.GameObject;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import core.util.Pair;
/**
 * Created by jonathanbrodie on 9/12/15.
 */
public class GameObjectNode extends Group {
    private ImageView imageView;

    GameObject myObject;

    public GameObjectNode(GameObject parent) {
        this.myObject=parent;
    }
    public void draw() {
        this.getChildren().clear();
        Pair currentImage = this.myObject.getGraphics().getImageAt(this.myObject.getPointer());
        Image image = new Image(this.myObject.getGraphics().getPath());
        WritableImage image1=new WritableImage(this.myObject.getGraphics().getWidth(),this.myObject.getGraphics().getHeight());
        for (int i=0; i< this.myObject.getGraphics().getWidth(); i++) {
            for (int j=0; j< this.myObject.getGraphics().getHeight();j++) {
                image1.getPixelWriter().setColor(i,j,image.getPixelReader().getColor(currentImage.getFirst()+i,currentImage.getSecond()+j));
            }
        }
        this.imageView = new ImageView();
        this.imageView.setImage(image1);
        this.getChildren().add(this.imageView);
        this.setLayoutX(this.myObject.getCurrentPosition().getX());
        this.setLayoutY(this.myObject.getCurrentPosition().getY()*-1);
    }
}
