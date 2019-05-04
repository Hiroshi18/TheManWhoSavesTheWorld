
import java.io.Serializable;

/**
 * Scenario class determines what will happen during the game flow. EnemyUnit objects, Mines and Meteors are added to the GameObjectManager at the predetermined times.
 */
public class Scenario implements Serializable {
    // variables
    private GameObjectManager manager;
    private int distance;

    /**
     * Creates a new Scenario.
     */
    public Scenario() {
        distance = 0;
    }
    /**
     * Moves Scenario
     */
    public void move () {
        manager = Game.ENGINE.getManager();
        if (distance == 0) {
            manager.add(new XWING(300, Game.ENGINE.getDifficulty()));
            manager.add(new Mine(500, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 20) {
            manager.add(new XWING(200, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 40) {
            manager.add(new XWING(100, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 60) {
            manager.add(new XWING(400, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 200) {
            manager.add(new XWING(300, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 250) {
            manager.add(new XWING(550, Game.ENGINE.getDifficulty()));
            manager.add(new Meteor(-50, -40, 3 * Math.PI / 2));
        }

        else if (distance == 300) {
            manager.add(new XWING(50, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 450) {
            manager.add(new Solar(300, Solar.RIGHT, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 500) {
            manager.add(new Solar(500, Solar.RIGHT, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 550) {
            manager.add(new Solar(300, Solar.RIGHT, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 725) {
            manager.add(new Meteor(50, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(150, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(250, -40, 3 * Math.PI / 2));
        }

        else if (distance == 825) {
            manager.add(new Meteor(350, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(450, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(550, -40, 3 * Math.PI / 2));
        }

        else if (distance == 850) {
            manager.add(new Meteor(100, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(200, -40, 3 * Math.PI / 2));
        }

        else if (distance == 875) {
            manager.add(new Meteor(350, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(450, -40, 3 * Math.PI / 2));
        }
        else if (distance == 900) {
            manager.add(new Mine(150, -40, 3 * Math.PI / 4, Game.ENGINE.getDifficulty()));
        }


        else if (distance == 1000) {
            manager.add(new Shadow(100, Game.ENGINE.getDifficulty(), false));
            manager.add(new Shadow(500, Game.ENGINE.getDifficulty(), false));
        }
        else if (distance == 1150) {
            manager.add(new Solar(200, Solar.LEFT, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 1200) {
            manager.add(new Solar(300, Solar.LEFT, Game.ENGINE.getDifficulty()));
        }
        else if (distance == 1250) {
            manager.add(new Solar(400, Solar.LEFT, Game.ENGINE.getDifficulty()));
        }
        else if (distance == 1300) {
            manager.add(new Solar(500, Solar.LEFT, Game.ENGINE.getDifficulty()));
        }
        else if (distance == 1400) {
            manager.add(new Mine(150, -40, 5 * Math.PI / 4, Game.ENGINE.getDifficulty()));

        }

        else if(distance == 1500) {
            manager.add(new Meteor(350, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(450, -40, 3 * Math.PI / 2));
        }
        else if (distance == 1600) {
            manager.add(new Shadow(300, Game.ENGINE.getDifficulty(), false));
        }
        else if (distance == 1700) {
            manager.add(new Mine(500, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 1750) {
            manager.add(new Mine(100, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 1850) {
            manager.add(new Solar(200, Solar.RIGHT, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 1950){
            manager.add(new XWING(200, Game.ENGINE.getDifficulty()));
            manager.add(new XWING(300, Game.ENGINE.getDifficulty()));
            manager.add(new XWING(400, Game.ENGINE.getDifficulty()));
        }

        else if(distance == 2000){
             manager.add(new Mine(550, -40, 3 * Math.PI / 4, Game.ENGINE.getDifficulty()));
        }

        else if (distance == 2120){
            manager.add(new Meteor(50, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(150, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(450, -40, 3 * Math.PI / 2));
            manager.add(new Meteor(550, -40, 3 * Math.PI / 2));
        }

        else if (distance == 2250){
            manager.add(new Shadow(300, Game.ENGINE.getDifficulty(), true));
        }

        else if (distance == 2350){
            manager.add(new Solar(500, Solar.LEFT, Game.ENGINE.getDifficulty()));
        }

        else if(distance == 2500){
            manager.add(new Shadow(100, Game.ENGINE.getDifficulty(), false));
            manager.add(new Shadow(500, Game.ENGINE.getDifficulty(), false));


        }
        else if (distance == 3300) {
        	manager.add(new Meteor(200, -40, 3 * Math.PI / 2));
        	manager.add(new Meteor(100, -40, 3 * Math.PI / 2));
        	manager.add(new Meteor(400, -40, 3 * Math.PI / 2));
        	manager.add(new Meteor(500, -40, 3 * Math.PI / 2));
            manager.add(new Shadow(300, Game.ENGINE.getDifficulty(), true));

        }
        else if (distance ==3800) {
         manager.add(new XWING(100, Game.ENGINE.getDifficulty()));
         manager.add(new XWING(200, Game.ENGINE.getDifficulty()));
         manager.add(new XWING(300, Game.ENGINE.getDifficulty()));
         manager.add(new XWING(400, Game.ENGINE.getDifficulty()));
         manager.add(new XWING(500, Game.ENGINE.getDifficulty()));
        }
        else if (distance == 3900) {
            manager.add(new Solar(100, Solar.LEFT, Game.ENGINE.getDifficulty()));
        }
        else if (distance == 3950) {
            manager.add(new Solar(250, Solar.LEFT, Game.ENGINE.getDifficulty()));
        }
        else if (distance == 4000) {
            manager.add(new Solar(500, Solar.LEFT, Game.ENGINE.getDifficulty()));
             }
        else if (distance == 4050) {
            manager.add(new Solar(150, Solar.LEFT, Game.ENGINE.getDifficulty()));
             }
       else if (distance == 4100) {
            manager.add(new Solar(350, Solar.LEFT, Game.ENGINE.getDifficulty()));
             }
       else if (distance == 4300) {
       	    manager.add(new Mine(75, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(150, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(225, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(300, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(375, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(450, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(525, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
             }
       else if (distance == 4325) {
       	    manager.add(new Mine(75, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(150, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(225, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(300, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(375, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(450, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(525, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
           }

       else if (distance == 4350) {
       	    manager.add(new Mine(75, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(150, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(225, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(300, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(375, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(450, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
       	    manager.add(new Mine(525, -40, 3 * Math.PI / 2, Game.ENGINE.getDifficulty()));
           }
  		else if (distance == 4400) {
            manager.add(new Shadow(100, Game.ENGINE.getDifficulty(), false));
            manager.add(new Shadow(300, Game.ENGINE.getDifficulty(), false));
            manager.add(new Shadow(500, Game.ENGINE.getDifficulty(), false));

        }
        else if (distance == 4950) {
            Game.ENGINE.finalStep();
            manager.add(new DeathStar(Game.ENGINE.getDifficulty()));
        }
        distance++;
    }
}
