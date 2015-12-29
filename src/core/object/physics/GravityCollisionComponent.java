package core.object.physics;

import core.Level;
import core.Position;
import core.object.GameObject;

import java.util.List;

/**
 * Created by jonathanbrodie on 9/12/15.
 */
public class GravityCollisionComponent extends CollisionComponentImpl implements GravityComponent {

    private boolean inAir;
    private boolean solid;

    public GravityCollisionComponent(boolean bsolid) {
        this.solid=bsolid;
        this.inAir=true;
    }

    @Override
    public void update(GameObject object, Level level) {
        this.inAir=true;
        super.update(object,level);

    }
    public void gravity(GameObject object) {
        if (this.inAir)
            object.getVelocity().setY(object.getVelocity().getY()+GRAVITY_CONSTANT);
            if (object.getVelocity().getY() < -15) {
                object.getVelocity().setY(-15);
            }

    }
    public boolean isSolid() {
        return this.solid;
    }
    public boolean inAir() {
        return this.inAir;
    }
    @Override
    public void collisionCallback(GameObject owner, GameObject object) {
        Position ownerPosition=owner.getCurrentPosition();
        Position otherPosition=object.getCurrentPosition();
        CollisionComponent component=(CollisionComponent) object.getPhysics();
        if (component.isSolid()) {
            //if the object is solid and under me, set the flag
            Position opt=super.closestPoints(object,owner);

            if ((int)Math.abs(opt.getY()) <= (int)Math.abs(opt.getX())) {
                if ((int) opt.getY() <= 0) {
                    owner.getVelocity().setY(0);
                    this.inAir=false;
                } else if (opt.getY() > 0) {
                    owner.getVelocity().setY(-0.1);
                }
            }
            else {
                if (opt.getX() >= 0 ) {
                    owner.getVelocity().setX(0.01);
                }
                else {
                    owner.getVelocity().setX(-0.01);
                }
            }

        }

    }
    @Override
    public void move(GameObject object) {
        if (this.inAir) {
            this.gravity(object);
        }
        else {
        }
        object.getVelocity().moveObject(object);
    }

}
