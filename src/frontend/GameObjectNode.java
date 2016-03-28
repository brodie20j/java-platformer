package frontend;
import core.GameActor;
import core.GameObject;
import core.GamePlayer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import core.util.Pair;
import javafx.scene.paint.Color;

/**
 * Created by jonathanbrodie on 9/12/15.
 */
public class GameObjectNode extends Group {
    private ImageView imageView;

    GameObject myObject;

    public GameObjectNode(GameObject parent) {
        this.myObject=parent;
    }
    public static GameObjectNode createNode(GameObject parent) {
        GameObjectNode node=new GameObjectNode(parent);
        return node;

    }
    public  void draw() {
        this.getChildren().clear();
        Pair currentImage = this.myObject.getGraphics().getImageAt(this.myObject.getPointer());
        Image image = new Image(this.myObject.getGraphics().getPath());
        WritableImage image1=new WritableImage(this.myObject.getGraphics().getWidth(),this.myObject.getGraphics().getHeight());



        for (int i=0; i < this.myObject.getGraphics().getWidth(); i++) {
            for (int j=0; j < this.myObject.getGraphics().getHeight();j++) {
                Color color;
                try {
                    if (this.myObject.getVelocity().getX() >= 0) {
                        color=image.getPixelReader().getColor(currentImage.getFirst()+this.myObject.getGraphics().getWidth()-i,currentImage.getSecond()+j);
                    }
                     else
                        color=image.getPixelReader().getColor(currentImage.getFirst()+i,currentImage.getSecond()+j);
                    image1.getPixelWriter().setColor(i,j,color);

                  //  image1.getPixelWriter().setColor(i, j, image.getPixelReader().getColor(currentImage.getFirst() + i, currentImage.getSecond() + j));
                }
                catch (IndexOutOfBoundsException e) {
                    //System.out.println("Out of bounds: "+(currentImage.getFirst()+i)+", "+(currentImage.getSecond()+j));
                }
            }
        }
        this.imageView = new ImageView();
        this.imageView.setImage(image1);
        this.getChildren().add(this.imageView);
        this.setLayoutX(this.myObject.getCurrentPosition().getX());
        this.setLayoutY(this.myObject.getCurrentPosition().getY()*-1);
    }
}
