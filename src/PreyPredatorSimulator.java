import gui.GUISimulator;
import gui.Oval;
import gui.Simulable;

import java.awt.*;
import java.util.List;

public class PreyPredatorSimulator implements Simulable {
        private Prey preys;
        private Predator predators;

        private GUISimulator gui;
        private EventManager eventManager;

        public  PreyPredatorSimulator(Prey preys,Predator predators,GUISimulator gui){
            this.preys=preys;
            this.predators= predators;
            this.gui = gui;
            this.eventManager = new EventManager();
            //this.draw();
        }

        public void next(){
            this.eventManager.next();
            this.eventManager.addEvent(new PreyEvent(this.eventManager.getCurrentDate()+predators.getStep(),preys,predators,gui,eventManager));
            this.eventManager.addEvent(new PredatorEvent(this.eventManager.getCurrentDate()+predators.getStep(),preys,predators,gui,eventManager));
            this.gui.reset();
            this.draw();

        }
        public void restart(){


            this.draw();
        }

        public void draw(){
            int numPreys= this.preys.getPosition().length;
            for (int i = 0;i<numPreys;i++){
                int x =(int)this.preys.getPosition()[i].x;
                int y =(int)this.preys.getPosition()[i].y;
                gui.addGraphicalElement(new Oval(x,y, Color.BLUE,Color.BLUE,this.preys.getDiam()));
            }
            int numPredators = this.predators.getPosition().length;
            for (int j = 0;j<numPredators;j++){
                int x =(int)this.predators.getPosition()[j].x;
                int y =(int)this.predators.getPosition()[j].y;
                gui.addGraphicalElement(new Oval(x,y, Color.GRAY,Color.GRAY,this.preys.getDiam()));
            }

        }

}
