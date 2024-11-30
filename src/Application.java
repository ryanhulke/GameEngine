import java.awt.Color;
import java.awt.geom.Rectangle2D;

import GameObject;
import GatorEngine;
import Material;


public class Application {
    //This is where we can implement applications in the Gator Engine.

    //We don't have an "editor mode", so we can only manually set up our application.
    
    //Here is an example of creating a GameObject and giving it a behavior (moving with WASD).
    public static void Start(){
        
        // // Create a GameObject at position (200, 200)
        // GameObject g = new GameObject(200, 200);
        // // Set a shape that will define the size of the image
        // g.SetShape(new Rectangle2D.Float(100, 0, 100, 100));
        // // Set an image material
        // g.SetObjectMaterial(new Material("resources/gator.jpg"));
        // g.AddScript(new MovementScript(g, 3));

        // // Add it to the engine
        // GatorEngine.Create(g);

        // GameObject g2 = new GameObject(400, 200);
        // // Set a shape that will define the size of the image
        // g2.SetShape(new Rectangle2D.Float(500, 0, 100, 100));
        // // Set an image material
        // g2.SetObjectMaterial(new Material("resources/gator.jpg"));

        // // add new random movement script
        // g2.AddScript(new RandomMovementScript(g2, 3));

        // // Add it to the engine
        // GatorEngine.Create(g2);

        // GameObject g = new GameObject(100, 100);
        // g.SetShape(new Rectangle2D.Float(0, 0, 100, 100));
        // g.SetObjectMaterial(new Material(Color.BLUE, Color.BLACK, 2));
        // g.AddScript(new ColorChangeScript(g));
        // g.AddScript(new ScaleScript(g));
        // GatorEngine.Create(g);

        GameObject player = new GameObject(100, 100); // Starting position at (100, 100)
        player.SetShape(new Rectangle2D.Float(0, 0, 100, 100)); // Player is a 50x50 rectangle
        player.SetObjectMaterial(new Material("resources/gator.jpg")); // Load player image
        player.AddScript(new PlayerControllerScript(player, 12.0f)); // Movement speed 5.0f
        GatorEngine.Create(player); // Add player to the engine

        // Create obstacle spawner GameObject (invisible)
        GameObject spawner = new GameObject(0, 0); // Position irrelevant
        // Set material to be fully transparent to make spawner invisible
        spawner.SetObjectMaterial(new Material(new Color(0, 0, 0, 0), new Color(0, 0, 0, 0), 0));
        spawner.AddScript(new ObstacleSpawnerScript(spawner, 5, 40)); // Spawn every 120 frames, speed 3
        GatorEngine.Create(spawner);
    }
}