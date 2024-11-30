
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public class RandomMovementScript extends ScriptableBehavior {
    float speedX, speedY;
    Random random;

    public RandomMovementScript(GameObject g, float speed) {
        super(g);
        this.speedX = speed;
        this.speedY = speed;
        this.random = new Random();
    }

    @Override
    public void Start() {
        // Initialization if needed
    }

    @Override
    public void Update() {
        // Randomly change direction
        if (random.nextFloat() < 0.05) { // 10% chance to change direction
            speedX = (random.nextFloat() - 0.5f) * 2 * Math.abs(speedX);
            speedY = (random.nextFloat() - 0.5f) * 2 * Math.abs(speedY);
        }

        gameObject.Translate(speedX, speedY);

        // Reverse direction when reaching edges
        AffineTransform transform = gameObject.GetObjectTransform();
        Point2D position = new Point2D.Double();
        transform.transform(new Point2D.Double(0, 0), position);

        // Get bounds of the GameObject
        Shape shape = gameObject.GetShape();
        Rectangle2D bounds = shape.getBounds2D();

        if (position.getX() < 0 || position.getX() > GatorEngine.WIDTH - bounds.getWidth()) {
            speedX = -speedX;
        }
        if (position.getY() < 0 || position.getY() > GatorEngine.HEIGHT - bounds.getHeight()) {
            speedY = -speedY;
        }
    }
}
