
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import GameObject;
import GatorEngine;
import ScriptableBehavior;

public class ObstacleMovementScript extends ScriptableBehavior {
    private float speed;

    public ObstacleMovementScript(GameObject g, float speed) {
        super(g);
        this.speed = speed;
    }

    @Override
    public void Start() {
        // Initialization if needed
    }

    @Override
    public void Update() {
        // Move obstacle left
        gameObject.Translate(-speed, 0);

        // Remove obstacle if it moves off-screen
        AffineTransform transform = gameObject.GetObjectTransform();
        Shape shape = gameObject.GetShape();
        Rectangle2D bounds = shape.getBounds2D();
        double xPos = transform.getTranslateX();

        if(xPos + bounds.getWidth() < 0) {
            GatorEngine.Delete(gameObject);
            System.out.println("Obstacle moved off-screen and was deleted.");
        }
    }
}