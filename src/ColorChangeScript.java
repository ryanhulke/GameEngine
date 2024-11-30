
import java.awt.Color;

import ScriptableBehavior;

public class ColorChangeScript extends ScriptableBehavior {
    private int frameCount = 0;

    public ColorChangeScript(GameObject g) {
        super(g);
    }

    @Override
    public void Start() {
    }

    @Override
    public void Update() {
        frameCount++;
        // Change color every 60 frames (~1 second at 60 FPS)
        if (frameCount % 60 == 0) {
            // Generate a random color
            Color randomColor = new Color(
                (float)Math.random(),
                (float)Math.random(),
                (float)Math.random()
            );
            gameObject.GetObjectMaterial().SetFill(randomColor);
        }
    }
}