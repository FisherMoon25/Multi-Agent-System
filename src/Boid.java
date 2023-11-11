
import java.awt.geom.Point2D.Float;
import java.util.List;


public class Boid
{
    private Float currPos;
    private Float initPos;
    private Float currVel;
    private Float initVel;
    private float currDir;
    private float initDir;

    public Boid(Float pos, Float vel, float direction)
    {
        this.currPos = pos;
        this.initPos = pos;
        this.currVel = vel;
        this.initVel = vel;
        this.currDir = direction;
        this.initDir = direction;
    }
    public Boid(Float pos)
    {
        this.currPos = pos;
        this.initPos = pos;
        this.currVel = new Float();
        this.initPos = new Float();
        this.currDir = 0;
        this.initDir = 0;
    }

    public Float getCurrPos() {
        return currPos;
    }

    public void setCurrPos(Float currPos) {
        this.currPos = currPos;
    }

    public Float getCurrVel() {
        return currVel;
    }

    public void setCurrVel(Float currVel) {
        this.currVel = currVel;
    }
    public void setCurrDir(float currDir) {
        this.currDir = currDir;
    }

    public void reInit()
    {
        this.currVel.setLocation(this.initVel);
        this.currPos.setLocation(this.initPos);
        this.currDir = this.initDir;
    }

    public Float cohesionForce(List<Boid> neighbors)
    {
        Float pc = new Float(0, 0);
        for (Boid neighbor : neighbors)
        {
            pc.setLocation(pc.x + neighbor.currPos.x, pc.y + neighbor.currPos.y);
        }
        pc.setLocation(pc.x / (neighbors.size()), pc.y / (neighbors.size()));
        pc.setLocation((pc.x - this.currPos.x) / 100,
                (pc.y - this.currPos.y) / 100);
        return pc;
    }

    public Float seperationForce(List<Boid>  neighbors, int benchmark)
    {
        Float c = new Float();
        for (Boid neighbor : neighbors)
        {
            if (neighbor.currPos.distance(this.currPos) < benchmark)
            {
                c.setLocation(c.x - (neighbor.currPos.x - this.currPos.x),
                        c.y - (neighbor.currPos.y - this.currPos.y));
            }
        }
        return c;
    }

    public void alignementForce(List<Boid> neighbors)
    {
        float newDir = 0;
        for (Boid neighbor : neighbors)
        {
            newDir += neighbor.currDir;
        }
        newDir = newDir / (neighbors.size());

        this.currDir = newDir;
    }

}
