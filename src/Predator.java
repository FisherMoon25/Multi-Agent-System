import java.awt.geom.Point2D;
import java.util.List;

public class Predator extends Boid {
    public Predator(Point2D.Float[] position, Point2D.Float[] velocity, float vlimX, float vlimY, float fieldOfView,long step) {
        super(position, velocity, vlimX, vlimY, fieldOfView,step);
    }

    private void chase(Prey preys) {
        int numBoids = this.getPosition().length;
        int numPreys = preys.getPosition().length;
        for (int i = 0; i < numBoids; i++) {
            Point2D.Float c = new Point2D.Float(0, 0);
            int count = 0;
            for (int j = 0; j < numPreys; j++) {

                float distX=this.getPosition()[i].x - preys.getPosition()[j].x;
                float distY=this.getPosition()[i].x - preys.getPosition()[j].x;
                double d =Math.sqrt(distX*distX+distY*distY);
                if (d>0&&d<distSeparation){

                    c.setLocation(c.x - (this.getPosition()[i].x - preys.getPosition()[j].x), c.y - (this.getPosition()[i].y - preys.getPosition()[j].y));


                }

            }

            if (count > 0) {

                double n = Math.sqrt(Math.pow(c.getX(), 2) + Math.pow(c.getY(), 2));
                c.setLocation(c.getX() / n, c.getY() / n);
                Point2D.Float acceleration = new Point2D.Float(c.x - this.getVelocity()[i].x, c.y - this.getVelocity()[i].y);
                this.acceleration[i].x += acceleration.x;
                this.acceleration[i].y += acceleration.y;

            }
        }
    }
    public void update(Prey preys,int width,int height){
        this.cohesion();
        this.separation();
        this.alignement();
        this.chase(preys);
        int numBoids =this.getPosition().length;
        for(int i=0;i<numBoids;i++){
            this.getVelocity()[i].x +=this.acceleration[i].x;
            this.getVelocity()[i].y +=this.acceleration[i].y;
            this.getPosition()[i].x+=this.getPosition()[i].x;
            this.getPosition()[i].y+=this.getPosition()[i].y;
            this.acceleration[i].setLocation(0,0);
            this.boundedPosition(getPosition()[i],getVelocity()[i],width,height);
        }




    }


}

