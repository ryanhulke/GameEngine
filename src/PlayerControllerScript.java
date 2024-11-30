import java.awt.geom.Ellipse2D;

public class PlayerControllerScript extends ScriptableBehavior {
    private float speed;

    public PlayerControllerScript(GameObject g, float speed) {
        super(g);
        this.speed = speed;
    }

    @Override
    public void Start() {
        // Initialization if needed
    }

    @Override
    public void Update() {
        // Handle movement with ADWS
        if(Input.GetKeyHeld('a') || Input.GetKeyHeld('A')) {
            gameObject.Translate(-speed, 0);
        }
        if(Input.GetKeyHeld('d') || Input.GetKeyHeld('D')) {
            gameObject.Translate(speed, 0);
        }
        if(Input.GetKeyHeld('w') || Input.GetKeyHeld('W')) {
            gameObject.Translate(0, -speed);
        }
        if(Input.GetKeyHeld('s') || Input.GetKeyHeld('S')) {
            gameObject.Translate(0, speed);
        }

        // Check collision with obstacles
        for(GameObject obstacle : GatorEngine.OBJECTLIST) {
            // Assuming obstacles are circles (Ellipse2D)
            if(obstacle != this.gameObject && obstacle.GetShape() instanceof Ellipse2D.Float) {
                if(gameObject.CollidesWith(obstacle)) {
                    // Delete player GameObject on collision
                    GatorEngine.Delete(this.gameObject);
                    System.out.println("Player collided with an obstacle and was deleted.");
                    break;
                }
            }
        }
    }
}