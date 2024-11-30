import java.awt.Color;
import java.awt.geom.Ellipse2D;
import java.util.Random;

public class ObstacleSpawnerScript extends ScriptableBehavior {
    private int spawnInterval; // Spawn every 120 frames (~2 seconds at 60 FPS)
    private int frameCount = 0;
    private Random random = new Random();
    private int speed; // Default speed of 3 pixels per frame

    public ObstacleSpawnerScript(GameObject g, int spawnInterval, int speed) {
        super(g);
        this.spawnInterval = spawnInterval;
        this.speed = speed;
    }

    @Override
    public void Start() {
        // Initialization if needed
    }

    @Override
    public void Update() {
        frameCount++;
        if(frameCount >= spawnInterval) {
            spawnObstacle();
            frameCount = 0;
        }
    }

    private void spawnObstacle() {
        // Define size of obstacle
        int diameter = 30 + random.nextInt(20); // Diameter between 30 and 50 pixels

        // Define Y position randomly within the window height
        float yPos = random.nextFloat() * (GatorEngine.HEIGHT - diameter);

        // Create new obstacle GameObject at the right edge
        GameObject obstacle = new GameObject(GatorEngine.WIDTH, (int)yPos);
        obstacle.SetShape(new Ellipse2D.Float(0, 0, diameter, diameter));
        obstacle.SetObjectMaterial(new Material(Color.RED, Color.BLACK, 1));
        obstacle.AddScript(new ObstacleMovementScript(obstacle, speed)); // Move left at speed 3.0f
        GatorEngine.Create(obstacle);
        System.out.println("Spawned obstacle at Y=" + yPos);
    }
}